class Solution:
    def resultArray(self, nums: List[int], k: int) -> List[int]:
        result = [0] * k
        count = [0] * k
        
        for num in nums:
            next_count = [0] * k
            val = num % k
            next_count[val] += 1
            
            for r in range(k):
                if count[r] > 0:
                    next_count[(r * val) % k] += count[r]
                    
            for r in range(k):
                result[r] += next_count[r]
                count[r] = next_count[r]
                
        return result