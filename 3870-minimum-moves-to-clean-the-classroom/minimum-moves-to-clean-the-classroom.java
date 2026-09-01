import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int startR = -1, startC = -1;
        List<int[]> litters = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') {
                    startR = i;
                    startC = j;
                } else if (c == 'L') {
                    litters.add(new int[]{i, j});
                }
            }
        }

        int numLitter = litters.size();
        int targetMask = (1 << numLitter) - 1;

        int[][][] dist = new int[m][n][1 << numLitter];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < (1 << numLitter); k++) {
                    dist[i][j][k] = -1;
                }
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();
        
        int initialMask = 0;
        for (int i = 0; i < numLitter; i++) {
            if (litters.get(i)[0] == startR && litters.get(i)[1] == startC) {
                initialMask |= (1 << i);
            }
        }

        queue.offer(new int[]{startR, startC, initialMask, energy, 0});
        dist[startR][startC][initialMask] = energy;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int mask = curr[2];
            int e = curr[3];
            int moves = curr[4];

            if (mask == targetMask) {
                return moves;
            }

            if (e == 0) continue;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n && classroom[nr].charAt(nc) != 'X') {
                    char cell = classroom[nr].charAt(nc);
                    int nextE = e - 1;
                    int nextMask = mask;

                    if (cell == 'R') {
                        nextE = energy;
                    }

                    if (cell == 'L') {
                        for (int l = 0; l < numLitter; l++) {
                            if (litters.get(l)[0] == nr && litters.get(l)[1] == nc) {
                                nextMask |= (1 << l);
                                break;
                            }
                        }
                    }

                    if (nextE > dist[nr][nc][nextMask]) {
                        dist[nr][nc][nextMask] = nextE;
                        queue.offer(new int[]{nr, nc, nextMask, nextE, moves + 1});
                    }
                }
            }
        }

        return -1;
    }
}