import java.util.*;

class Solution {
    public int countCompleteComponents(int n, int[][] edges) 
    {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) 
        {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) 
        {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int completeComponentCount = 0;

        for (int i = 0; i < n; i++) 
        {
            if (!visited[i]) 
            {
                int[] stats = new int[2]; 
                
                bfs(i, adj, visited, stats);
                
                int V = stats[0];
                int E = stats[1];

                if (E == V * (V - 1)) 
                {
                    completeComponentCount++;
                }
            }
        }

        return completeComponentCount;
    }

    private void bfs(int start, List<List<Integer>> adj, boolean[] visited, int[] stats) 
    {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) 
        {
            int curr = queue.poll();
            stats[0]++; 
            stats[1] += adj.get(curr).size(); 

            for (int neighbor : adj.get(curr)) 
            {
                if (!visited[neighbor]) 
                {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }
}