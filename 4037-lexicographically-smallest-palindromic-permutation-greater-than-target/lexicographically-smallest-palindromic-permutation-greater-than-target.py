class Solution:
    def lexPalindromicPermutation(self, s: str, target: str) -> str:
        n = len(s)
        count = [0] * 26
        for char in s:
            count[ord(char) - ord('a')] += 1

        odd_count = 0
        mid_char = -1
        for i in range(26):
            if count[i] % 2 != 0:
                odd_count += 1
                mid_char = i

        if odd_count > 1:
            return ""

        half_len = n // 2
        half_count = [count[i] // 2 for i in range(26)]

        for i in range(half_len, -1, -1):
            current_half_count = half_count.copy()
            valid_prefix = True
            pref = [''] * half_len

            for k in range(i):
                c = ord(target[k]) - ord('a')
                if current_half_count[c] > 0:
                    current_half_count[c] -= 1
                    pref[k] = target[k]
                else:
                    valid_prefix = False
                    break

            if not valid_prefix:
                continue

            if i == half_len:
                if n % 2 != 0:
                    t_mid = target[half_len]
                    if mid_char >= 0 and chr(mid_char + ord('a')) >= t_mid:
                        candidate = self._construct_palindrome(pref, chr(mid_char + ord('a')), current_half_count, n)
                        if candidate > target:
                            return candidate
                else:
                    candidate = self._construct_palindrome(pref, '', current_half_count, n)
                    if candidate > target:
                        return candidate
                continue

            t_char = target[i]
            for next_char in range(ord(t_char) - ord('a') + 1, 26):
                if current_half_count[next_char] > 0:
                    next_half_count = current_half_count.copy()
                    next_half_count[next_char] -= 1
                    pref[i] = chr(next_char + ord('a'))

                    pos = i + 1
                    for c in range(26):
                        while next_half_count[c] > 0:
                            pref[pos] = chr(c + ord('a'))
                            pos += 1
                            next_half_count[c] -= 1

                    mid = chr(mid_char + ord('a')) if n % 2 != 0 else ''
                    candidate = self._build_full_palindrome(pref, mid, n)
                    if candidate > target:
                        return candidate

        return ""

    def _construct_palindrome(self, pref: list, mid: str, remaining_half_count: list, n: int) -> str:
        full_pref = pref.copy()
        pos = len(pref) - remaining_half_count.count(0) # place at end
        pos = len(pref) - sum(remaining_half_count)
        for c in range(26):
            while remaining_half_count[c] > 0:
                full_pref[pos] = chr(c + ord('a'))
                pos += 1
                remaining_half_count[c] -= 1
        return self._build_full_palindrome(full_pref, mid, n)

    def _build_full_palindrome(self, half: list, mid: str, n: int) -> str:
        half_str = "".join(half)
        return half_str + mid + half_str[::-1]