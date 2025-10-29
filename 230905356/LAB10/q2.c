#include <stdio.h>
#include <stdlib.h>

#define TIME_QUANTUM_Q1 4
#define TIME_QUANTUM_Q2 8
#define MAX_PROCESSES 10

typedef struct process {
    int pid;
    int arrival_time;
    int burst_time;
    int remaining_time;
    int queue_level;
    int completed;
} Process;

// Function prototypes
void roundRobin(Process *p[], int *n, int tq, int *time);
void fcfs(Process *p[], int *n, int *time);

int main() {
    int n, i, time = 0;
    Process *processes;

    printf("Enter number of processes (max %d): ", MAX_PROCESSES);
    scanf("%d", &n);

    // Dynamically allocate memory for processes
    processes = (Process *) malloc(n * sizeof(Process));

    for (i = 0; i < n; i++) {
        processes[i].pid = i + 1;
        printf("Enter arrival time and burst time for process P%d: ", i + 1);
        scanf("%d %d", &processes[i].arrival_time, &processes[i].burst_time);
        processes[i].remaining_time = processes[i].burst_time;
        processes[i].queue_level = 1;  // Start in highest priority queue
        processes[i].completed = 0;
    }

    int completed_count = 0;
    while (completed_count < n) {
        int q1_count = 0, q2_count = 0, q3_count = 0;
        Process *q1[MAX_PROCESSES], *q2[MAX_PROCESSES], *q3[MAX_PROCESSES];

        for (i = 0; i < n; i++) {
            if (!processes[i].completed && processes[i].arrival_time <= time) {
                if (processes[i].queue_level == 1) q1[q1_count++] = &processes[i];
                else if (processes[i].queue_level == 2) q2[q2_count++] = &processes[i];
                else q3[q3_count++] = &processes[i];
            }
        }

        if (q1_count > 0) {
            roundRobin(q1, &q1_count, TIME_QUANTUM_Q1, &time);
        } else if (q2_count > 0) {
            roundRobin(q2, &q2_count, TIME_QUANTUM_Q2, &time);
        } else if (q3_count > 0) {
            fcfs(q3, &q3_count, &time);
        } else {
            time++; 
        }

        completed_count = 0;
        for (i = 0; i < n; i++) {
            if (processes[i].completed)
                completed_count++;
        }
    }

    printf("\nAll processes completed.\n");
    free(processes);
    return 0;
}


void roundRobin(Process *p[], int *n, int tq, int *time) {
    int i;
    for (i = 0; i < *n; i++) {
        if (p[i]->remaining_time > 0) {
            int exec_time = (p[i]->remaining_time > tq) ? tq : p[i]->remaining_time;
            printf("Time %d-%d: P%d (Q%d)\n", *time, *time + exec_time, p[i]->pid, p[i]->queue_level);
            p[i]->remaining_time -= exec_time;
            *time += exec_time;

            if (p[i]->remaining_time == 0) {
                p[i]->completed = 1;
                printf("P%d completed at time %d\n", p[i]->pid, *time);
            } else {
                p[i]->queue_level++;
                if (p[i]->queue_level > 3) p[i]->queue_level = 3;
            }
        }
    }
}


void fcfs(Process *p[], int *n, int *time) {
    int i;
    for (i = 0; i < *n; i++) {
        if (p[i]->remaining_time > 0) {
            printf("Time %d-%d: P%d (Q3)\n", *time, *time + p[i]->remaining_time, p[i]->pid);
            *time += p[i]->remaining_time;
            p[i]->remaining_time = 0;
            p[i]->completed = 1;
            printf("P%d completed at time %d\n", p[i]->pid, *time);
        }
    }
}
