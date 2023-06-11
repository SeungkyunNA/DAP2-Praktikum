import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DirectedGraph {
    
    public Node[] nodes;
    public DirectedGraph(int n) {
        nodes = new Node[n];
        for (int id = 0 ; id < n ; id++) {
            Node node = new Node(id);
            this.nodes[id] = node;
        }
    }
    
    public Node getNode(int i) {
        if (i >= 0 && i < nodes.length) {
            return nodes[i];
        } else { 
            return null;
        }
        
    }

    public void addEdge(int i, int j) throws IllegalArgumentException {
        if (i == j) {
            throw new IllegalArgumentException("i == j");
        }
        if (i < 0 || j < 0 || i >= nodes.length  || j >= nodes.length) {
            throw new IllegalArgumentException();
        }
        
        try {
            nodes[i].addEdge(nodes[j]);
        } catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("Edge already exsist");
        }
    }

    public boolean hasEdge(int i, int j) throws IllegalArgumentException {

        if (i >= 0 && j >= 0 && i < nodes.length && j < nodes.length) {
            return nodes[i].hasEdge(nodes[j]);
        } else {
            throw new IllegalArgumentException("Out of Index from DirectedGraph.hasEdge");
        }
        
    }

    public Integer bfs(int iStart, int iEnd) throws IllegalArgumentException {

        if (iStart < 0 || iEnd < 0 || iStart >= nodes.length  || iEnd >= nodes.length) {
            throw new IllegalArgumentException();
        }

        boolean[] visited = new boolean[nodes.length]; 
        int[] dist = new int[nodes.length]; 
        Queue<Integer> q = new LinkedList<>();

        /* Init for start node */
        visited[iStart] = true;
        dist[iStart] = 0;
        q.add(iStart);

        
        while (!q.isEmpty()) {
            int nodeIndex = q.poll();

            if (nodeIndex == iEnd) {
                return dist[nodeIndex];
            }

            for (int i = 0; i < nodes.length; i++) {
                if (this.hasEdge(nodeIndex, i) && !visited[i]) {
                    q.add(i);
                    visited[i] = true;
                    dist[i] = dist[nodeIndex] + 1;
                }
            }
        }
        
        return null;
    }

    public Integer dfs(int iStart, int iEnd) throws IllegalArgumentException {
        if (iStart < 0 || iEnd < 0 || iStart >= nodes.length  || iEnd >= nodes.length) {
            throw new IllegalArgumentException();
        }
        boolean[] visited = new boolean[nodes.length];
        Integer result = dfsLoop(iStart, iEnd, visited, 0);
        return result == Integer.MAX_VALUE ? null : result;
    }

    private Integer dfsLoop(int current, int iEnd, boolean[] visited, int currentDistance) {
        
        visited[current] = true;
        if (current == iEnd) {
            return currentDistance;
        }

        Integer minDist = Integer.MAX_VALUE;
        for (int i = 0; i < nodes.length; i++) {
            if (hasEdge(current, i) && !visited[i]) {
                Integer distance = dfsLoop(i, iEnd, visited, currentDistance + 1);
                minDist = Math.min(minDist, distance);
            }
        }

        return minDist;
    }



    public static DirectedGraph readFile(String file) {
        DirectedGraph dg = null;
        int numberOfNodes = 0;
        Scanner s = new Scanner(file);


        while(s.hasNextLine()) {
            String str = s.nextLine();
            str = str.replaceAll("\\s", "");
            char[] input = str.toCharArray();

            if (input.length != 0) {
                switch(input[0]){
                    case 'd' : {
                        try {
                            numberOfNodes = input[1]-48;
                            if (numberOfNodes < 1 || input.length != 2) {
                                System.err.println("Fileread Fail : Invaild d-Value ");
                                s.close();
                                return null;
                            }

                            dg = new DirectedGraph(numberOfNodes);

                        } catch (NumberFormatException e) {
                            System.err.println("Fileread Fail : Invaild d-Value ");
                            s.close();
                            return null;
                        }
                        break;
                    }
                    case 'e' : {
                        if (numberOfNodes == 0) {
                            System.out.println("Fileread Fail : e-Value before d-value");
                            s.close();
                            return null;
                        }
                        try{
                            if (input.length != 3) {
                                System.err.println("Fileread Fail : only 2 Arguments for e");
                                s.close();
                                return null;
                            }
                            int start = input[1]-48;
                            int end = input[2]-48;
                            System.out.println("s : " + start + " e : " + end );
                            
                            if (start < 0 || end < 0 || start >= numberOfNodes || end >= numberOfNodes || start == end) {
                                System.err.println("Fileread Fail : Arguments for e OutOfIndex");
                                s.close();
                                return null;
                            }
                            if(dg == null) {
                                System.err.println("Fileread Fail : e-Value before d-value");
                                return null;
                            }
                            
                            dg.addEdge(start, end);
                            

                        } catch (NumberFormatException e) {
                            System.err.println("Fileread Fail : Invaild e-Value ");
                            s.close();
                            return null;
                        }
                        break;
                    }
                    case '#' : break;
                    default : s.close(); System.err.println("Fileread Fail : Invaild symbol"); return null;
            }
        }
        }

        s.close();
        return dg;
    }
}