class Solution {
    public int distinctSubseqII(String s) {
        long[] last = new long[26];
        long mod = 1_000_000_007;
        for (char c : s.toCharArray()) {
            long sum = 1;
            for (int i = 0; i < 26; i++) {
                sum = (sum + last[i]) % mod;
            }
            last[c - 'a'] = sum;
        }
        long ans = 0;
        for (int i = 0; i < 26; i++) {
            ans = (ans + last[i]) % mod;
        }
        return (int) ans;
    }
}