class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] count = new long[k];
        
        for (int num : nums) {
            long[] nextCount = new long[k];
            int val = num % k;
            nextCount[val]++;
            
            for (int r = 0; r < k; r++) {
                if (count[r] > 0) {
                    nextCount[(r * val) % k] += count[r];
                }
            }
            
            for (int r = 0; r < k; r++) {
                result[r] += nextCount[r];
                count[r] = nextCount[r];
            }
        }
        
        return result;
    }
}