
class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        
        Stack<Integer> stack = new Stack<>();
        int currentNum = 0;
        char operation = '+';
        int len = s.length();
        
        for (int i = 0; i < len; i++) {
            char currentChar = s.charAt(i);
            
            if (Character.isDigit(currentChar)) {
                currentNum = currentNum * 10 + (currentChar - '0');
            }
            
            if (!Character.isDigit(currentChar) && currentChar != ' ' || i == len - 1) {
                if (operation == '+') {
                    stack.push(currentNum);
                } else if (operation == '-') {
                    stack.push(-currentNum);
                } else if (operation == '*') {
                    stack.push(stack.pop() * currentNum);
                } else if (operation == '/') {
                    stack.push(stack.pop() / currentNum);
                }
                
                operation = currentChar;
                currentNum = 0;
            }
        }
        
        int result = 0;
        for (int num : stack) {
            result += num;
        }
        
        return result;
    }
}