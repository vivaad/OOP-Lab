#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <semaphore.h>
#include <time.h>


#define NUM_CUSTOMERS 20
#define CHAIRS 3


/* Semaphores and mutex */
sem_t customers; /* counts waiting customers (customers sitting) */
sem_t barber;    /* barber ready to cut (signals a waiting customer) */
sem_t mutex;     /* binary semaphore to protect waiting count */


pthread_mutex_t count_mutex = PTHREAD_MUTEX_INITIALIZER; /* protects counters below */


/* Shared state */
int waiting = 0;          /* number of customers currently waiting (sitting) */
int processed_count = 0;  /* number of customers processed (served + left) */
int total_customers = NUM_CUSTOMERS;


/* Utility: random sleep (milliseconds) */
static void mssleep(int ms)
{
   usleep(ms * 1000);
}


/* Barber thread: sleeps if no customer, serves waiting customers */
void *barber_thread(void *arg)
{
   (void)arg;


   while (1)
   {
       /* Wait for a customer to arrive (will block if none) */
       sem_wait(&customers);


       /* protect waiting variable */
       sem_wait(&mutex);
       if (waiting > 0)
       {
           /* take a waiting customer */
           waiting--;
           /* signal the customer that barber is ready */
           sem_post(&barber);
           sem_post(&mutex);


           /* cutting hair (critical section) */
           printf("[Barber] is cutting hair. Waiting seats left = %d\n", waiting);
           mssleep(200 + (rand() % 300)); /* simulate haircut time */


           /* update processed_count and show progress */
           pthread_mutex_lock(&count_mutex);
           processed_count++;
           printf("[Barber] finished haircut. processed_count=%d\n", processed_count);
           /* if all customers have been processed, barber can exit */
           if (processed_count >= total_customers)
           {
               pthread_mutex_unlock(&count_mutex);
               break;
           }
           pthread_mutex_unlock(&count_mutex);
       }
       else
       {
           /* This path occurs if we received an extra wakeup used for termination.
              Just release mutex and re-check termination condition. */
           sem_post(&mutex);


           pthread_mutex_lock(&count_mutex);
           if (processed_count >= total_customers)
           {
               pthread_mutex_unlock(&count_mutex);
               break;
           }
           pthread_mutex_unlock(&count_mutex);
       }
   }


   printf("[Barber] all done, barber thread exiting.\n");
   return NULL;
}


/* Customer thread: attempt to sit, wait for barber or leave if no chair */
void *customer_thread(void *arg)
{
   int id = *(int *)arg;

    free(arg);


   /* Simulate random arrival time */
   mssleep(rand() % 500);


   printf("[Customer %d] arrives.\n", id);


   /* try to sit in waiting room */
   sem_wait(&mutex);
   if (waiting < CHAIRS)
   {
       waiting++;
       printf("[Customer %d] sits in waiting room. waiting=%d\n", id, waiting);
       /* notify barber there's a waiting customer */
       sem_post(&customers);
       sem_post(&mutex);


       /* wait until barber signals readiness */
       sem_wait(&barber);


       /* getting haircut */
       printf("[Customer %d] is getting a haircut.\n", id);
       /* haircut simulated by barber; customer just waits until barber finishes */
       /* after haircut, customer is done: update processed_count */


       /* Note: the barber increments processed_count after finishing haircut.
          So no increment here. */


       printf("[Customer %d] leaves after haircut.\n", id);
   }
   else
   {
       /* no chair available: customer leaves */
       printf("[Customer %d] found no empty chair and leaves.\n", id);
       sem_post(&mutex);


       /* mark as processed (left without haircut) */
       pthread_mutex_lock(&count_mutex);
       processed_count++;
       pthread_mutex_unlock(&count_mutex);
   }

    return NULL;
}


int main(void)
{
   pthread_t barber_tid;
   pthread_t customer_tids[NUM_CUSTOMERS];


   srand((unsigned)time(NULL));


   /* initialize semaphores:
      customers = 0 (no waiting customers initially)
      barber = 0 (barber not ready until a customer wakes him)
      mutex = 1 (binary semaphore for protecting waiting count) */
   sem_init(&customers, 0, 0);
   sem_init(&barber, 0, 0);
   sem_init(&mutex, 0, 1);


   /* start barber thread */
   if (pthread_create(&barber_tid, NULL, barber_thread, NULL) != 0)
   {
       perror("pthread_create barber");
       exit(EXIT_FAILURE);
   }


   /* start customer threads */
   for (int i = 0; i < NUM_CUSTOMERS; i++)
   {
       int *id = malloc(sizeof(int));
       if (!id)
       {
           perror("malloc");
           exit(EXIT_FAILURE);
       }
       *id = i + 1;
       if (pthread_create(&customer_tids[i], NULL, customer_thread, id) != 0)
       {
           perror("pthread_create customer");
           exit(EXIT_FAILURE);
       }
   }

   for (int i = 0; i < NUM_CUSTOMERS; i++)
   {
       pthread_join(customer_tids[i], NULL);
   }


   /* At this point all customers either were served or left.
      If barber is blocked waiting for customers, wake him once so he can
      observe processed_count and exit. */
   sem_post(&customers);


   /* wait for barber to finish */
   pthread_join(barber_tid, NULL);


   /* cleanup */
   sem_destroy(&customers);
   sem_destroy(&barber);
   sem_destroy(&mutex);


   printf("Simulation complete. processed_count=%d\n", processed_count);
   return 0;




