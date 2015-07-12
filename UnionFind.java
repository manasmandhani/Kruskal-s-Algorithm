

/**
*
* Class "UnionFind" creates Minimum Spanning Tree using Union Find data structure.
* 
* @version 1.0
* 
* Reference: http://en.wikipedia.org/wiki/Disjoint-set_data_structure
*
* @author  Manas Mandhani
*/

class UnionFind{
	Node[] nodeList;

	/**
	* Create node list 
	*/
	public void creatNodeList(int n) {
		nodeList = new Node[n];
		for (int i = 0; i < n; i++) {
			nodeList[i] = new Node(i);
		}
	}
	
	/**
	* Determines which subset a particular set is in. 
	*/
	public int find(int node1) {
		Node node = nodeList[node1];
		int count;
		for (count = 0; node.parent != null; count++){
			node = node.parent;
		}
		if(count > 1){
			reduceTreeDepth(node1, node.name);
		}
		return node.name;
	}
	
	/**
	* Performs the compression of path so that the time of operation can be O(1)
	*/
	public void reduceTreeDepth(int nodeChild, int nodeRoot){
		Node current = nodeList[nodeChild];
		while(current.name != nodeRoot){
			Node temp = current.parent;
			current.parent = nodeList[nodeRoot];
			current = temp;
		}
	}

	/**
	* Combines two subsets into a single set 
	*/
	public void union(int node1, int node2) {
		Node rootA = nodeList[find(node1)];
		Node rootB = nodeList[find(node2)];
		rootB.parent = rootA;
	}
}