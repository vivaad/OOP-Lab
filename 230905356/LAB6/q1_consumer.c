#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <fcntl.h>
#include <limits.h>
#include <sys/types.h>
#include <sys/stat.h>
#define FIFO_NAME "./my_fifo"
#define BUFFER_SIZE PIPE_BUF

int main(){
	int pipe_fd;
	int res;
	int open_mode = O_RDONLY;
	char buffer[BUFFER_SIZE+1];
	int bytes_read=0;
	memset(buffer,'\0',sizeof(buffer));
	printf("Process %d opening FIFO O_RDONLY\n",getpid());
	pipe_fd = open(FIFO_NAME,open_mode);

	char a[4][10];
	printf("Process %d result %d\n",getpid(),pipe_fd);
	if(pipe_fd!=-1){
		for(int i=0;i<4;i++){
			res = read(pipe_fd,&a[i],sizeof(a[i]));
			if(res<0){
				printf("READ ERROR!");
				exit(EXIT_FAILURE);
			}
		}
	}else{
		exit(EXIT_FAILURE);
	}
	printf("Process %d finished, %d bytes read\n",getpid(),bytes_read);

	for(int i=0;i<4;i++){
		printf("%s",a[i]);
		printf("\n");
	}
	exit(EXIT_SUCCESS);
}
