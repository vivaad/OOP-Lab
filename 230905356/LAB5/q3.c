#include <stdio.h>
#include <unistd.h>

int main() {
    pid_t pid = fork();

    if (pid > 0) {
        printf("Parent process: PID = %d, Child PID returned by fork() = %d\n", getpid(), pid);
    } else if (pid == 0) {
        printf("Child process: PID = %d, Parent PID = %d\n", getpid(), getppid());
    } else {
        perror("fork failed");
    }

    return 0;
}
