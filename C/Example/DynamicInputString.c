#include <stdlib.h>
#include <stdio.h>

int main(){
    int space = 5;
    int count = 0;
    char *Str;
    char c;
    Str = calloc(space,sizeof(char));

    while((c=getchar())!='\n'){

        if(count == space - 1){
            char *temp;
            temp = calloc(space,sizeof(char));
            strcat(temp,Str);

            space = space*10;
            Str = calloc(space,sizeof(char));
            strcat(Str,temp);

            free(temp);
        }
        printf("%p\n",*Str);

        Str[count] = c;
        count++;
    }

    printf("¦r¦ê³á:%s\n",Str);

    printf("Space:%d",space);


    free(space);
}
