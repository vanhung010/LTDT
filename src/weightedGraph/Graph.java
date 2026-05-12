package weightedGraph;

import java.util.Arrays;

public abstract class Graph {
    protected double[][] adjMatrix;

    public Graph(int ver) {
        adjMatrix = new double[ver][ver];
        for(int i =0 ; i< adjMatrix.length; i++){
            Arrays.fill(adjMatrix[i], Double.POSITIVE_INFINITY);
        }
    }

    public int getTotalVer(){
        return this.adjMatrix.length;
    }

    public abstract void addEdge(int start, int destination, double weight);
}
