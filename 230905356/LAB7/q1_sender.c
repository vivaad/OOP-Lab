#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<errno.h>
#include<unistd.h>
#include<sys/types.h>
#include<sys/ipc.h>
#include<sys/msg.h>

#define MAX_TEX 512


struct my_msg_st{
	long int my_msg_type;
	int num_send;
};

int main(){
	int running=1;
	struct my_msg_st data;
	int msgid;
	int number;
	msgid = msgget((key_t)1234,0666|IPC_CREAT);
	if(msgid==-1){
		fprintf(stderr,"msgget failed with error: %d\n", errno);
		exit(EXIT_FAILURE);
	}

	while(running){
		printf("Enter some number: ");
		scanf("%d",&number);
		data.num_send=number;
		data.my_msg_type=1;
		if(msgsnd(msgid,(void*)&data,sizeof(data)-sizeof(data.my_msg_type),0)==-1){
			fprintf(stderr,"msgsnd failed\n");
			exit(EXIT_FAILURE);
			if(number=-1){
				running=0;
			}
		}

	}
	exit(EXIT_SUCCESS);
}