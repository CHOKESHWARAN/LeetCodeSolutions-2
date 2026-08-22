class Solution {
    public boolean checkDivisibility(int n) {
        int sum=0;
        int p=1;
        int g=n;
        while(n>=1){
            int t=n%10;
            p*=t;
            sum+=t;
            n=n/10;

        }
    if(g%(p+sum)==0)
           return true;
        return false;
    }
}