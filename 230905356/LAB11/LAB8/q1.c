#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <semaphore.h>


#define FIFO_NAME "/tmp/my_fifo"
int main() {
   mkfifo(FIFO_NAME, 0666);          // Create FIFO if not exists
   sem_t *empty = sem_open("/empty", O_CREAT, 0666, 10); // 10-slot gap allowed
   sem_t *full  = sem_open("/full",  O_CREAT, 0666, 0);


   int fd = open(FIFO_NAME, O_WRONLY);
   if (fd < 0) { perror("open"); exit(1); }


   for (int i = 1; i <= 30; i++) {   // Produce 30 items for demo
       sem_wait(empty);              // Wait if 10 ahead
       write(fd, &i, sizeof(i));
       printf("Produced: %d\n", i);
       sem_post(full);               // Signal one item available
       sleep(1);
   }


   close(fd);
   return 0;
}

