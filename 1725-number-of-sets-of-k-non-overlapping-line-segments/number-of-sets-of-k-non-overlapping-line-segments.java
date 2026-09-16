class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1000000007;
        long[][] dp = new long[n + 1][k + 1];
        long[][] sumDp = new long[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
            sumDp[i][0] = i + 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = (dp[i - 1][j] + sumDp[i - 1][j - 1]) % MOD;
                sumDp[i][j] = (sumDp[i - 1][j] + dp[i][j]) % MOD;
            }
        }

        return (int) dp[n - 1][k];
    }
}