class Solution:

  def calculate(self, s: str) -> int:
    stack = []
    current_num = 0
    operation = "+"
    length = len(s)

    for i in range(length):
      char = s[i]

      if char.isdigit():
        current_num = current_num * 10 + int(char)

      if (not char.isdigit() and char != " ") or i == length - 1:
        if operation == "+":
          stack.append(current_num)
        elif operation == "-":
          stack.append(-current_num)
        elif operation == "*":
          stack.append(stack.pop() * current_num)
        elif operation == "/":
          stack.append(int(stack.pop() / current_num))

        operation = char
        current_num = 0

    return sum(stack)