package ltdt;

import java.util.Stack;

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

	private boolean isConnectedIgnoreZeroDegree() {
		int start = -1;
		for (int i = 0; i < adjMatrix.length; i++) {
			if (degree(i) > 0) {
				start = i;
				break;
			}
		}

		// Không có cạnh: xem như thỏa điều kiện liên thông cho bài toán Euler.
		if (start == -1) return true;

		boolean[] visited = new boolean[adjMatrix.length];
		Stack<Integer> stack = new Stack<>();
		stack.push(start);

		while (!stack.isEmpty()) {
			int v = stack.pop();
			if (visited[v]) continue;
			visited[v] = true;
			for (int j = 0; j < adjMatrix.length; j++) {
				if (adjMatrix[v][j] > 0 && !visited[j]) {
					stack.push(j);
				}
			}
		}

		for (int i = 0; i < adjMatrix.length; i++) {
			if (degree(i) > 0 && !visited[i]) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEuler() {
		if (!isConnectedIgnoreZeroDegree()) return false;
		for (int i = 0; i < adjMatrix.length; i++) {
			if (degree(i) % 2 != 0) return false;
		}
		return true;
	}

	@Override
	public boolean isSemiEuler() {
		if (!isConnectedIgnoreZeroDegree()) return false;
		int oddCount = 0;
		for (int i = 0; i < adjMatrix.length; i++) {
			if (degree(i) % 2 != 0) oddCount++;
		}
		return oddCount == 2;
	}

}
