#include<iostream>

using namespace std;

int pow_f(long long b, long long num, long long m) {
    if (num == 0)
        return 1;
    if (num == 1)
        return b;
    long long hn = pow_f(b, num / 2, m);
    if (num % 2 == 0)
        return (hn * hn) % m;
    else
        return (((hn * hn) % m) * b) % m;
}

int fermat(int num, int m) {
    return pow_f(num, m - 2, m);
}

int main() {
    long long f[100001];
    f[0] = 1;
    int i = 1;
    int MOD = 1000000007;
    while (i <= 100000) {
        f[i] = (f[i - 1] * i) % MOD;
        i++;
    }

    int n, k;

    cin>>n>>k;
    k = k % 2 ? k - 1 : k;

    long long num, den, min_den, ans = 0;
    for (i = 0; i <= k; i = i + 2) {
        num = f[n];
        den = (f[i] * f[n - i]) % MOD;
        min_den = fermat(den, MOD);
        ans = ans + (num * min_den) % MOD;

    }
    cout<<ans;
    return 0;
}