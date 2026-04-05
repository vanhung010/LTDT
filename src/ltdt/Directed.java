package ltdt;

import java.util.Iterator;

public class Directed extends Graph {

	public Directed(int ver) {
		super(ver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addEdge(int start, int destination) {
		adjMatrix [start][destination] += 1;
		
	}

	public void printListEdge() {
		for(int i = 0; i< adjMatrix.length; i++) {
			for(int j=0; j< adjMatrix.length; j++) {
				int m = adjMatrix[i][j];
				if(m>0) {
					for(int k =0; k<m;k++) {
						System.out.println("canh ("+i+","+j+")");
					}
				}
			}
			
		}
		
	}

	
	public int inDegree(int ver) {
		int degree =0;
		for(int i =0 ; i< adjMatrix.length; i++) {
			degree += adjMatrix[i][ver];
		}
		return degree;
	}
	
	public int outDegree(int ver) {
		int degree=0;
		for(int i = 0; i<adjMatrix.length; i++) {
			degree += adjMatrix[ver][i];
		}
		return degree;
	}
	@Override
	public int degree(int ver) {
		// TODO Auto-generated method stub
		return outDegree(ver)+inDegree(ver);
	}

	
	
}
