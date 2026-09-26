class Solution:
    def evaluate(self, s: str, knowledge: List[List[str]]) -> str:
        d = {k: v for k, v in knowledge}
        res = []
        cur_key = []
        in_bracket = False
        
        for c in s:
            if c == '(':
                in_bracket = True
                cur_key = []
            elif c == ')':
                in_bracket = False
                key = "".join(cur_key)
                res.append(d.get(key, "?"))
            else:
                if in_bracket:
                    cur_key.append(c)
                else:
                    res.append(c)
                    
        return "".join(res)