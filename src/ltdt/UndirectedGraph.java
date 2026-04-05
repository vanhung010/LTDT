package ltdt;

public class UndirectedGraph extends Graph {

	public UndirectedGraph(int ver) {
		super(ver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addEdge(int start, int destination) {
		adjMatrix [start][destination] += 1;
		 
		if(start != destination) {
			adjMatrix [destination][start] +=1;
		}
	}

	@Override
	public void printListEdge() {
		for(int i = 0; i< adjMatrix.length; i++) {
			for(int j=i; j< adjMatrix.length; j++) {
				int m = adjMatrix[i][j];
				if(m>0) {
					for(int k = 0; k<m; k++) {
						System.out.println("Canh ("+ i+"."+j+")");
					}
				}
			}
			
		}
		
	}

	@Override
	public int degree(int ver) {
		int degree =0;
		if(adjMatrix[ver][ver]>0) degree++; //kiểm tra khuyên
		
		for(int i = 0; i< adjMatrix[ver].length; i++) {
			degree += adjMatrix[ver][i];
		}
		return degree;
		
	}

}
