class Solution:

  def countCommas(self, n: int) -> int:
    p, r = 1000, 0
    while p <= n:
      r += n - p + 1
      p *= 1000
    return r