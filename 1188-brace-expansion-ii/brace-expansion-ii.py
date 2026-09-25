class Solution:
    def braceExpansionII(self, expression):
        def parse(s):
            res = {""}
            i = 0

            while i < len(s):
                if s[i] == '{':
                    j = i
                    count = 0

                    while j < len(s):
                        if s[j] == '{':
                            count += 1
                        elif s[j] == '}':
                            count -= 1

                        if count == 0:
                            break
                        j += 1

                    parts = split(s[i + 1:j])
                    res = {a + b for a in res for b in parts}
                    i = j + 1

                elif s[i].isalpha():
                    res = {x + s[i] for x in res}
                    i += 1

            return res

        def split(s):
            res = []
            start = 0
            count = 0

            for i, c in enumerate(s):
                if c == '{':
                    count += 1
                elif c == '}':
                    count -= 1
                elif c == ',' and count == 0:
                    res.extend(parse(s[start:i]))
                    start = i + 1

            res.extend(parse(s[start:]))
            return res

        return sorted(parse(expression))