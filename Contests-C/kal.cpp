#include <bits/stdc++.h>
using namespace std;
#define lli long long int
#define pb push_back
#define mp make_pair
#define sd(a) scanf("%lld",&a)
#define pd(a) printf("%lld\n",a)
#define F first
#define S second
#define setl set<long long int>
#define sets set<string>
#define msetl multiset<long long int>
#define msets multiset<string>
#define vll vector<lli>
#define vstr vector<string>
#define pii pair<int , int>
#define SZ(x) (int)x.size()


// string s= to_string(int ); int to str

//int num =atoi(str_to_conv.c_str()); dont forget .c_str()

//***dont forget to clear the vector using v.clear();***

// for ceil indivision if int use k = x / y; if (k * y < x) ++k;

//for cin with white space cin.getline(var,sizeof(var)); with  char var[100];

int main()
{
    lli t,x,y,n,k,m,ans;
   sd(t);
   while(t--)
  {

	sd(n);
	sd(k);
	if(k==0)
    {
        for(x=1;x<=n;x++)
            cout<<x<<" ";
        cout<<endl;
        continue;
    }
	if( k>(n/2) )
    {
        cout<<-1<<endl;
        continue;
    }
	vll v;
	x=0;m=2*k;
	while( (x)+ m <= n)
    {

        for(y=x+k+1;y<= ( x+(m) ) ;y++)
            v.pb(y);
        for(y=x+1;y<=x+k;y++)
        {
            v.pb(y);
        }
        x+=m;
       // cout<<x<<endl;
    }

    vll b;
   // x-=m;
   // cout<<x<<endl;
    for(y=(x)+1;y<=n;y++)
    {
        b.pb(y);
      //  cout<<y<<endl;

    }

    x=SZ(v);
    x--;
    while(SZ(b) < (2*k))
    {
        b.pb(v[x]);
        v.erase(v.begin()+x);
        x--;
    }
    sort(b.begin(),b.end());
    for(x=k;x<(2*k);x++)
        v.pb(b[x]);
    for(x=0;x<k;x++)
        v.pb(b[x]);
    for(x=0;x<n;x++)
        cout<<v[x]<<" ";
    cout<<endl;

  }
    return 0;

}

