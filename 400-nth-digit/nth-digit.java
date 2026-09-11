class Solution {
    public int findNthDigit(int n) {
        long length = 1;
        long count = 9;
        long start = 1;

        // Step 1: Find the digit group length
        while (n > length * count) {
            n -= length * count;
            length++;
            count *= 10;
            start *= 10;
        }

        // Step 2: Find the exact number
        start += (n - 1) / length;

        // Step 3: Extract the specific digit
        String s = Long.toString(start);
        return Character.getNumericValue(s.charAt((int)((n - 1) % length)));
    }
}