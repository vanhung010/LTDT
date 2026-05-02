package ltdt;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Directed extends Graph {

	public Directed(int ver) {
		super(ver);

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

		return outDegree(ver)+inDegree(ver);
	}

	public void removeEdge(int start, int destination){
		if(adjMatrix[start][destination] > 0) adjMatrix[start][destination]--;
	}

	private boolean isWeaklyConnectedIgnoreZeroDegree() {
		int start = -1;
		for (int i = 0; i < adjMatrix.length; i++) {
			if (inDegree(i) + outDegree(i) > 0) {
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
				if ((adjMatrix[v][j] > 0 || adjMatrix[j][v] > 0) && !visited[j]) {
					stack.push(j);
				}
			}
		}

		for (int i = 0; i < adjMatrix.length; i++) {
			if (inDegree(i) + outDegree(i) > 0 && !visited[i]) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEuler() {
		if (!isWeaklyConnectedIgnoreZeroDegree()) return false;
		for (int i = 0; i < adjMatrix.length; i++) {
			if (inDegree(i) != outDegree(i)) return false;
		}
		return true;
	}

	@Override
	public boolean isSemiEuler() {
		if (!isWeaklyConnectedIgnoreZeroDegree()) return false;

		int startCount = 0; // out - in = 1
		int endCount = 0;   // in - out = 1

		for (int i = 0; i < adjMatrix.length; i++) {
			int diff = outDegree(i) - inDegree(i);
			if (diff == 1) {
				startCount++;
			} else if (diff == -1) {
				endCount++;
			} else if (diff != 0) {
				return false;
			}
		}

		return startCount == 1 && endCount == 1;
	}

	public List<Integer> findEuler(){
		for(int i =0; i< adjMatrix.length; i++){
			if( degree(i) % 2 != 0) {
				System.out.println("không có chu trình euler");
				return null;
			}
		}
		if(!isWeaklyConnectedIgnoreZeroDegree())  return null;
		List<Integer> result = new ArrayList<>();
		Stack<Integer> stack = new Stack<>();
		Random random = new Random();
		int start = random.nextInt(0, adjMatrix.length);
		stack.push(start);
		while(!stack.empty()){
			int ver = stack.peek();
			boolean foundEdge = false;
			//Tìm cạnh kề
			for(int i = 0; i<adjMatrix.length;i++){
				if(adjMatrix[ver][i] > 0 ) {
					removeEdge(ver, i);
					stack.push(i);
					foundEdge = true;
					break;
				}
			}
			//Nếu đỉnh không có cạnh kề
			if(!foundEdge){
				result.add(ver);
				stack.pop();
			}
		}
return result.reversed();
	}

//	public List<Integer> findEulerPath(){
//		if(!isSemiEuler())
//	}

	static void main() {
		Directed directed = new Directed(6);
		directed.addEdge(1,3);
		directed.addEdge(1,2);
		directed.addEdge(1,4);
		directed.addEdge(1,0);
		directed.addEdge(3,2);
		directed.addEdge(2,4);
		directed.addEdge(0,4);
		directed.addEdge(2,5);
		directed.addEdge(4,5);
		System.out.println(directed.findEuler());
	}
}
