#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include<sys/wait.h>
#include<string.h>

struct shared_user_space{
	int written_by_interface;
	char buffer[256];
};

int main(){
	void* shared_memory = (void*)0;
	struct shared_user_space* shared_stuff;
	int shmid;
	shmid = shmget((key_t)1234,sizeof(struct shared_user_space),0666|IPC_CREAT);
	if(shmid==-1){
		fprintf(stderr,"shmget failed\n");
		exit(EXIT_FAILURE);
	}

	shared_memory = shmat(shmid,(void*)0,0);
	if(shared_memory==(void*)-1){
		fprintf(stderr,"shmat failed\n");
		exit(EXIT_FAILURE);
	}

	printf("Memory attached at %x\n",(int*)shared_memory);
	shared_stuff=(struct shared_user_space*)shared_memory;
	shared_stuff->written_by_interface=0;
	int running=1;
	while(running){
		printf("Enter a message to send: \n");
		gets(shared_stuff->buffer);
		shared_stuff->written_by_interface=1;
		if(strcmp(shared_stuff->buffer,"STOP")==0){
			running=0;
		}
	}
	exit(0);

}