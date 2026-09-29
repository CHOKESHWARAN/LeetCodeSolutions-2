class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        if ((m + n - 1) % 2 != 0 || grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }
        
        Boolean[][][] memo = new Boolean[m][n][(m + n) / 2 + 1];
        return dfs(grid, 0, 0, 1, memo);
    }
    
    private boolean dfs(char[][] grid, int r, int c, int balance, Boolean[][][] memo) {
        int m = grid.length;
        int n = grid[0].length;
        
        if (balance < 0 || balance > (m + n) / 2) {
            return false;
        }
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }
        if (memo[r][c][balance] != null) {
            return memo[r][c][balance];
        }
        
        boolean res = false;
        if (r + 1 < m) {
            res |= dfs(grid, r + 1, c, balance + (grid[r + 1][c] == '(' ? 1 : -1), memo);
        }
        if (!res && c + 1 < n) {
            res |= dfs(grid, r, c + 1, balance + (grid[r][c + 1] == '(' ? 1 : -1), memo);
        }
        
        return memo[r][c][balance] = res;
    }
}