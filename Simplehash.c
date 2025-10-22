#include <stdio.h>

unsigned long hash(char *str) {
    unsigned long h = 5381;
    int c;
    while ((c = *str++))
        h = ((h << 5) + h) + c; // h * 33 + c
    return h;
}

int main() {
    char msg[256];
    printf("Enter message: ");
    fgets(msg, sizeof(msg), stdin);

    // remove newline
    for (int i = 0; msg[i]; i++)
        if (msg[i] == '\n') msg[i] = 0;

    unsigned long result = hash(msg);
    printf("Hash value: %lu\n", result);
    return 0;
}
