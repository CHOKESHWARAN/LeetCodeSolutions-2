class Solution {
    int[] tree_prod;
    int[][] tree_cnt;
    int K;
    int acc_prod;
    int[] acc_cnt;
    
    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        this.K = k;
        tree_prod = new int[4 * n];
        tree_cnt = new int[4 * n][k];
        
        build(1, 0, n - 1, nums);
        
        int[] res = new int[queries.length];
        acc_cnt = new int[k];
        
        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];
            
            update(1, 0, n - 1, idx, val);
            
            acc_prod = 1;
            for (int j = 0; j < k; j++) acc_cnt[j] = 0;
            
            query(1, 0, n - 1, start, n - 1);
            
            res[i] = acc_cnt[x];
        }
        return res;
    }
    
    private void build(int node, int L, int R, int[] nums) {
        if (L == R) {
            tree_prod[node] = nums[L] % K;
            tree_cnt[node][nums[L] % K] = 1;
            return;
        }
        int mid = L + (R - L) / 2;
        build(2 * node, L, mid, nums);
        build(2 * node + 1, mid + 1, R, nums);
        pushUp(node);
    }
    
    private void update(int node, int L, int R, int idx, int val) {
        if (L == R) {
            tree_prod[node] = val % K;
            for (int i = 0; i < K; i++) tree_cnt[node][i] = 0;
            tree_cnt[node][val % K] = 1;
            return;
        }
        int mid = L + (R - L) / 2;
        if (idx <= mid) {
            update(2 * node, L, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, R, idx, val);
        }
        pushUp(node);
    }
    
    private void pushUp(int node) {
        int left = 2 * node;
        int right = 2 * node + 1;
        tree_prod[node] = (tree_prod[left] * tree_prod[right]) % K;
        for (int i = 0; i < K; i++) {
            tree_cnt[node][i] = tree_cnt[left][i];
        }
        for (int i = 0; i < K; i++) {
            tree_cnt[node][(tree_prod[left] * i) % K] += tree_cnt[right][i];
        }
    }
    
    private void query(int node, int L, int R, int qL, int qR) {
        if (L >= qL && R <= qR) {
            int[] next_cnt = new int[K];
            for (int i = 0; i < K; i++) {
                next_cnt[i] = acc_cnt[i];
            }
            for (int i = 0; i < K; i++) {
                next_cnt[(acc_prod * i) % K] += tree_cnt[node][i];
            }
            for (int i = 0; i < K; i++) {
                acc_cnt[i] = next_cnt[i];
            }
            acc_prod = (acc_prod * tree_prod[node]) % K;
            return;
        }
        int mid = L + (R - L) / 2;
        if (qL <= mid) query(2 * node, L, mid, qL, qR);
        if (qR > mid) query(2 * node + 1, mid + 1, R, qL, qR);
    }
}