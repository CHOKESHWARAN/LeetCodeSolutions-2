class Solution {
    int[] treeMax, treePref, treeSuff;
    char[] leftChar, rightChar;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        treeMax = new int[4 * n];
        treePref = new int[4 * n];
        treeSuff = new int[4 * n];
        leftChar = new char[4 * n];
        rightChar = new char[4 * n];

        char[] chars = s.toCharArray();
        build(1, 0, n - 1, chars);

        int k = queryIndices.length;
        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {
            update(1, 0, n - 1, queryIndices[i], queryCharacters.charAt(i));
            ans[i] = treeMax[1];
        }

        return ans;
    }

    private void merge(int node, int lLen, int rLen) {
        int lc = 2 * node, rc = 2 * node + 1;

        leftChar[node] = leftChar[lc];
        rightChar[node] = rightChar[rc];

        treeMax[node] = Math.max(treeMax[lc], treeMax[rc]);
        treePref[node] = treePref[lc];
        treeSuff[node] = treeSuff[rc];

        if (rightChar[lc] == leftChar[rc]) {
            treeMax[node] = Math.max(treeMax[node], treeSuff[lc] + treePref[rc]);

            if (treePref[lc] == lLen) {
                treePref[node] = lLen + treePref[rc];
            }
            if (treeSuff[rc] == rLen) {
                treeSuff[node] = rLen + treeSuff[lc];
            }
        }
    }

    private void build(int node, int l, int r, char[] s) {
        if (l == r) {
            treeMax[node] = 1;
            treePref[node] = 1;
            treeSuff[node] = 1;
            leftChar[node] = s[l];
            rightChar[node] = s[l];
            return;
        }

        int mid = l + (r - l) / 2;
        build(2 * node, l, mid, s);
        build(2 * node + 1, mid + 1, r, s);
        merge(node, mid - l + 1, r - mid);
    }

    private void update(int node, int l, int r, int idx, char ch) {
        if (l == r) {
            leftChar[node] = ch;
            rightChar[node] = ch;
            return;
        }

        int mid = l + (r - l) / 2;
        if (idx <= mid) {
            update(2 * node, l, mid, idx, ch);
        } else {
            update(2 * node + 1, mid + 1, r, idx, ch);
        }

        merge(node, mid - l + 1, r - mid);
    }
}