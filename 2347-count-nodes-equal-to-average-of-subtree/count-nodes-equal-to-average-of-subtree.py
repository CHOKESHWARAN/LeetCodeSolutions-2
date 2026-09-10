# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def averageOfSubtree(self, root: TreeNode) -> int:
        count = 0
        
        def dfs(node):
            nonlocal count
            if not node:
                return 0, 0
            
            left_sum, left_cnt = dfs(node.left)
            right_sum, right_cnt = dfs(node.right)
            
            curr_sum = node.val + left_sum + right_sum
            curr_cnt = 1 + left_cnt + right_cnt
            
            if curr_sum // curr_cnt == node.val:
                count += 1
                
            return curr_sum, curr_cnt
            
        dfs(root)
        return count