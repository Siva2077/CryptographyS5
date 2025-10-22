#include <stdio.h>
#include <stdint.h>

int pow_mod(int b, int e, int m) {
    int x = 1;
    b %= m;
    while (e > 0) {
        if (e & 1) {
            x = (x * b) % m;
        }
        b = (b * b) % m;
        e >>= 1;
    }
    return x;
}

typedef struct {
    int n;
    int e;
    int d;
} RSA;

void rsa_init(RSA *x) {
    int p = 17, q = 19;
    x->n = p * q;            // n = 323
    int phi = (p - 1) * (q - 1); // phi = 288
    x->e = 7;
    x->d = 103;
}

int rsa_enc(RSA *x, int m) {
    return pow_mod(m, x->e, x->n);
}

int rsa_dec(RSA *x, int c) {
    return pow_mod(c, x->d, x->n);
}

int main() {
    RSA x;
    rsa_init(&x);

    int m;
    printf("Enter the message code: ");
    scanf("%d", &m);

    int c = rsa_enc(&x, m);
    printf("Enc: %d\n", c);

    int d = rsa_dec(&x, c);
    printf("Dec: %d\n", d);

    return 0;
}

