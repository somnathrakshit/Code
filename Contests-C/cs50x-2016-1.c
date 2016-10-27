#include <stdio.h>
#include<string.h>

int main(void)
{
    char arr[2000];
    int i;
    gets(arr);
    for(i=0;arr[i]!='\0';i++){
        if( arr[i]=='.'||arr[i]==','){
            
            printf("%c ",arr[i]);
        	
        	if(arr[i+1]!='\0'&&arr[i]=='.')
        		arr[i+1]-=32;
        }
        else
        	printf("%c",arr[i]);        
    }
}

