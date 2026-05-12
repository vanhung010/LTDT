package weightedGraph;

public class UndirectedGraph extends Graph {

    public UndirectedGraph(int ver) {
        super(ver);
    }

    @Override
    public void addEdge(int start, int destination, double weight) {

        if(adjMatrix[start][destination] != Double.POSITIVE_INFINITY) return;

        if (start >= 0 && start < getTotalVer() && destination >= 0 && destination < getTotalVer()) {
            adjMatrix[start][destination] = weight;
            adjMatrix[destination][start] = weight;
        } else {

            System.out.println("Đỉnh không hợp lệ!");
        }
    }

}
