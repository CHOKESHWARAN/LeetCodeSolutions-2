class Solution:
    def minSumOfLengths(self, arr: list[int], target: int) -> int:
        n = len(arr)
        best = [float('inf')] * n
        min_sum = float('inf')
        left = 0
        current_sum = 0
        
        for right in range(n):
            current_sum += arr[right]
            
            while current_sum > target and left <= right:
                current_sum -= arr[left]
                left += 1
                
            if current_sum == target:
                l = right - left + 1
                if left > 0 and best[left - 1] != float('inf'):
                    min_sum = min(min_sum, l + best[left - 1])
                best[right] = min(best[right - 1] if right > 0 else float('inf'), l)
            else:
                if right > 0:
                    best[right] = best[right - 1]
                    
        return min_sum if min_sum != float('inf') else -1