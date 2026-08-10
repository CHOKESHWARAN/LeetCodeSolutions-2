class Solution:
    def fib(self, n: int) -> int:
     if n <= 0:
        return 0
     if n == 1 or n == 2:
        return 1

     i, j = 1, 1
     for _ in range(2, n):
        t = i + j
        i = j
        j = t

     return j