#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>

struct shared_use_st {
	int written_by_player1;
	int tic_tac[3][3];
	int winner;
};

void print_board(struct shared_use_st* shared_stuff){
	for(int i=0;i<3;i++){
		for(int j=0;j<3;j++){
			printf("%d\t",shared_stuff->tic_tac[i][j]);
		}
		printf("\n");
	}
}

int check_rows(int a[3][3]){
	for(int i=0;i<3;i++){
		if(a[i][0]==1&&a[i][1]==1&&a[i][2]==1){
			return 1;
		}
	}
	return 0;
}

int check_columns(int a[3][3]){
	for(int i=0;i<3;i++){
		if(a[0][i]==1&&a[1][i]==1&&a[2][i]==1){
			return 1;
		}
	}
	return 0;
}

int check_diagonals(int a[3][3]){
	if(a[0][0]==1&&a[1][1]==1&&a[2][2]==1){
		return 1;
	}else{
		if(a[0][2]==1&&a[1][1]==1&&a[2][0]==1){
			return 1;
		}
	}
	return 0;
}

int main(){
	int running=1;
	void *shared_memory=(void*)0;
	struct shared_use_st *shared_stuff;
	int shmid;
	shmid=shmget((key_t)1234,sizeof(struct shared_use_st),0666|IPC_CREAT);
	if(shmid==-1){
		fprintf(stderr,"shmget failed");
		exit(EXIT_FAILURE);
	}
	shared_memory = shmat(shmid,(void*)0,0);
	if(shared_memory==(void*)-1){
		fprintf(stderr,"shmat failed");
		exit(EXIT_FAILURE);

	}

	printf("Memory attached at %x \n",(int)shared_memory);
	shared_stuff = (struct shared_use_st*)shared_memory;
	shared_stuff->written_by_player1=0;
	for(int i=0;i<3;i++){
		for(int j=0;j<3;j++){
			shared_stuff->tic_tac[i][j]=0;
		}
	}
	int row=0,column=0;
	shared_stuff->winner=0;

	while(running){
		while(shared_stuff->written_by_player1==1&&shared_stuff->winner==0);
		if(shared_stuff->winner==2){
			printf("You lose! :( \n");
			break;
		}
		print_board(shared_stuff);
		printf("\n");
		printf("Player 1 turn.........\n");
		printf("Choose row: \n");
		scanf("%d",&row);
		printf("Choose column: \n");
		scanf("%d",&column);
		shared_stuff->tic_tac[row][column]=1;
		if(check_rows(shared_stuff->tic_tac)){
			printf("Player 1 wins!");
			shared_stuff->winner=1;
			break;
		}
		if(check_columns(shared_stuff->tic_tac)){
			printf("Player 1 wins;");
			shared_stuff->winner=1;
			break;
		}
		if(check_diagonals(shared_stuff->tic_tac)){
			printf("Player 1 wins!");
			shared_stuff->winner=1;
			break;
		}
		shared_stuff->written_by_player1=1;
	}

	if (shmdt(shared_memory) == -1) {
	fprintf(stderr,"shmdt failed\n");
	exit(EXIT_FAILURE);
	}
	if (shmctl(shmid, IPC_RMID, 0) == -1) {
	fprintf(stderr, "shmctl(IPC_RMID) failed\n");
	exit(EXIT_FAILURE);
	}
	exit(EXIT_SUCCESS);

}