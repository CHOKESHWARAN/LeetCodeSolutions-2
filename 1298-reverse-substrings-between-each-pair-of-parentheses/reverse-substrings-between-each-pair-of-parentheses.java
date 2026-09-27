class Solution {
    public String reverseParentheses(String s) {
        Stack<StringBuilder> stack = new Stack<>();
        stack.push(new StringBuilder());
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(new StringBuilder());
            } else if (c == ')') {
                StringBuilder reversed = stack.pop().reverse();
                stack.peek().append(reversed);
            } else {
                stack.peek().append(c);
            }
        }
        
        return stack.pop().toString();
    }
}