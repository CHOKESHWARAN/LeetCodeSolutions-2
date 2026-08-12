class Solution:
    def canCross(self, stones: list[int]) -> bool:
        if stones[1] != 1:
            return False

        stone_set = set(stones)
        target = stones[-1]

        @cache
        def dfs(pos: int, last_jump: int) -> bool:
            if pos == target:
                return True
            
            for step in (last_jump - 1, last_jump, last_jump + 1):
                if step > 0 and (pos + step) in stone_set:
                    if dfs(pos + step, step):
                        return True
            
            return False

        return dfs(1, 1)