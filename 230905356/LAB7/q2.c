#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include<sys/wait.h>
struct shared_use_st{
	int written_by_parent;
	char alphabet;
};


int main(){
	void *shared_memory=(void*)0;
	struct shared_use_st *shared_stuff;
	int shmid;
	shmid=shmget((key_t)1234,sizeof(struct shared_use_st),0666|IPC_CREAT);

	if (shmid==-1){
		fprintf(stderr,"shmgget failed\n");
		exit(EXIT_FAILURE);
	}

	shared_memory = shmat(shmid,(void*)0,0);

	if(shared_memory==(void*)-1){
		fprintf(stderr,"shmat failed\n");
		exit(EXIT_FAILURE);
	}

	printf("Memory attached at %x\n",(int*)shared_memory);
	shared_stuff=(struct shared_use_st*)shared_memory;
	shared_stuff->written_by_parent=0;

	pid_t pid = fork();
	if(pid==0){
		while(shared_stuff->written_by_parent==0){
			sleep(1);
		}
		printf("Child process running.....\n");
		char alpha_prev=shared_stuff->alphabet;
		alpha_prev=alpha_prev+1;
		shared_stuff->alphabet=alpha_prev;
		shared_stuff->written_by_parent=0;
		exit(0);

	}else{
		printf("Parent process running.....");
		printf("Enter an alphabet: \n");
		char alphabet;
		scanf("%c",&alphabet);
		shared_stuff->alphabet=alphabet;
		shared_stuff->written_by_parent=1;
		wait(NULL);
		printf("Next alphabet is: %c\n",shared_stuff->alphabet);
		exit(0);
	}


}
