#include<stdio.h>
#include<unistd.h>
#include<stdlib.h>
#include<string.h>
#include<fcntl.h>
#include<limits.h>
#include<sys/types.h>
#include<sys/stat.h>
#define FIFO_NAME "./my_fifo"
#define BUFFER_SIZE PIPE_BUF
#define TEN_MEG (1024*1024*10)

int main(){
	int pipe_fd;
	int res;
	int open_mode = O_WRONLY;
	int bytes_sent = 0;
	char buffer[BUFFER_SIZE+1];
	if(access(FIFO_NAME,F_OK)==-1){
		res = mkfifo(FIFO_NAME,0777);
		if(res != 0 ){
			fprintf(stderr,"Could not create fifo %s\n",FIFO_NAME);
			exit(EXIT_FAILURE);
		}
	}

	char a[4][10];
	printf("Enter 4 integers:\n");
	for(int i=0;i<4;i++){
		scanf("%9s",a[i]);

	}


	printf("Process %d opening FIFO O_WRONLY\n",getpid());
	pipe_fd=open(FIFO_NAME, open_mode);
	printf("Process %d result %d\n",getpid(),pipe_fd);
	if(pipe_fd!=-1){
			for(int i=0;i<4;i++){
				res=write(pipe_fd,&a[i],sizeof(a[i]));
				if(res<0){
					printf("WRITE ERROR!\n");
					exit(EXIT_FAILURE);
				}
			}
		(void)close(pipe_fd);
	} else {
		printf("Error in opening pipe_fd\n");
		exit(EXIT_FAILURE);
	}

	printf("%d\n FINISHED\n",getpid());
	exit(EXIT_SUCCESS);
}
