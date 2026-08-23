class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int l = 0,r= 0;
        int ll = 0, rr = 0;

        for (int i = 0; i < n / 2; i++) {
            if (num.charAt(i) == '?') {
                ll++;
            } else {
               l += num.charAt(i) - '0';
            }
        }

        for (int i = n / 2; i < n; i++) {
            if (num.charAt(i) == '?') {
                rr++;
            } else {
               r+= num.charAt(i) - '0';
            }
        } 
        if ((ll + rr) % 2 != 0) {
            return true;
        } 
        return (l - r ) * 2 != (rr - ll) * 9;
    }
}