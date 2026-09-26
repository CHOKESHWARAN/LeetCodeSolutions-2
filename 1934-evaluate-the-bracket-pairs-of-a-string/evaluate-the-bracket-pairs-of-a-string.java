class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> dict = new HashMap<>();
        for (List<String> pair : knowledge) {
            dict.put(pair.get(0), pair.get(1));
        }

        StringBuilder result = new StringBuilder();
        StringBuilder currentKey = new StringBuilder();
        boolean inBracket = false;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                inBracket = true;
                currentKey.setLength(0);
            } else if (c == ')') {
                inBracket = false;
                String key = currentKey.toString();
                if (dict.containsKey(key)) {
                    result.append(dict.get(key));
                } else {
                    result.append("?");
                }
            } else {
                if (inBracket) {
                    currentKey.append(c);
                } else {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }
}