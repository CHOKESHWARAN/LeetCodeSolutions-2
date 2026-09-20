class Solution {
    public int reverseDegree(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int reverseAlphabetPosition = 'z' - s.charAt(i) + 1;
            sum += reverseAlphabetPosition * (i + 1);
        }
        return sum;
    }
}