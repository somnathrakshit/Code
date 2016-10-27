#include <iostream>
#include<algorithm>
using namespace std;

int main() {
    int test;
    cin>> test;
    while(test--)
    {
        int n,i;
        cin >> n;
        long long int arr[n];
        for(i=0;i<n;i++)
            cin >> arr[i];
        sort(arr,arr+n);
        int c1=0,c0=0,c_1=0,d=0;
        for(i=0;i<n;i++)
        {
            if(arr[i]==0)
                c0++;
            else if(arr[i]==1)
                c1++;
            else if (arr[i]==-1)
                c_1++;
            else
                d++;
        }
        if(c_1>0)
        {
            if(d==0 && c1>0)
                cout << "yes" << endl;
            else
                cout << "no" << endl;
        }
        else
        {
            if(d==0)
            {
                if((c0+c1)==n)
                    cout <<"yes" << endl;
                else
                    cout << "no" << endl;
            }
            else
            {
                if((c0+c1)==(n-1))
                    cout <<"yes" << endl;
                else
                    cout << "no" << endl;
            }
        }

    }
    return 0;
}