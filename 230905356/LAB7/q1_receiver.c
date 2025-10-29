#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<errno.h>
#include<unistd.h>
#include<sys/types.h>
#include<sys/ipc.h>
#include<sys/msg.h>


struct my_msg_st{
	long int my_msg_type;
	int send_num;
};

int main(){
	int running=1;
	int msgid;
	int number,temp,reversed=0;
	struct my_msg_st data;
	long int msg_to_receive=0;
	msgid = msgget((key_t)1234, 0666 | IPC_CREAT);
	if(msgid==-1){
		fprintf(stderr,"msgget failed with error: %d\n",errno);
		exit(EXIT_FAILURE);
	}
	while(running){
		if(msgrcv(msgid, (void*)&data,sizeof(data)-sizeof(data.my_msg_type),msg_to_receive,0)==-1){
			fprintf(stderr,"msgrcv failed with error: %d\n", errno);
			exit(EXIT_FAILURE);
		}
		printf("Received number is: %d\n",data.send_num);
		number=data.send_num;
		while(number>0){
			temp=number%10;
			number=number/10;
			reversed=reversed*10+temp;
		}
		if(data.send_num==reversed){
			printf("Palindrome\n");
		}else{
			printf("Not a palindrome\n");
		}
		reversed=0;

	}

	if(msgctl(msgid,IPC_RMID,0)==-1){
		fprintf(stderr,"msgctl(ipc_rmid) failed\n");
		exit(EXIT_FAILURE);
	}
	exit(EXIT_SUCCESS);
}