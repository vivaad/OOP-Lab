#include <stdio.h>


int main(void)
{
   int pages[30];
   int frames[10];
   int refBit[10];
   int n;
   int f;
   int i;
   int j;
   int pageFaults = 0;
   int hit = 0;
   int pointer = 0; /* points to the frame to be replaced */


   /* Input */
   printf("Enter number of pages: ");
   if (scanf("%d", &n) != 1)
   {
       fprintf(stderr, "Invalid input for number of pages\n");
       return 1;
   }


   printf("Enter the reference string:\n");
   for (i = 0; i < n; i++)
   {
       if (scanf("%d", &pages[i]) != 1)
       {
           fprintf(stderr, "Invalid page number at position %d\n", i);
           return 1;
       }
   }


   printf("Enter number of frames: ");
   if (scanf("%d", &f) != 1)
   {
       fprintf(stderr, "Invalid input for number of frames\n");
       return 1;
   }


   if (f <= 0 || f > 10)
   {
       fprintf(stderr, "Number of frames must be between 1 and 10\n");
       return 1;
   }


   /* Initialize frames and reference bits */
   for (i = 0; i < f; i++)
   {
       frames[i] = -1;
       refBit[i] = 0;
   }


   /* Simulation */
   for (i = 0; i < n; i++)
   {
       int page = pages[i];
       int found = 0;


       /* Check if page already in frame (HIT) */
       for (j = 0; j < f; j++)
       {
           if (frames[j] == page)
           {
               refBit[j] = 1; /* Give second chance */
               found = 1;
               hit++;
               break;
           }
       }


       /* MISS (Page Fault) */
       if (!found)
       {
           /* Find a victim using second chance (clock) */
           while (refBit[pointer] == 1)
           {
               refBit[pointer] = 0;             /* Remove second chance */
               pointer = (pointer + 1) % f;     /* Move circularly */
           }


           /* Replace the page at pointer */
           frames[pointer] = page;
           refBit[pointer] = 1;
           pointer = (pointer + 1) % f;
           pageFaults++;
       }


       /* Print current frame contents */
       printf("Step %2d: ", i + 1);
       for (j = 0; j < f; j++)
       {
           if (frames[j] == -1)
           {
               printf("- ");
           }
           else
           {
               printf("%d ", frames[j]);
           }
       }
       printf("\n");
   }


   /* Results */
   printf("\nTotal Page Faults = %d\n", pageFaults);

      return 0;
}

