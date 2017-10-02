#include <stdio.h>

typedef struct node {

    char name[20];
    int status;
    char peoName[10];
    char phone[15];
    char reservation[20];
    struct node *next;

} NODE;

typedef struct list {
    int count;
    struct node *first;

} LIST;

int openFileLoadingData(LIST *listHead);
LIST *CreateList();
NODE *CreateNode(char name[],char status[],char peoName[],char phone[],char reservation[]);
void print(LIST *printlist,int type);
void insertNode( LIST *insertlist, NODE *insertnode );
LIST *sortLinkedList(LIST *needSortLink);
void insertSortNode(LIST *CreateList,NODE *);
NODE *searchNode(LIST *sourceList,char BookName[]);
int deleteNode(LIST *deletelist,char bookName[]);

void chooseBook(LIST *bookList,int type);
int reserveBook(LIST *bookList,char bookName[],char peopleName[]);
int backBook(LIST *bookList,char bookName[]);
int borrowBook(LIST *bookList,char bookName[],char peopleName[]);


int main() {

    char tempC;
    char tempIns[50];

    int bookNumber=0;
    int i=0,count = 0;

    LIST *listHead = CreateList();


    while(1) {
        printf("請輸入指令:");
        fgets(tempIns,50,stdin);
        if(strcmp(tempIns,"Load File\n")== 0) {
            openFileLoadingData(listHead);
        } else if(strcmp(tempIns,"Exit\n")== 0) {
            break;
        } else if(strcmp(tempIns,"Sort\n")== 0) {
            LIST *tempList = sortLinkedList(listHead);

            listHead = tempList;
            print(listHead,4);
        }else if(strcmp(tempIns,"Print\n")== 0){
            print(listHead,4);
        }else{
            printf("指令有誤。\n");
        }

    }

    //Delete
    /*deleteNode(listHead,"Java");
    print(listHead);*/



}
//Open File
int openFileLoadingData(LIST *listHead) {
    char String[1000];
    char tempS[100];
    char *tempsubStr[5];
    const char *del = ",";

    FILE *fp_r = fopen("library.txt", "r");

    if (fp_r == NULL) {
        return -1;
    }

    while(!feof(fp_r)) {
        int i = 0;
        char *tempStr;

        fgets(String,1000,fp_r);
        tempStr = strtok(String,del);

        while(tempStr != NULL) {
            tempsubStr[i] = tempStr;
            tempStr = strtok (NULL, del);
            i++;
        }

        insertNode(listHead,CreateNode(tempsubStr[0],tempsubStr[1],tempsubStr[2],tempsubStr[3],tempsubStr[4]));
    }

    print(listHead,4);

    fclose(fp_r);

    return 1;
}

//LIST Basic Function
LIST *CreateList() {
    LIST *Createlist = (LIST*)malloc(sizeof(LIST));

    Createlist->count = 0;
    Createlist->first = NULL;
    return Createlist;
}

NODE *CreateNode(char name[],char status[],char peoName[],char phone[],char reservation[]) {

    NODE *Createnode = (NODE*)malloc(sizeof(NODE));

    strcpy(Createnode->name,name);

    if(strcmp(status,"On") == 0) {
        Createnode->status = 1;
    } else {
        Createnode->status = 0;
    }
    strcpy(Createnode->peoName,peoName);
    strcpy(Createnode->phone,phone);
    strcpy(Createnode->reservation,reservation);
    Createnode->next = NULL;

    //printf("%d\t%s\t%p\n",Createnode->id,Createnode->name,Createnode->next);
    return Createnode;
}

void insertNode( LIST *insertlist, NODE *insertnode ) {
    NODE *tempnode;
    if ( insertlist->count == 0) {
        insertlist->first = insertnode;
    } else {
        tempnode = insertlist->first;
        while (tempnode->next != NULL) {
            tempnode=tempnode->next;
        }
        tempnode->next = insertnode;
    }
    (insertlist->count)++;
}

LIST *sortLinkedList(LIST *needSortLink) {
    LIST *sortedList = CreateList();
    while(needSortLink->first != NULL) {
        NODE *minNode = needSortLink->first;
        NODE *tempNode = needSortLink->first;

        while(tempNode->next != NULL) {
            if(strcmp(minNode->name,tempNode->name) > 0) {
                minNode = tempNode;
            }
            tempNode = tempNode->next;
        }

        insertNode(sortedList,minNode);
        deleteNode(needSortLink,minNode->name);
    }

    return sortedList;
}

//有問題
/*void insertSortNode( LIST *insertlist, NODE *insertnode ) {
    NODE *prenode;
    NODE *tempnode;

    tempnode = insertlist->first;
    if ( insertlist->count == 0) {
        insertlist->first = insertnode;
        printf("1");
    } else if(insertlist->count == 1) {
        if(strcmp(tempnode->name,insertnode->name) > 0) {
            insertlist->first = insertnode;
            insertnode->next = tempnode;
        } else {
            tempnode->next = insertnode;
        }
        printf("2");
    } else {
        printf("3");
        if(strcmp(tempnode->name,insertnode->name) > 0) {
            insertlist->first = insertnode;
            insertnode->next = tempnode;
        }
        prenode = tempnode;
        tempnode = tempnode->next;
        while (tempnode->next != NULL) {
               // printf("*");
            if(strcmp(tempnode->name,insertnode->name) > 0) {
                printf("Y");
                prenode->next = insertnode;
                insertnode->next = tempnode;
                break;
            } else {
                printf("N");
                prenode = tempnode;
                tempnode = tempnode->next;
            }
        }
        if(tempnode->next == NULL) {
            tempnode->next = insertnode;
        }
    }
    (insertlist->count)++;
}*/


void print(LIST *printlist,int type) {
    NODE *tempnode = printlist->first;
    char *status;
    int count = 1;

    while ( tempnode != NULL) {
        switch (type) {
        case 0:
            if(tempnode->status) {
                status = "On";
            } else {
                status = "Off";
            }
            printf(" NO.%d:\n 書名:%s\n   書本狀態:%s\n",count,tempnode->name,status);
            break;
        case 1:
            printf(" NO.%d:\n 書名:%s\n   租借日期:%s\n",count,tempnode->name,tempnode->peoName);
            break;
        case 2:
            printf(" NO.%d:\n 書名:%s\n   租借人手機:%s\n",count,tempnode->name,tempnode->phone);
            break;
        case 3:
            printf(" NO.%d:\n 書名:%s\n   預約人名:%s\n",count,tempnode->name,tempnode->reservation);
            break;
        default:
            if(tempnode->status) {
                status = "On";
            } else {
                status = "Off";
            }
            printf(" NO.%d:\n 書名:%s\n 書本狀態:%s\n 租借日期:%s\n 租借人手機:%s\n 預約人名:%s\n",count,tempnode->name,status,tempnode->peoName,tempnode->phone,tempnode->reservation);
        }

        tempnode = tempnode->next;
        count++;
    }
    printf("\n");

}

NODE *searchNode(LIST *sourceList,char BookName[]) {
    NODE *tempnode;

    tempnode = sourceList->first;

    while ( tempnode != NULL) {
        if(strcmp(tempnode->name,BookName) == 0) {
            break;
        }
        tempnode = tempnode->next;
    }

    /*if(tempnode != NULL){
        printf("尋找書名:%s\n",tempnode->name);
    }else{
        printf("找不到此書\n");
    }*/

    return tempnode;
}


int deleteNode(LIST *deletelist,char bookName[]) {
    NODE *preDelNode;
    NODE *willDelNode;
    NODE *nextDelNode;

    willDelNode = deletelist->first;
    nextDelNode = willDelNode->next;

    if(strcmp(willDelNode->name,bookName)==0) {
        deletelist->first = nextDelNode;
        willDelNode->next = NULL;
        //free(willDelNode);
        deletelist->count--;
        return 1;
    } else {
        while ( willDelNode->next != NULL) {
            preDelNode = willDelNode;
            willDelNode = nextDelNode;
            nextDelNode = willDelNode->next;
            if(strcmp(willDelNode->name,bookName) == 0) {
                preDelNode->next = nextDelNode;
                willDelNode->next = NULL;
                //free(willDelNode);
                deletelist->count--;
                return 1;
            }

        }
        printf("\n End");
    }
    return -1;
}

//Library Function

int borrowBook(LIST *bookList,char bookName[],char peopleName[]) {
    NODE *tempNode = searchNode(bookList,bookName);
    if (tempNode == NULL) {
        printf("找不到此書");
        return 0;
    }
    if(tempNode->peoName == '-') {
        tempNode->status = 0;
        strcpy(tempNode->peoName,peopleName);
    } else {
        printf("此書已被借出");
    }
    return 1;
}

int backBook(LIST *bookList,char bookName[]) {
    NODE *tempNode = searchNode(bookList,bookName);
    if (tempNode == NULL) {
        printf("找不到此書");
        return 0;
    }
    if(tempNode->peoName == '-') {
        printf("此書未被借出，是在還三小!!");
    } else {
        tempNode->status = 1;
        strcpy(tempNode->peoName,"-");
    }
    return 1;
}

int reserveBook(LIST *bookList,char bookName[],char peopleName[]) {
    NODE *tempNode = searchNode(bookList,bookName);
    if (tempNode == NULL) {
        printf("找不到此書");
        return 0;
    }

    if(tempNode->peoName == '-') {
        printf("此書未借出，請直接借書好嗎?");
    } else {
        strcpy(tempNode->reservation,peopleName);
    }
    return 1;
}

void chooseBook(LIST *bookList,int type) {
    //type 0:book status
    //type 1:Person Information
    //type 2:Person Cellphone
    //type 3:Reserve Book
    //type 4:All
    print(bookList,type);
}
