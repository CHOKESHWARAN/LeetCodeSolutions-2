class Solution {
    static class State {
        int r, c, mask, energy, steps;
        State(int r, int c, int mask, int energy, int steps) {
            this.r = r;
            this.c = c;
            this.mask = mask;
            this.energy = energy;
            this.steps = steps;
        }
    }

    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int startR = -1, startC = -1;
        List<int[]> litterLocations = new ArrayList<>();

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);
                if (ch == 'S') {
                    startR = r;
                    startC = c;
                } else if (ch == 'L') {
                    litterLocations.add(new int[]{r, c});
                }
            }
        }

        int numLitter = litterLocations.size();
        if (numLitter == 0) return 0;

        int targetMask = (1 << numLitter) - 1;
        int[][] litterIdxMap = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(litterIdxMap[i], -1);
        for (int i = 0; i < numLitter; i++) {
            int[] loc = litterLocations.get(i);
            litterIdxMap[loc[0]][loc[1]] = i;
        }

        int[][][] bestEnergy = new int[m][n][1 << numLitter];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(bestEnergy[i][j], -1);
            }
        }

        Queue<State> queue = new LinkedList<>();
        queue.add(new State(startR, startC, 0, energy, 0));
        bestEnergy[startR][startC][0] = energy;

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            State curr = queue.poll();

            if (curr.mask == targetMask) return curr.steps;
            if (curr.energy == 0) continue;

            for (int[] dir : dirs) {
                int nr = curr.r + dir[0];
                int nc = curr.c + dir[1];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n && classroom[nr].charAt(nc) != 'X') {
                    char cell = classroom[nr].charAt(nc);
                    int nextMask = curr.mask;
                    int nextEnergy = curr.energy - 1;

                    if (cell == 'L' && litterIdxMap[nr][nc] != -1) {
                        nextMask |= (1 << litterIdxMap[nr][nc]);
                    }

                    if (cell == 'R') {
                        nextEnergy = energy;
                    }

                    if (nextEnergy > bestEnergy[nr][nc][nextMask]) {
                        bestEnergy[nr][nc][nextMask] = nextEnergy;
                        queue.add(new State(nr, nc, nextMask, nextEnergy, curr.steps + 1));
                    }
                }
            }
        }

        return -1;
    }
}