// q3.c — Deliberate deadlock via improper semaphore ordering
// Build: gcc -O2 -Wall -pthread q3.c -o q3


#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <semaphore.h>

sem_t S1, S2;


void *threadA(void *arg) {
   sem_wait(&S1);               // lock S1
   usleep(100000);              // small delay to increase deadlock chance
   sem_wait(&S2);               // waits for S2 (held by threadB) -> deadlock
   // never reached
   sem_post(&S2);
   sem_post(&S1);
   return NULL;
}


void *threadB(void *arg) {
   sem_wait(&S2);               // lock S2
   usleep(100000);              // small delay to increase deadlock chance
   sem_wait(&S1);               // waits for S1 (held by threadA) -> deadlock
   // never reached
   sem_post(&S1);
   sem_post(&S2);
   return NULL;
}


int main(void) {
   pthread_t a, b;


   sem_init(&S1, 0, 1);
   sem_init(&S2, 0, 1);


   pthread_create(&a, NULL, threadA, NULL);
   pthread_create(&b, NULL, threadB, NULL);


   // These joins will block forever due to deadlock.
   pthread_join(a, NULL);
   pthread_join(b, NULL);


   sem_destroy(&S1);
   sem_destroy(&S2);
   return 0;
}

