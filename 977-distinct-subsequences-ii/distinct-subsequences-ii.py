class Solution:
    def distinctSubseqII(self, s: str) -> int:
        last = [0] * 26
        MOD = 10**9 + 7
        for c in s:
            last[ord(c) - ord('a')] = (sum(last) + 1) % MOD
        return sum(last) % MOD