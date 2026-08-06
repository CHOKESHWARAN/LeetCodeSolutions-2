class Solution {
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        
        int count = 1;
        int minVal = nums[0];
        
        for (int num : nums) {
            if (num - minVal > k) {
                count++;
                minVal = num;
            }
        }
        
        return count;
    }
}