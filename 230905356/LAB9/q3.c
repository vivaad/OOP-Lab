#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>


typedef struct
{
   int start;
   int end;
} Range;


int isPrime(int n);
void *primesInBetween(void *arg);


int main(void)
{
   int s, e;
   printf("Enter start and end points: ");
   if (scanf("%d %d", &s, &e) != 2)

       {
       fprintf(stderr, "Invalid input\n");
       return 1;
   }


   Range *r = malloc(sizeof(*r));
   if (r == NULL)
   {
       perror("malloc");
       return 1;
   }
   r->start = s;
   r->end = e;


   pthread_t tid;
   if (pthread_create(&tid, NULL, primesInBetween, r) != 0)
   {
       perror("pthread_create");
       free(r);
       return 1;
   }


   pthread_join(tid, NULL);
   return 0;
}


void *primesInBetween(void *arg)
{
   Range *rg = (Range *)arg;
   int a = rg->start;
   int b = rg->end;
   free(rg);


   if (a > b)
   {
       int tmp = a;
       a = b;
       b = tmp;
   }


   printf("Primes between %d and %d:\n", a, b);
   for (int i = (a < 2) ? 2 : a; i <= b; i++)

      {
       if (isPrime(i))
       {
           printf("%d ", i);
       }
   }
   printf("\n");
   return NULL;
}


int isPrime(int n)
{
   if (n <= 1)
   {
       return 0;
   }
   if (n == 2)
   {
       return 1;
   }
   if ((n & 1) == 0)
   {
       return 0;
   }
   for (int i = 3; (long long)i * i <= n; i += 2)
   {
       if (n % i == 0)
       {
           return 0;
       }
   }
   return 1;
}


