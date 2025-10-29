#include <stdio.h>
#include <stdlib.h>



typedef struct Mab
{
   int offset;            // starting address of block
   int size;              // size of block
   int allocated;         // 1 if used, 0 if free
   struct Mab *next;      // next block
   struct Mab *prev;      // previous block
} Mab;


Mab* memInit(int total_size)
{
   Mab *head = (Mab*)malloc(sizeof(Mab));
   head->offset = 0;
   head->size = total_size;
   head->allocated = 0;
   head->next = NULL;
   head->prev = NULL;
   return head;
}
Mab* memSplit(Mab *m, int size)
{
   if (m->size <= size)
   {
       return m;  // can't split
   }


   Mab *newBlock = (Mab*)malloc(sizeof(Mab));
   newBlock->offset = m->offset + size;
   newBlock->size = m->size - size;
   newBlock->allocated = 0;
   newBlock->next = m->next;
   newBlock->prev = m;


   if (m->next)
   {
       m->next->prev = newBlock;
   }


   m->next = newBlock;
   m->size = size;


   return m;
}

Mab* memAlloc(Mab *m, int size)
{
   Mab *temp = m;


   while (temp != NULL)
   {
       if (!temp->allocated && temp->size >= size)
       {
           memSplit(temp, size);
           temp->allocated = 1;
           printf("Allocated %d bytes at offset %d\n", size, temp->offset);
           return temp;
       }
       temp = temp->next;
   }


   printf("No free block found!\n");
   return NULL;
}




// ------------------- MERGE FREE BLOCKS -------------------
Mab* memMerge(Mab *m)
{
   Mab *temp = m;


   while (temp != NULL && temp->next != NULL)
   {
       if (!temp->allocated && !temp->next->allocated)
       {
           temp->size += temp->next->size;
           Mab *del = temp->next;
           temp->next = del->next;


           if (del->next)
           {
               del->next->prev = temp;

                }


           free(del);
       }
       else
       {
           temp = temp->next;
       }
   }


   return m;
}




// ------------------- FREE A BLOCK -------------------
Mab* memFree(Mab *m)
{
   if (m == NULL)
   {
       return NULL;
   }


   m->allocated = 0;
   printf("Freed block at offset %d\n", m->offset);


   while (m->prev)
   {
       m = m->prev; // move to head
   }


   return memMerge(m);
}




// ------------------- DISPLAY MEMORY STATE -------------------
void memDisplay(Mab *m)
{
   printf("\nMemory Layout:\n");
   Mab *temp = m;

    while (temp)
   {
       printf("[%s %d bytes @%d] ",
              temp->allocated ? "Alloc" : "Free",
              temp->size, temp->offset);
       temp = temp->next;
   }


   printf("\n");
}




// ------------------- MAIN FUNCTION -------------------
int main()
{
   Mab *memory = memInit(1000); // total 1000 bytes
   memDisplay(memory);


   Mab *a = memAlloc(memory, 200);
   memDisplay(memory);


   Mab *b = memAlloc(memory, 300);
   memDisplay(memory);


   memory = memFree(a);
   memDisplay(memory);


   memory = memFree(b);
   memDisplay(memory);


   return 0;
}






