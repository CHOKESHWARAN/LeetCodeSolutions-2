class Solution {
    public int longestPalindrome(String s) {
        int[] count = new int[128];
        
        for (char c : s.toCharArray()) {
            count[c]++;
        }

        int length = 0;
        boolean hasOdd = false;

        for (int c : count) {
            length += (c / 2) * 2;  
            if (c % 2 != 0) {
                hasOdd = true;  
            }
        }

        if (hasOdd) {
            length += 1;  
        }

        return length;
    }
}