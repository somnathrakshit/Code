#include <stdio.h>
#include <ctype.h>
#include "cs50.h"

int main(void)
{
    char arr[150];
    scanf("%s",&arr);
    int a,b,i,j,k,c,d;
    for(i=0;arr[i]!='\0';i++){
        
        if(isalnum(arr[i])){
            a=i;
            for(j=i+1;arr[j+1]!='\0';j++){
                if(arr[j+1]==' '){
                    if(isalnum(arr[j]))
                        b=j;
                    else
                        b=j-1;
                    break;
                }
            }
            for(k=b;k>=a;k--)
                printf("%c",arr[k]);
            if(b==j)
                printf(" ");
            else
                printf("%c ",arr[j]);
            a=j+2;
        }
    }
}

