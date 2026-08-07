class Solution {
    public int arrangeCoins(int n) {
     int i=1;
     int j=0;
     while(n>0){
        n=n-i;
        if(n<0)
           return j;

        i++;
        j++;
     }   
     return j;
    }
}