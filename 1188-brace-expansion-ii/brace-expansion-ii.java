class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> set = new TreeSet<>();
        dfs(expression, 0, set);
        return new ArrayList<>(set);
    }

    void dfs(String s, int idx, Set<String> set) {
        if (idx == s.length()) {
            set.add(s);
            return;
        }

        if (s.charAt(idx) == '{') {
            int end = idx, count = 0;
            while (end < s.length()) {
                if (s.charAt(end) == '{') count++;
                if (s.charAt(end) == '}') count--;
                if (count == 0) break;
                end++;
            }

            String inside = s.substring(idx + 1, end);
            List<String> parts = split(inside);

            for (String p : parts) {
                dfs(s.substring(0, idx) + p + s.substring(end + 1), idx, set);
            }
        } else {
            dfs(s, idx + 1, set);
        }
    }

    List<String> split(String s) {
        List<String> res = new ArrayList<>();
        int count = 0, start = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '{') count++;
            else if (s.charAt(i) == '}') count--;
            else if (s.charAt(i) == ',' && count == 0) {
                res.add(s.substring(start, i));
                start = i + 1;
            }
        }

        res.add(s.substring(start));
        return res;
    }
}