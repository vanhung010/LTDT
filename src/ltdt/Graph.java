package ltdt;

import java.util.*;

public abstract class Graph {

	protected int[][] adjMatrix;
	
	
	
	
	public Graph(int ver) {
		super();
		adjMatrix = new int[ver][ver];
	}




	public abstract void addEdge(int start, int destination);
	public abstract void printListEdge();
	public abstract int degree(int ver);
	public void printDegree() {
		for (int i = 0; i < adjMatrix.length; i++) {
		System.out.println("Bậc của đỉnh "+i+" là: "+ degree(i));
		}
	}


	public Set<Integer> getVer(){
		Set<Integer> result = new HashSet<>();
		for(int i =0; i<adjMatrix.length; i++){
			for(int j=0; j<adjMatrix.length;j++){
				if(adjMatrix[i][j]>0) result.add(i);
			}
		}
		return result;
	}

	public void printAdj() {
		for(int i = 0; i< adjMatrix.length; i++) {
			for(int j=0; j< adjMatrix[i].length; j++) {
				System.out.print(adjMatrix[i][j]+" ");
			}
			System.out.println("");
		}
	}

	public void printADJWithDFS(int start){
		Stack<Integer> stack = new Stack<>();
		List<Integer> visted = new ArrayList<>();

		stack.push(start);

		while(!stack.isEmpty()){
			Integer i = stack.pop();
			if(!visted.contains(i)){
				System.out.print(i + " ");

				visted.add(i);
			}
			//đưa phần tử j kề i chưa thăm và stack
			for(int j =0;j< adjMatrix.length;j++){
				if(adjMatrix[i][j] >0 && !visted.contains(j)){
					stack.push(j);
				}

			}

		}
	}
	public void printADJWithBFS(int start){
		Queue<Integer> queue = new LinkedList<>();


		List<Integer> visted = new ArrayList<>();

		queue.add(start);

		while(!queue.isEmpty()){
			int i = queue.poll();
			if(!visted.contains(i)){
				System.out.print(i+" ");
				visted.add(i);
				for(int j = 0; j < adjMatrix.length; j++){
					if(adjMatrix[i][j] > 0 && !visted.contains(j)){
						queue.add(j);
					}
				}
			}
		}
	}





	public static void main(String[] args) {
		Graph matrix1 = new UndirectedGraph(10);
		matrix1.addEdge(0, 1);
		matrix1.addEdge(0, 2);
		matrix1.addEdge(0, 3);
		matrix1.addEdge(1, 4);
		matrix1.addEdge(1, 6);
		matrix1.addEdge(2, 6);
		matrix1.addEdge(2, 9);
		matrix1.addEdge(2,7);
		matrix1.addEdge(3, 7);
		matrix1.addEdge(3, 6);
		matrix1.addEdge(6, 5);
		matrix1.addEdge(6, 8);

		matrix1.printADJWithDFS(0);

		matrix1.printADJWithBFS(0);
	}
}
