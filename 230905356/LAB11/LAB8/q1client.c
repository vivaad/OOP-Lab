#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <semaphore.h>


#define FIFO_NAME "/tmp/my_fifo"


int main() {
   mkfifo(FIFO_NAME, 0666);
   sem_t *empty = sem_open("/empty", O_CREAT, 0666, 10);
   sem_t *full  = sem_open("/full",  O_CREAT, 0666, 0);


   int fd = open(FIFO_NAME, O_RDONLY);
   if (fd < 0) { perror("open"); exit(1); 
}


