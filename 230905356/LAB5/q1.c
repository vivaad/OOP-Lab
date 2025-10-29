#include<stdio.h>
#include<unistd.h>
#include<sys/types.h>
#include<sys/wait.h>
#include<stdlib.h>

void main(){
	int status=-1;
	pid_t pid;
	pid = fork();
	if(pid==-1){
		printf("\nERROR child not created");
	}else if(pid==0){
		printf("\nIm the child\n");
		exit(0);
	}else{
		wait(&status);
		printf("Im the parent!");
		printf("\nChild returned: %d\n\n", status);
	}
}
