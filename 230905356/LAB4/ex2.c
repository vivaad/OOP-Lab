#include <unistd.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main() {
    int fd = open("file.txt", O_RDONLY);
    if (fd < 0) {
        perror("open");
        return 1;
    }

    char buf[1024];
    char line_buf[2048];
    int bytes_read;
    int line_count = 1;
    int line_pos = 0;

    while ((bytes_read = read(fd, buf, sizeof(buf))) > 0) {
        for (int i = 0; i < bytes_read; i++) {
            if (buf[i] == '\n') {
                if (line_count == 20) {
                    line_buf[line_pos] = '\0';
                    write(1, line_buf, line_pos);
                    write(1, "\n", 1);
                    close(fd);
                    return 0;
                }
                line_count++;
                line_pos = 0; 
            } else {
                if (line_count == 20) {
                    line_buf[line_pos++] = buf[i];
                }
            }
        }
    }

    if (bytes_read < 0) {
        perror("read");
    }

    close(fd);
    return 0;
}
