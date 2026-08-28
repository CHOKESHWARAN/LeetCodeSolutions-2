import java.util.*;

class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        int oddCount = 0;
        int midChar = -1;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                midChar = i;
            }
        }

        if (oddCount > 1) {
            return "";
        }

        int halfLen = n / 2;
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        for (int i = halfLen; i >= 0; i--) {
            int[] currentHalfCount = halfCount.clone();
            boolean validPrefix = true;
            char[] pref = new char[halfLen];

            for (int k = 0; k < i; k++) {
                int c = target.charAt(k) - 'a';
                if (currentHalfCount[c] > 0) {
                    currentHalfCount[c]--;
                    pref[k] = target.charAt(k);
                } else {
                    validPrefix = false;
                    break;
                }
            }

            if (!validPrefix) continue;

            if (i == halfLen) {
                int currentMid = midChar;
                if (n % 2 != 0) {
                    char tMid = target.charAt(halfLen);
                    if (currentMid >= 0 && (currentMid + 'a') > tMid) {
                        String candidate = constructPalindrome(pref, (char) (currentMid + 'a'), currentHalfCount, n);
                        if (candidate.compareTo(target) > 0) return candidate;
                    } else if (currentMid >= 0 && (currentMid + 'a') == tMid) {
                        String candidate = constructPalindrome(pref, (char) (currentMid + 'a'), currentHalfCount, n);
                        if (candidate.compareTo(target) > 0) return candidate;
                    }
                } else {
                    String candidate = constructPalindrome(pref, ' ', currentHalfCount, n);
                    if (candidate.compareTo(target) > 0) return candidate;
                }
                continue;
            }

            char tChar = target.charAt(i);
            for (int nextChar = tChar - 'a' + 1; nextChar < 26; nextChar++) {
                if (currentHalfCount[nextChar] > 0) {
                    int[] nextHalfCount = currentHalfCount.clone();
                    nextHalfCount[nextChar]--;
                    pref[i] = (char) (nextChar + 'a');

                    int pos = i + 1;
                    for (int c = 0; c < 26; c++) {
                        while (nextHalfCount[c] > 0) {
                            pref[pos++] = (char) (c + 'a');
                            nextHalfCount[c]--;
                        }
                    }

                    char mid = n % 2 != 0 ? (char) (midChar + 'a') : ' ';
                    String candidate = buildFullPalindrome(pref, mid, n);
                    if (candidate.compareTo(target) > 0) {
                        return candidate;
                    }
                }
            }
        }

        return "";
    }

    private String constructPalindrome(char[] pref, char mid, int[] remainingHalfCount, int n) {
        char[] fullPref = pref.clone();
        int pos = pref.length;
        for (int c = 0; c < 26; c++) {
            while (remainingHalfCount[c] > 0) {
                fullPref[pos++] = (char) (c + 'a');
                remainingHalfCount[c]--;
            }
        }
        return buildFullPalindrome(fullPref, mid, n);
    }

    private String buildFullPalindrome(char[] half, char mid, int n) {
        StringBuilder sb = new StringBuilder();
        sb.append(half);
        if (n % 2 != 0) {
            sb.append(mid);
        }
        for (int i = half.length - 1; i >= 0; i--) {
            sb.append(half[i]);
        }
        return sb.toString();
    }
}