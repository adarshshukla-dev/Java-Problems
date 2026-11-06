import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int qLen = queries.length;
        int[][] sortedQueries = new int[qLen][2];
        for (int i = 0; i < qLen; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));

        int[] ans = new int[qLen];
        int[] freq = new int[n + 1];
        int activeServers = 0;

        int left = 0, right = 0;
        int logLen = logs.length;

        for (int[] q : sortedQueries) {
            int qTime = q[0];
            int qIdx = q[1];
            int startTime = qTime - x;

            while (right < logLen && logs[right][1] <= qTime) {
                int server = logs[right][0];
                if (freq[server] == 0) {
                    activeServers++;
                }
                freq[server]++;
                right++;
            }

            while (left < logLen && logs[left][1] < startTime) {
                int server = logs[left][0];
                freq[server]--;
                if (freq[server] == 0) {
                    activeServers--;
                }
                left++;
            }

            ans[qIdx] = n - activeServers;
        }

        return ans;
    }
}