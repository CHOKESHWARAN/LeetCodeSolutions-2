class Solution:
    def maxPalindromes(self, s: str, k: int) -> int:
        n = len(s)
        count = 0
        i = 0

        def is_palindrome(l: int, r: int) -> bool:
            if r >= n:
                return False
            while l < r:
                if s[l] != s[r]:
                    return False
                l += 1
                r -= 1
            return True

        while i < n:
            found = False
            for j in range(i, n):
                if is_palindrome(j, j + k - 1):
                    count += 1
                    i = j + k
                    found = True
                    break
                if is_palindrome(j, j + k):
                    count += 1
                    i = j + k + 1
                    found = True
                    break
            if not found:
                break

        return count