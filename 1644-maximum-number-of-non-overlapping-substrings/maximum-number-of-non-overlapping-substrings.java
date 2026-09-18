class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) first[c] = i;
            last[c] = i;
        }
        
        List<int[]> intervals = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (first[i] == -1) continue;
            int l = first[i], r = last[i];
            boolean valid = true;
            
            for (int j = l; j <= r; j++) {
                int c = s.charAt(j) - 'a';
                if (first[c] < l) {
                    valid = false;
                    break;
                }
                r = Math.max(r, last[c]);
            }
            
            if (valid) {
                intervals.add(new int[]{l, r});
            }
        }
        
        Collections.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        
        List<String> ans = new ArrayList<>();
        int prevEnd = -1;
        for (int[] interval : intervals) {
            if (interval[0] > prevEnd) {
                ans.add(s.substring(interval[0], interval[1] + 1));
                prevEnd = interval[1];
            }
        }
        
        return ans;
    }
}