#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>


void *fibonacci(void *arg);


int *arr;


int main()
{
   int n;
   printf("Enter the number of Fibonacci numbers: ");
   scanf("%d", &n);


   if (n <= 0)
   {
       printf("Please enter a positive number.\n");
       return 1;
   }


   arr = (int *)malloc(n * sizeof(int));

   if (arr == NULL)
   {
       printf("Memory allocation failed.\n");
       return 1;
   }


   pthread_t tid;
   pthread_create(&tid, NULL, fibonacci, &n);
   pthread_join(tid, NULL);


   printf("Fibonacci sequence: ");
   for (int i = 0; i < n; i++)
   {
       printf("%d\t", arr[i]);
   }
   printf("\n");


   free(arr);
   return 0;
}


void *fibonacci(void *arg)
{
   int n = *(int *)arg;


   if (n > 0)
   {
       arr[0] = 0;
   }
   if (n > 1)
   {
       arr[1] = 1;
   }


   for (int i = 2; i < n; i++)
   {
       arr[i] = arr[i - 1] + arr[i - 2];
   }


   return NULL;
}

