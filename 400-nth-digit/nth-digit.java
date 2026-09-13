class Solution {
    public int findNthDigit(int n) {
        long length = 1;
        long count = 9;
        long start = 1;
 
        while (n > length * count) {
            n -= length * count;
            length++;
            count *= 10;
            start *= 10;
        }
 
        start += (n - 1) / length; 
        String s = Long.toString(start);
        return Character.getNumericValue(s.charAt((int)((n - 1) % length)));
    }
}