#include <stdio.h>


struct Segment
{
   int base;
   int limit;
};


void createSegmentTable(struct Segment segTable[]);
int logicalToPhysical(struct Segment segTable[], int segNum, int offset);


int main(void)
{
   struct Segment segTable[5];


   /* Step 1: Create the segment table */
   createSegmentTable(segTable);


   /* Step 2: Convert logical -> physical for the 3 examples */
   printf("Physical Address (i): %d\n",
          logicalToPhysical(segTable, 2, 53));


   printf("Physical Address (ii): %d\n",
          logicalToPhysical(segTable, 3, 852));


   printf("Physical Address (iii): %d\n",
          logicalToPhysical(segTable, 0, 1222));


   return 0;
}


void createSegmentTable(struct Segment segTable[])
{
   segTable[0].base = 1400;
   segTable[0].limit = 1000;


   segTable[1].base = 6300;
   segTable[1].limit = 400;


   segTable[2].base = 4300;
   segTable[2].limit = 400;


   segTable[3].base = 3200;
   segTable[3].limit = 1100;


   segTable[4].base = 4700;
   segTable[4].limit = 1000;
}


int logicalToPhysical(struct Segment segTable[], int segNum, int offset)
{
   if (offset < segTable[segNum].limit)
   {
       return segTable[segNum].base + offset;
   }
   else
   {
       printf("Invalid: Offset %d exceeds limit of segment %d\n", offset, segNum);
       return -1;
   }
}
