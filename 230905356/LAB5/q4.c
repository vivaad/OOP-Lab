#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<sys/types.h>
#include<sys/wait.h>


int main(){
	int n;
	printf("\nFork starting\n");
	pid_t pid = fork();

	if(pid<0){
		printf("\nError while creating a fork()");
	}else if(pid==0){
		printf("\nChild process beginning....\n");
		printf("\nChild process terminating......\n");
		exit(0);
	}else{
		sleep(5);
		printf("\nParent process beginning....\n");
		execl("/bin/ps","ps","-ef",NULL);
		printf("\nParent process terminating.....\n");
		exit(0);
	}

}