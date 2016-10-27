#include<stdio.h>
#include<stdlib.h>
int cmpfunc (const void * a, const void * b)
{
   return ( *(int*)a - *(int*)b );
}
int main(){
	float arr[4];
	float x,y;
	int v=0;
	scanf("%f %f %f %f",&arr[0],&arr[1],&arr[2],&arr[3]);
	qsort(arr, 4, sizeof(float), cmpfunc);
	x=arr[0]/arr[1];
	y=arr[2]/arr[3];
	if(x==y)
		v=1;
	x=arr[0]/arr[2];
	y=arr[1]/arr[3];
	if(x==y)
		v=1;
	x=arr[0]/arr[3];
	y=arr[1]/arr[2];
	if(x==y)
		v=1;
	if(v==1)
		printf("Possible");
	else
		printf("Impossible");
	return 0;
}
	
