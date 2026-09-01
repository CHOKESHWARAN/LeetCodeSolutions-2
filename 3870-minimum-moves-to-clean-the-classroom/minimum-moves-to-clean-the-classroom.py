from collections import deque
from typing import List

class Solution:
    def minMoves(self, classroom: List[str], energy: int) -> int:
        m, n = len(classroom), len(classroom[0])
        start_r, start_c = -1, -1
        litter_locations = []
        
        for r in range(m):
            for c in range(n):
                cell = classroom[r][c]
                if cell == 'S':
                    start_r, start_c = r, c
                elif cell == 'L':
                    litter_locations.append((r, c))
                    
        num_litter = len(litter_locations)
        if num_litter == 0:
            return 0
            
        target_mask = (1 << num_litter) - 1
        litter_map = {pos: i for i, pos in enumerate(litter_locations)}
        
        best_energy = {}
        queue = deque([(start_r, start_c, 0, energy, 0)])
        best_energy[(start_r, start_c, 0)] = energy
        
        directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]
        
        while queue:
            r, c, mask, cur_energy, steps = queue.popleft()
            
            if mask == target_mask:
                return steps
                
            if cur_energy == 0:
                continue
                
            for dr, dc in directions:
                nr, nc = r + dr, c + dc
                
                if 0 <= nr < m and 0 <= nc < n and classroom[nr][nc] != 'X':
                    next_cell = classroom[nr][nc]
                    next_mask = mask
                    next_energy = cur_energy - 1
                    
                    if next_cell == 'L' and (nr, nc) in litter_map:
                        next_mask |= (1 << litter_map[(nr, nc)])
                        
                    if next_cell == 'R':
                        next_energy = energy
                        
                    state = (nr, nc, next_mask)
                    if next_energy > best_energy.get(state, -1):
                        best_energy[state] = next_energy
                        queue.append((nr, nc, next_mask, next_energy, steps + 1))
                        
        return -1