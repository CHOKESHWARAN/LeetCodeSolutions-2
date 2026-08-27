class Solution:
    def lexGreaterPermutation(self, s: str, target: str) -> str:
        n = len(s)
        count = [0] * 26
        for c in s:
            count[ord(c) - 97] += 1
        
        prefix_count = count.copy()
        match_len = 0
        while match_len < n and prefix_count[ord(target[match_len]) - 97] > 0:
            prefix_count[ord(target[match_len]) - 97] -= 1
            match_len += 1
        
        for i in range(match_len, -1, -1):
            if i < match_len:
                prefix_count[ord(target[i]) - 97] += 1
            if i == n:
                continue
            
            target_char = ord(target[i]) - 97
            for c in range(target_char + 1, 26):
                if prefix_count[c] > 0:
                    res = [target[:i], chr(97 + c)]
                    prefix_count[c] -= 1
                    for ch in range(26):
                        if prefix_count[ch] > 0:
                            res.append(chr(97 + ch) * prefix_count[ch])
                    return "".join(res)
        
        return ""