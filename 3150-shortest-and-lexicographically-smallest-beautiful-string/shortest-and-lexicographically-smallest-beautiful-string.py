class Solution:
    def shortestBeautifulSubstring(self, s: str, k: int) -> str:
        n = len(s)
        count = 0
        left = 0
        ans = ""

        for right in range(n):
            if s[right] == '1':
                count += 1

            while count == k: 
                while s[left] == '0':
                    left += 1

                current = s[left : right + 1] 
                if not ans or len(current) < len(ans) or (len(current) == len(ans) and current < ans):
                    ans = current 
                count -= 1
                left += 1

        return ans