#include<stdio.h>
int main()
{
        int a, i = 0;
        float num, average;
        scanf("%d", &a);
        while(a > 0){
                i = i + 1;
                num = num + a;
                scanf("%d", &a);
        }
        average = num / i;
        printf("num = %.0f, average = %.2f\n", num, average);
        return 0;
}
