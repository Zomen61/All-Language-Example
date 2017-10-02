#include<stdio.h>
#include<stdlib.h>

int main() {
    FILE *fp_r = fopen("library.txt", "r");

    char String[1000];
    int i = 1;

    if (fp_r == NULL) {
        return -1;
    }

    while(!feof(fp_r)) {
        fgets(String, 1000, fp_r);
        printf("test_%d::%s\n", i, String);
        i++;
    }

    system("pause");
    return 0;
}

