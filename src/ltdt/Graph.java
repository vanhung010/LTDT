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
	public abstract boolean isEuler();
	public abstract boolean isSemiEuler();
//in bậc
	public void printDegree() {
		for (int i = 0; i < adjMatrix.length; i++) {
		System.out.println("Bậc của đỉnh "+i+" là: "+ degree(i));
		}
	}
//in đồ thị ma trận
	public void printAdj() {
		for(int i = 0; i< adjMatrix.length; i++) {
			for(int j=0; j< adjMatrix[i].length; j++) {
				System.out.print(adjMatrix[i][j]+" ");
			}
			System.out.println("");
		}
	}
	//in đồ thị với BFS
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
//in đồ thị với DFS
	public void printADJWithDFS(int start){
		System.out.println(ADJWithDFS(start));
	}
//Duyệt các phần tử bẳng DFS
	public List<Integer> ADJWithDFS(int start){
		List<Integer> result = new ArrayList<>();
		boolean[] visted = new boolean[adjMatrix.length];
		int count = 0;
		Stack<Integer> stack = new Stack<>();
		stack.push(start);
		while(!stack.isEmpty()){
			int i = stack.pop();
			if(visted[i] == false){
				result.add(i);
				visted[i] = true;
				count++;
			}
			for(int j=0; j<adjMatrix.length; j++){
				if(adjMatrix[i][j] > 0 && visted[j] == false){
					stack.push(j);
				}
			}
		}
		return result;
	}
//Kiểm tra đồ thị có liên thông không
	public boolean checkConnectGraph(){
		return ADJWithDFS(0).size() == adjMatrix.length;
	}
//Kiểm tra đồ thị có đường đi từ điểm này đến điểm kia không
	public boolean checkPath(int start, int end){
		boolean[] visted = new boolean[adjMatrix.length];
		int count = 0;
		Stack<Integer> stack = new Stack<>();
		stack.push(start);
		if(start == end) return true;
		while(!stack.isEmpty()){
			int i = stack.pop();
			if(visted[i] == false){
				visted[i] = true;
				count++;
				for(int j=0; j<adjMatrix.length; j++){
					if(adjMatrix[i][j] > 0 && visted[j] == false){
						if(j == end) {
							return true;
						}
						stack.push(j);
					}
				}
			}

		}
		return false;
	}
//Tìm số thành phần liên thông
	public int calcConnect(){
		if(checkConnectGraph()) return 1;
		int count =0;
		List<Integer> ver = new ArrayList<>();
		for(int i = 0; i< adjMatrix.length; i++){
			ver.add(i);
		}
		while(!ver.isEmpty()){
			Random random = new Random();
			ver.removeAll(ADJWithDFS(ver.get(0)));
			count++;
		}
		return count;
	}
//Lấy thành phần liên thông chưa đỉnh
	public List<Integer> getConnectHasVer(int ver1){
		List<Integer> ver = new ArrayList<>();
		for(int i = 0; i< adjMatrix.length; i++){
			ver.add(i);
		}
		while(!ver.isEmpty()){
			List<Integer> compoment = ADJWithDFS(ver.get(0)); //thành phaần liên thông
			if(compoment.contains(ver1)) return compoment;
			ver.removeAll(compoment);
		}
		return null;
	}



	public static void main(String[] args) {
		Graph matrix1 = new UndirectedGraph(10);
		matrix1.addEdge(0,1);
		matrix1.addEdge(0,2);
		matrix1.addEdge(0,3);
		matrix1.addEdge(1,5);
		matrix1.addEdge(1,4);
		matrix1.addEdge(2,6);
		matrix1.addEdge(7,8);
		matrix1.addEdge(9, 9);

		System.out.println(matrix1.calcConnect());
		System.out.println((matrix1.getConnectHasVer(8)));






	}
}
