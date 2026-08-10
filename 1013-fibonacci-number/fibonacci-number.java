class Solution {
    public int fib(int n) {
        if(n<=0)
          return n;
        int i=1;
        int j=1;
        for(int k=3;k<=n;k++){
            int t=i+j;
            i=j;
            j=t;
        }
        return j;
    }
}