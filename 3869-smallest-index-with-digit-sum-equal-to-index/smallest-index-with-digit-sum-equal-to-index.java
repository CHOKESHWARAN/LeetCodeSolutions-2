class Solution {
    public int smallestIndex(int[] num) {
        for (int i = 0; i < num.length; i++) {
            int sum = 0;
            int temp = num[i]; 
            while (temp > 0) {
                sum += temp % 10;
                temp /= 10;
            } 
            if (i == sum) {
                return i;
            }
        } 
        return -1;
    }
}