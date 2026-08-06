class Solution {
    public int smallestNumber(int n, int t) {
        int i = n;
        int j = 0;
        
        while (getDigitProduct(i) % t != 0) {
            i++;
            j++;
        }
        
        return n + j;
    }

    private int getDigitProduct(int num) {
        int prod = 1;
        while (num > 0) {
            prod *= (num % 10);
            num /= 10;
        }
        return prod;
    }
}