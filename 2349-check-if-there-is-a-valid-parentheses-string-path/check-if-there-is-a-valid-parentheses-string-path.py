class Solution:
    def hasValidPath(self, grid: list[list[str]]) -> bool:
        m, n = len(grid), len(grid[0])
        
        if (m + n - 1) % 2 != 0:
            return False
        if grid[0][0] == ')' or grid[m - 1][n - 1] == '(':
            return False
            
        from functools import cache
        
        @cache
        def dfs(r, c, balance):
            if balance < 0:
                return False
            if r == m - 1 and c == n - 1:
                return balance == 0
            
            res = False
            if r + 1 < m:
                res = res or dfs(r + 1, c, balance + (1 if grid[r + 1][c] == '(' else -1))
            if not res and c + 1 < n:
                res = res or dfs(r, c + 1, balance + (1 if grid[r][c + 1] == '(' else -1))
                
            return res
            
        return dfs(0, 0, 1)