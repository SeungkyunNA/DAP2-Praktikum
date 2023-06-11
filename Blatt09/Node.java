import java.util.ArrayList;

public class Node {
    public int id;
    public ArrayList<Edge> outgoingEdges;
    public Node(int id) {
        this.id = id;
        outgoingEdges = new ArrayList<Edge>();
    }
    public void addEdge(Node to) {
        if (!hasEdge(to)) {
            Edge e = new Edge(this, to);
            outgoingEdges.add(e);
        } else {
            throw new IllegalArgumentException();
        }

    }
    public boolean hasEdge(Node to) {
        for (Edge e : outgoingEdges) {
            if (e.end.equals(to)) {
                return true;
            }
        }
        return false;
    }
}
