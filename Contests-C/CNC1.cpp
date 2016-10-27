#include <iostream>
using namespace std;

int maxSubArraySum(int a[], int size)
{
    int max_so_far = 0, max_ending_here = 0;
    for (int i = 0; i < size; i++) {
        max_ending_here = max_ending_here + a[i];
        if (max_ending_here < 0)
            max_ending_here = 0;
        else if (max_so_far < max_ending_here)
            max_so_far = max_ending_here;
    }
    return max_so_far;
}
int main()
{
    int t, i, n;
    cin >> t;
    while (t--) {
        cin >> n;
        int* a = (int*)malloc(n * sizeof(int));
        for (i = 0; i < n; i++)
            cin >> a[i];
        int max_sum = maxSubArraySum(a, n);
        cout << max_sum << endl;
    }
    return 0;
}
