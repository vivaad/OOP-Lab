#include <unistd.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main() {
    char buf[1024];
    int bytes_read;
    int fd;
    char text[] = "ayman";
    int j = 0;
    int newline_count = 1;  
    int line_start = 0;

    fd = open("file.txt", O_RDONLY);
    if (fd < 0) {
        perror("open");
        exit(EXIT_FAILURE);
    }

    while ((bytes_read = read(fd, buf, sizeof(buf))) > 0) {
        for (int i = 0; i < bytes_read; i++) {
            if (buf[i] == '\n') {
                newline_count++;
                line_start = i + 1;
                j = 0;
            }

            if (buf[i] == text[j]) {
                j++;
                if (text[j] == '\0') {
                    printf("Found in Line %d\n", newline_count);
                    int len = i - line_start + 1;
                    write(1, buf + line_start, len); 
                    write(1, "\n", 1);
                    j = 0;
                }
            } else {
                j = (buf[i] == text[0]) ? 1 : 0;
            }
        }
    }

    if (bytes_read < 0) {
        perror("read");
    }

    close(fd);
    return 0;
}
