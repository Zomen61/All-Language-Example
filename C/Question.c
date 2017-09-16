#include <stdio.h>

int main(){
    char c;
    char *inputStr,*dynaStringA,;
    int space = 100;
    int count = 0;


    dynaStringA = malloc(sizeof(char)*space);
    inputStr = dynaStringA;

    printf("¿é¤J¦r¦ê:");
    while((c=getchar()!='\n')){
        if(count > space){
            space = space*10;
            dynaStringA = malloc(sizeof(char)*space);

        }
        inputStr = c;
        inputStr++;
    }

    inputStr = malloc(sizeof(char));



}
