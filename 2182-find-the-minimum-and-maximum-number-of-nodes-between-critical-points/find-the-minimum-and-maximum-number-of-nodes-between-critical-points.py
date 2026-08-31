# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def nodesBetweenCriticalPoints(self, head: Optional[ListNode]) -> List[int]:
        if not head or not head.next or not head.next.next:
            return [-1, -1]
        
        prev = head
        curr = head.next
        index = 1
        
        first_critical = -1
        prev_critical = -1
        min_distance = float('inf')
        
        while curr.next:
            next_node = curr.next
            
            is_local_maxima = curr.val > prev.val and curr.val > next_node.val
            is_local_minima = curr.val < prev.val and curr.val < next_node.val
            
            if is_local_maxima or is_local_minima:
                if first_critical == -1:
                    first_critical = index
                else:
                    min_distance = min(min_distance, index - prev_critical)
                prev_critical = index
                
            prev = curr
            curr = next_node
            index += 1
            
        if min_distance == float('inf'):
            return [-1, -1]
            
        return [min_distance, prev_critical - first_critical]