#include <stdio.h>

int main(){
    char pos[8][14] =
    {
        {"1234567890-=  "},
        {"!@#$%^&*()_+  "},
        {"qwertyuiop[]\ "},
        {"asdfghjkl;'   "},
        {"zxcvbnm,./    "},
        {"QWERTYUIOP{}| "},
        {"ASDFGHJKL:""  "},
        {"ZXCVBNM<>?    "},
    };
    char **input;
    char c='A';
    int n=0;
    int space=10;
    int count = 1;
    int length = 0;
    scanf("%d\n",&n);

    input = malloc(sizeof(char*)*n);

    for(int i=0;i < n;i++){
        *(input+i) = malloc(sizeof(char)*space);
        count = 0;
        while((c=getchar())!='\n'){
            *(*(input+i)+count) = c;
            count++;
        }
        *(*(input+i)+count) = '\n';
    }

       for(int i=0;i<n;i++){
        /*for(int j=0;j< length(input[i]);j++){

        }*/
         printf("輸入第%d字串:",i+1);
         count = 0;
         while((c=*(*(input+i)+count)) != '\n'){
            printf("%c",*(*(input+i)+count));
            count++;
         }
         printf("\n");
    }

    for(int i=0;i<n;i++){
        /*for(int j=0;j< length(input[i]);j++){

        }*/
         printf("變更第%d字串:",i+1);
         count = 0;
         while((c=*(*(input+i)+count)) != '\n'){

            for(int k = 0;k<8;k++){
                length = sizeof(pos[k]) / sizeof(pos[k][0]);
                for(int j=0;j<length;j++){
                    if(c==pos[k][j]){
                        if((j<length-1)){
                            if(pos[k][j+1]!=' '){
                                c = pos[k][j+1];
                                *(*(input+i)+count) = c;
                                break;
                            }
                        }
                    }
                }
            }
            printf("%c",*(*(input+i)+count));
            count++;
         }
         printf("\n");
    }
}
