class Solution:
    def partitionArray(self, nums: list[int], k: int) -> int:
        nums.sort()
        
        count = 1
        min_val = nums[0]
        
        for num in nums:
            if num - min_val > k:
                count += 1
                min_val = num
                
        return count