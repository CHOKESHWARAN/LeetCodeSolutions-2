class Solution:
    def isValidSudoku(self, board: List[List[str]]) -> bool:
        seen = set()
        
        for r in range(9):
            for c in range(9):
                val = board[r][c]
                if val != '.':
                    row_key = (val, 'r', r)
                    col_key = (val, 'c', c)
                    box_key = (val, 'b', r // 3, c // 3)
                    
                    if row_key in seen or col_key in seen or box_key in seen:
                        return False
                    
                    seen.add(row_key)
                    seen.add(col_key)
                    seen.add(box_key)
                    
        return True