class Solution:
    def findKthSmallest(self, coins: list[int], k: int) -> int:
        n = len(coins)
        
        def count(target: int) -> int:
            cnt = 0
            for i in range(1, 1 << n):
                lcm_val = 1
                bits = 0
                for j in range(n):
                    if (i >> j) & 1:
                        bits += 1
                        lcm_val = math.lcm(lcm_val, coins[j])
                        if lcm_val > target:
                            break
                else:
                    if bits % 2 == 1:
                        cnt += target // lcm_val
                    else:
                        cnt -= target // lcm_val
            return cnt

        low, high = 1, min(coins) * k
        ans = high
        while low <= high:
            mid = (low + high) // 2
            if count(mid) >= k:
                ans = mid
                high = mid - 1
            else:
                low = mid + 1
        return ans