#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct node {

    int id;
    char name[20];

    struct node *next;

} NODE;

typedef struct list {

    int count;
    struct node *first;

} LIST;


NODE *CreateNode(int id ,char name[]);
LIST *CreateList();
void insertNode(LIST *CreateList,NODE *);
void print(LIST *printlist);
int deleteNode(LIST *deletelist ,int num);
NODE *searchNode(LIST *sourceList,int searchID);

int del;

int main(void) {

    LIST *sss = CreateList();
    NODE *qqq = CreateNode(9,"123456789");
    NODE *fff = CreateNode(11,"9");
    NODE *hhh = CreateNode(12,"2");
    NODE *ggg = CreateNode(13,"5");
    NODE *eee = CreateNode(10,"1256569");

    //printf("%d",4);
    insertNode( sss, qqq );
    //printf("%d",45);
    insertNode( sss, fff );
    insertNode( sss, hhh );
    insertNode( sss, eee );
    insertNode( sss, ggg );

    NODE *tempNode = searchNode(sss,10);
    printf("ID:%d name:%s\n",tempNode->id,tempNode->name);


    //deleteNode(sss, 5);

    print(sss);

}

NODE *CreateNode(int id ,char name[]) {

    NODE *Createnode = (NODE*)malloc(sizeof(NODE));
    Createnode->id = id;
    strcpy(Createnode->name,name);
    Createnode->next = NULL;

    //printf("%d\t%s\t%p\n",Createnode->id,Createnode->name,Createnode->next);
    return Createnode;
}

LIST *CreateList() {

    LIST *Createlist = (LIST*)malloc(sizeof(LIST));

    Createlist->count = 0;
    Createlist->first = NULL;
    return Createlist;
}

void insertNode( LIST *insertlist, NODE *insertnode ) {

    NODE *tempnode/* = (NODE*)malloc(sizeof(NODE))*/;

    tempnode = insertlist->first;
    if ( insertlist->count == 0) {
         insertlist->first = insertnode;
        //printf("%d",6987);
    } else {

        while ( tempnode->next != NULL ) {
            tempnode = tempnode->next;
        }
        tempnode->next = insertnode;
    }
    (insertlist->count)++;

}

void print(LIST *printlist){

    NODE *tempnode/* = (NODE*)malloc(sizeof(NODE))*/;

    tempnode = printlist->first;

    while ( tempnode != NULL) {
        printf("%d\t%s\n",tempnode->id,tempnode->name);
        tempnode = tempnode->next;

    }

}

int deleteNode(LIST *deletelist ,int num) {
    NODE *tempnode = (NODE*)malloc(sizeof(NODE));
    NODE *deletenode = (NODE*)malloc(sizeof(NODE));
    NODE *upnode = (NODE*)malloc(sizeof(NODE));


    tempnode = deletelist->first->next;
    deletenode = deletelist->first;

    if (num <=0 || num > (deletelist->count)) {
        return 0;
    } /*else if (num == 1) {
        free(tempnode);
        (deletelist->count)--;
    } */else {

        for (del = 1 ; del <= num ; del++) {
            if (del == num && del !=1) {
                upnode->next = tempnode;
                free(deletenode);
                (deletelist->count)--;
                return 1;
            } else if (del == 1 && del == num){
                deletelist->first = tempnode;
                free(deletenode);
                (deletelist->count)--;
                return 1;
            } else {
                upnode = deletenode;
                deletenode = tempnode;
                tempnode = tempnode->next;
            }
        }
    }
}


NODE *searchNode(LIST *sourceList,int searchType,char searchText){
    NODE *tempnode;

    tempnode = sourceList->first;

    while ( tempnode != NULL) {
        switch (searchType){
            case 0:
                if(strcmp(tempnode->name,searchText){
                    return tempnode;
                }
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }

        tempnode = tempnode->next;
    }

    return NULL;
}
