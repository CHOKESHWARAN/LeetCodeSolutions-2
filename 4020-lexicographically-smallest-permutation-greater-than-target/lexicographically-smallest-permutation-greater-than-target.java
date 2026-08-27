class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        int[] prefixCount = count.clone();
        int matchLen = 0;
        while (matchLen < n && prefixCount[target.charAt(matchLen) - 'a'] > 0) {
            prefixCount[target.charAt(matchLen) - 'a']--;
            matchLen++;
        }

        for (int i = matchLen; i >= 0; i--) {
            if (i < matchLen) {
                prefixCount[target.charAt(i) - 'a']++;
            }
            if (i == n) continue;

            int targetChar = target.charAt(i) - 'a';
            for (int c = targetChar + 1; c < 26; c++) {
                if (prefixCount[c] > 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(target, 0, i);
                    sb.append((char) ('a' + c));
                    prefixCount[c]--;

                    for (int ch = 0; ch < 26; ch++) {
                        while (prefixCount[ch] > 0) {
                            sb.append((char) ('a' + ch));
                            prefixCount[ch]--;
                        }
                    }
                    return sb.toString();
                }
            }
        }

        return "";
    }
}