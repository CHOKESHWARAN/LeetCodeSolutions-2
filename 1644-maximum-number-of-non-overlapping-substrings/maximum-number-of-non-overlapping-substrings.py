class Solution:
    def maxNumOfSubstrings(self, s: str) -> List[str]:
        first = {c: s.index(c) for c in set(s)}
        last = {c: s.rindex(c) for c in set(s)}
        
        intervals = []
        for c in set(s):
            l, r = first[c], last[c]
            valid = True
            j = l
            while j <= r:
                if first[s[j]] < l:
                    valid = False
                    break
                r = max(r, last[s[j]])
                j += 1
            
            if valid:
                intervals.append((r, l))
                
        intervals.sort()
        
        ans = []
        prev_end = -1
        for end, start in intervals:
            if start > prev_end:
                ans.append(s[start:end+1])
                prev_end = end
                
        return ans