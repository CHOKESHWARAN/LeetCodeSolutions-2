class Solution {
    public boolean canCross(int[] stones) {
        if (stones[1] != 1) return false;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < stones.length; i++) {
            map.put(stones[i], i);
        }

        Set<Integer>[] memo = new HashSet[stones.length];
        for (int i = 0; i < stones.length; i++) {
            memo[i] = new HashSet<>();
        }

        return dfs(stones, map, memo, 0, 0);
    }

    private boolean dfs(int[] stones, Map<Integer, Integer> map, Set<Integer>[] memo, int index, int lastJump) {
        if (index == stones.length - 1) return true;
        if (memo[index].contains(lastJump)) return false;

        for (int step = lastJump - 1; step <= lastJump + 1; step++) {
            if (step > 0) {
                int nextPos = stones[index] + step;
                if (map.containsKey(nextPos)) {
                    if (dfs(stones, map, memo, map.get(nextPos), step)) {
                        return true;
                    }
                }
            }
        }

        memo[index].add(lastJump);
        return false;
    }
}