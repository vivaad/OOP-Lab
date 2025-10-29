#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <fcntl.h>
#include <limits.h>
#include <sys/types.h>
#include <sys/stat.h>
#include<assert.h>
#include<sys/wait.h>
#define BUFFER_SIZE 25


int main(int argc, char *argv[]){
	int fd[2];
	pid_t pid;
	assert(argc==2);
	if(pipe(fd)==-1){
		perror("Pipe error!");
		exit(EXIT_FAILURE);
	}
	pid=fork();

	char buffer[BUFFER_SIZE];

	if(pid==-1){
		printf("Error in creating a fork()\n");
		exit(EXIT_FAILURE);
	}else if(pid==0){
		printf("THIS IS THE CHILD!\n");
		close(fd[1]);
		int bytes_read=read(fd[0],buffer,sizeof(buffer));
		
		write(STDOUT_FILENO,buffer,sizeof(buffer));
		write(STDOUT_FILENO,"\n",1);
		close(fd[0]);
		exit(EXIT_SUCCESS);
	}else{
		printf("THIS IS THE PARENT!\n");
		close(fd[0]);
		write(fd[1],argv[1],strlen(argv[1]));
		close(fd[1]);
		wait(NULL);
		exit(EXIT_SUCCESS);
	}
}