package ltdt;

import java.util.ArrayList;
import java.util.List;
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

	//kiểm tra đồ thị liên thông
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

	public void removeEdge(int start, int destination, int [][] matrix){
		//nếu là cung
		if(adjMatrix[start][destination] <= 0) {
			return;
		}

		adjMatrix[start][destination]--;
		adjMatrix[destination][start]--;
	}

	//tìm cạnh kề
	public int findAdjacent(int ver, int[][] matrix){

		for(int i =0; i< adjMatrix.length; i++){
			if(matrix[ver][i] >0){
				return i;
			}
		}
		//tìm không thấy return -1
		return -1;
	}

	public List<Integer> findEulerPath(){

		if (!isEuler()) return null;

		int length = adjMatrix.length;
		int verStart =0;
		//clone đồ thị
		int [][] adj = new int[length][length];
		for(int i =0; i<length; i++){
			adj[i] = adjMatrix[i].clone();
		}
		//Bước 2 triển khai
		Stack<Integer> stack = new Stack<>();
		List<Integer> path = new ArrayList<>();
		stack.push(verStart);
		//chayj đến khi đồ thị rỗng
		while(!stack.isEmpty()){
			int currEdge = stack.peek();
			//tìm cạnh kề
			int nextVer = findAdjacent(currEdge, adj);

			if(nextVer != -1){
				//nếu còn đường đi thì xóa cạnh
				removeEdge(currEdge, nextVer, adj);
				//dịch chuyển sang điểm mới
				stack.push(nextVer);
			}
			else {
				//nếu hết đường thì ta Pop đỉnh hiện tại ra stack theem vào path
				path.add(stack.pop());
			}
		}
		return path.reversed();
	}

}
