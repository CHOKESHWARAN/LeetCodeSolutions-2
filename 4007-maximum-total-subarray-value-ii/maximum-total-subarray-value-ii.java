class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        if (n == 0 || k == 0) return 0;

        int log = (int) (Math.log(n) / Math.log(2)) + 1;
        int[][] stMax = new int[n][log];
        int[][] stMin = new int[n][log];

        for (int i = 0; i < n; i++) {
            stMax[i][0] = nums[i];
            stMin[i][0] = nums[i];
        }

        for (int j = 1; j < log; j++) {
            int length = 1 << (j - 1);
            for (int i = 0; i + (1 << j) <= n; i++) {
                stMax[i][j] = Math.max(stMax[i][j - 1], stMax[i + length][j - 1]);
                stMin[i][j] = Math.min(stMin[i][j - 1], stMin[i + length][j - 1]);
            }
        }

        int[] lg = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            lg[i] = lg[i / 2] + 1;
        } 
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));

        for (int l = 0; l < n; l++) {
            long val = queryVal(stMax, stMin, lg, l, n - 1);
            pq.offer(new long[]{-val, l, n - 1});
        }

        long totalVal = 0;
        for (int i = 0; i < k; i++) {
            long[] curr = pq.poll();
            long val = -curr[0];
            int l = (int) curr[1];
            int r = (int) curr[2];

            totalVal += val;

            if (r > l) {
                long nextVal = queryVal(stMax, stMin, lg, l, r - 1);
                pq.offer(new long[]{-nextVal, l, r - 1});
            }
        }

        return totalVal;
    }

    private long queryVal(int[][] stMax, int[][] stMin, int[] lg, int l, int r) {
        int length = r - l + 1;
        int j = lg[length];
        int mx = Math.max(stMax[l][j], stMax[r - (1 << j) + 1][j]);
        int mn = Math.min(stMin[l][j], stMin[r - (1 << j) + 1][j]);
        return (long) mx - mn;
    }
}