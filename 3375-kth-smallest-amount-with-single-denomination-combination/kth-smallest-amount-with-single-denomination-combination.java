class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long low = 1;
        long high = (long) k * 25;
        long ans = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (count(mid, coins) >= k) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private long count(long target, int[] coins) {
        long cnt = 0;
        int n = coins.length;
        for (int i = 1; i < (1 << n); i++) {
            long lcmVal = 1;
            int bits = 0;
            boolean overflow = false;
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) {
                    bits++;
                    lcmVal = lcm(lcmVal, coins[j]);
                    if (lcmVal > target) {
                        overflow = true;
                        break;
                    }
                }
            }
            if (overflow) continue;
            if (bits % 2 == 1) {
                cnt += target / lcmVal;
            } else {
                cnt -= target / lcmVal;
            }
        }
        return cnt;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}