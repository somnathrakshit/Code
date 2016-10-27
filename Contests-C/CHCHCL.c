#include <stdio.h>

int main() {
	long t,a,b,c;
	scanf("%ld",&t);
	while(t--){
	    scanf("%ld %ld",&a,&b);
	    a%=10;
	    b%=10;
	    c=a*b;
	    if(c%2==0)
	        printf("Yes\n");
	    else
	        printf("No\n");
	}
	return 0;
}
