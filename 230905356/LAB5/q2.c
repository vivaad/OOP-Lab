#include<stdio.h>
#include<stdlib.h>
#include<sys/types.h>
#include<sys/wait.h>
#include<unistd.h>


int main(){
	pid_t pid = fork();

	if(pid<0){
		printf("\nError ocurred in fork() :(");
	}else if(pid==0){
		printf("\nIm the child process for Question 2!\n");
		execl("./first","first",NULL);
	}else{
		printf("Im the parent Process for question 2!\n");
		wait(NULL);
		printf("\nQuestion 2 Child Complete.\n");
		exit(0);
	}
}