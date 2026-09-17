class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] best = new int[n];
        Arrays.fill(best, Integer.MAX_VALUE);
        int minSum = Integer.MAX_VALUE;
        int left = 0;
        int currentSum = 0;
        
        for (int right = 0; right < n; right++) {
            currentSum += arr[right];
            
            while (currentSum > target && left <= right) {
                currentSum -= arr[left];
                left++;
            }
            
            if (currentSum == target) {
                int l = right - left + 1;
                if (left > 0 && best[left - 1] != Integer.MAX_VALUE) {
                    minSum = Math.min(minSum, l + best[left - 1]);
                }
                best[right] = (right > 0) ? Math.min(best[right - 1], l) : l;
            } else {
                if (right > 0) {
                    best[right] = best[right - 1];
                }
            }
        }
        
        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}