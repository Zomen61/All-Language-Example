#include <stdio.h>
#include <string.h>

int main(){
    char a[] = "ADFF";
    char b[] = "ACDD";
    char c[] = "BTF";
    char d[] = "CTES DFM";
    char e[] = "CTES DFM";


    printf("%d\n",strcmp(a,b));
    printf("%d\n",strcmp(b,d));
    printf("%d\n",strcmp(c,d));
    printf("%d\n",strcmp(d,e));

    return 0;
}
