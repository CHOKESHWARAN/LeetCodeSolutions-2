class Solution:
    def smallestIndex(self, num: list[int]) -> int:
        for i, n in enumerate(num):
            temp = n
            digit_sum = 0
            
            while temp > 0:
                digit_sum += temp % 10
                temp //= 10
                
            if i == digit_sum:
                return i
                
        return -1