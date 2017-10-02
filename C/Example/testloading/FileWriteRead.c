#include <stdio.h>

int main() {
    FILE *RF,*WF;

    int data[100];
    int temp=0;
    RF=fopen("input.txt","r");
    WF=fopen("output.txt","w");
    int sum=0,count=0;



    for(int a=0;a<100;a++){
        fscanf(RF ,"%d" , &data[a]);
    }

    for(int b=0;b<100;b++){
        for(int a=0;a<99;a++){
            if(data[a]>data[a+1]){
                temp = data[a];
                data[a] = data[a+1];
                data[a+1] = temp;
            }
        }
    }
    for(int a=0;a<100;a++){
        sum += data[a];
        count = a;
    }


    fprintf(WF, "min:%d\n",data[0]);
    fprintf(WF, "max:%d\n",data[99]);
    fprintf(WF, "amount:%d\n",100);
    fprintf(WF, "avg:%d\n",sum/100);
    for(int a=0;a<100;a++){
        fprintf(WF, "%d\n",data[a]);
    }

    fclose(RF);
    fclose(WF);

}
