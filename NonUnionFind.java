
/**
*
* Class "NonUnionFind" builds Minimum Spanning Tree without using Union Find data structure.
*
* @version 1.0
*
* @author  Manas Mandhani
*/

public class NonUnionFind {
	Kskl kl = new Kskl();
	int[] route;
	int[] v_list;
	InputNodes[] temp;
	
	/**
	* Add edges to the Minimum Spanning Tree
	*/
	public void mergeSets( int n, int m, int length, int[] array, int[] list ) {
		this.v_list = list;
		kl.ListOfEdges(m, length, array);
		kl.spanningTree = new Edge[n-1];
		temp = new InputNodes[this.v_list.length];
		for (int i = 0; i < temp.length; i++){
			  temp[i] = new InputNodes(i, this.v_list[i]);
		  }
		route = new int[n];
		int i = 0;
		int count = 0;
		int counter = 0;
		for (int j = 0; j < n; j++){
			route[j] = 0;
		}
		while((i!=n-1) && count!=kl.sorted_elist.length){
			if (findCycle(kl.sorted_elist[count])){
				kl.spanningTree[counter] = kl.sorted_elist[count];
				i++;
				counter++;
			}
			count++;
		}
	}
	
	/**
	* extract the node Id 
	*/
	public int getNodeId(int value){
		int nId = 0;
		for (int i = 0; i < temp.length; i++){
			if(temp[i].value == value){
				nId = temp[i].id;
			}
		}
		return nId;
	}
	
	/**
	* Check if there are any cycles. 
	*/
	public boolean findCycle(Edge m ){
		 int u = this.getNodeId(m.node1);
		 int v = this.getNodeId(m.node2);
		 
	     while (route[u] > 0){
	       u = route[u];
	     }
	     while (route[v] > 0){
	       v = route[v];
	     }
	     if (u != v) {
	       route[u] = v;
	       return true;
	     }
	     return false;
		}
}
