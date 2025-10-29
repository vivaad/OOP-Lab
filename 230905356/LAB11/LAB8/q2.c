#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>


#define NREADERS 3
#define NWRITERS 2
#define RLOOPS   5
#define WLOOPS   4

int shared_data = 0;
int readcount = 0;


sem_t mutex;     // protects readcount
sem_t rw_mutex;  // protects shared_data (writers' exclusive access)


void *reader(void *arg) {
   int id = (int)(size_t)arg;
   for (int k = 0; k < RLOOPS; k++) {
       // Entry section for readers (readers priority)
       sem_wait(&mutex);
       readcount++;
       if (readcount == 1) sem_wait(&rw_mutex); // first reader locks writers out
       sem_post(&mutex);


       // Critical section (read)
       int val = shared_data;
       // printf("[R%d] read %d\n", id, val);


       // Exit section for readers
       sem_wait(&mutex);
       readcount--;
       if (readcount == 0) sem_post(&rw_mutex); // last reader lets writers in
       sem_post(&mutex);


       usleep(50000); // tiny delay to interleave
   }
   return NULL;
}


void *writer(void *arg) {
   int id = (int)(size_t)arg;
   for (int k = 0; k < WLOOPS; k++) {
       sem_wait(&rw_mutex);          // exclusive access
       shared_data++;                // write
       // printf("[W%d] wrote %d\n", id, shared_data);
       sem_post(&rw_mutex);
       usleep(120000);               // tiny delay to interleave

        }
   return NULL;
}


int main(void) {
   pthread_t rtid[NREADERS], wtid[NWRITERS];


   sem_init(&mutex, 0, 1);
   sem_init(&rw_mutex, 0, 1);


   for (int i = 0; i < NREADERS; i++)
       pthread_create(&rtid[i], NULL, reader, (void*)(size_t)(i+1));
   for (int j = 0; j < NWRITERS; j++)
       pthread_create(&wtid[j], NULL, writer, (void*)(size_t)(j+1));


   for (int i = 0; i < NREADERS; i++) pthread_join(rtid[i], NULL);
   for (int j = 0; j < NWRITERS; j++) pthread_join(wtid[j], NULL);


   sem_destroy(&mutex);
   sem_destroy(&rw_mutex);


   // Final value (should be exactly NWRITERS * WLOOPS)
   printf("Final shared_data = %d\n", shared_data);
   return 0;
}


