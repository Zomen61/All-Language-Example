#include <stdio.h>

int main() {
    char input[100] = "";
    char temp[100] = "";
    char tempC = 'a';
    int number = 0;
    int countC = 0;
    int bools = 0;
    int countN = 0;
    int countT = 0;

    scanf("%d",&number);

    input[0]='\0';

    while(countN <= number){
        while((tempC=getchar())!='\n'){
            if(countC > 99){
                printf("Error");
            }

            if((tempC>64 && tempC<91 )){
                if(bools){
                    strcat(input,temp);
                    for(int i=0;i<100;i++ ){
                        temp[i]='\0';
                    }
                    countT = 0;
                    bools = 0;
                }
                temp[countT] = tempC;
                countT++;
            }else if (tempC>47 && tempC<58){
                int len = tempC - '0';
                for(int i=0;i < countT;i++){
                    temp[i] = temp[i]+len;
                    if(temp[i]>'Z'){
                        temp[i] = temp[i] - 'Z' + 'A' - 1;
                    }
                }

                bools = 1;
            }

        }
        if(temp[0]!='\0'){
            strcat(input,temp);
        }
        printf("%s",input);

        countC = 0;

        input[0] = '\0';
        countN++;
        printf("\n");
    }


    printf("End!!");


}
