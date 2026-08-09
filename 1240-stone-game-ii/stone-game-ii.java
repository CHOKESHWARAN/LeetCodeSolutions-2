class Solution {
    public int stoneGameII(int[] piles) {
          int n = piles.length;

        int[] sum = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            sum[i] = sum[i + 1] + piles[i];
        }

        int[][] dp = new int[n + 1][n + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int m = n; m >= 1; m--) {

                if (i + 2 * m >= n) {
                    dp[i][m] = sum[i];
                } else {
                    for (int x = 1; x <= 2 * m; x++) {
                        dp[i][m] = Math.max(
                            dp[i][m],
                            sum[i] - dp[i + x][Math.max(m, x)]
                        );
                    }
                }
            }
        }

        return dp[0][1];
    }
}