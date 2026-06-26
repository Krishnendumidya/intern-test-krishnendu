
    static class Pair {
        int row, col, dist;

        Pair(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }

    public static int minMoves(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Queue<Pair> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];

        int[] dr = {-1, 1, 0, 0}; 
        int[] dc = {0, 0, -1, 1}; 

        // Find the starting position
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'S') {
                    queue.offer(new Pair(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();

            if (grid[cur.row][cur.col] == 'E') {
                return cur.dist;
            }

            for (int k = 0; k < 4; k++) {
                int nr = cur.row + dr[k];
                int nc = cur.col + dc[k];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n &&
                        !visited[nr][nc] &&
                        grid[nr][nc] != '#') {

                    visited[nr][nc] = true;
                    queue.offer(new Pair(nr, nc, cur.dist + 1));
                }
            }
        }

        return -1;
    }
