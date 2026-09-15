class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int count = 0;
        int i = 0;

        while (i < n) {
            boolean found = false;
            for (int j = i; j < n; j++) {
                if (isPalindrome(s, j, j + k - 1)) {
                    count++;
                    i = j + k;
                    found = true;
                    break;
                }
                if (isPalindrome(s, j, j + k)) {
                    count++;
                    i = j + k + 1;
                    found = true;
                    break;
                }
            }
            if (!found) break;
        }

        return count;
    }

    private boolean isPalindrome(String s, int l, int r) {
        if (r >= s.length()) return false;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }
}