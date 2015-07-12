

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

/**
*
* Class "Kskl" implements Kruskals Algorithm.
*
* @version 1.0
*
* @author  Manas Mandhani
*/
public class Kskl {
	int[] vertex_list = null;
	Edge[] edge_list = null;
	Edge[] sorted_elist = null;
	Edge[] spanningTree = null;
	InputNodes[] input_nodes;
	HashMap<Integer, Integer> map1 = new HashMap<Integer, Integer>();
	
	/**
	* Create node list 
	*/
	public int listOfNodes(int[] arr){
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (Integer i : arr) {
		  if (map.containsKey(i)) {
		      map.put(i, map.get(i) + 1);
		  } else {
		      map.put(i, 1);
		  }
		}
		 int count = 0;
		  int s = map.size();
		  vertex_list = new int[s];
		  for (Entry<Integer, Integer> entry : map.entrySet()) {
			  vertex_list[count] = entry.getKey();
		  	count++;
		  }
		  Arrays.sort(vertex_list);
		  input_nodes = new InputNodes[vertex_list.length];
		  for (int i = 0; i < input_nodes.length; i++){
			  input_nodes[i] = new InputNodes(i, vertex_list[i]);
		  }
	  return vertex_list.length;
	}
	
	/**
	* extract the node Id
	*/
	public int getId(int value){
		int nodeId = 0;
		for (int i = 0; i < input_nodes.length; i++){
			if(input_nodes[i].value == value){
				nodeId = input_nodes[i].id;
				break;
			}
		}
		return nodeId;
	}
	
	/**
	* Create Edge list 
	*/
	public void ListOfEdges(int m, int length, int[] array){
		edge_list = new Edge[m];
		int counter = 0;
		int count = 0;
		while(counter < length){
			edge_list[count] = new Edge(array[counter], array[counter+1], array[counter+2]);
			counter = counter+3;
			count++;
		}
		sorted_elist = new Edge[edge_list.length]; 
		for (int i = 0; i < edge_list.length; i++){
			sorted_elist[i] = edge_list[i];
		}
		MergeSort sort_edge = new MergeSort();
		sort_edge.mergeSort(sorted_elist);
	}
	
	/**
	* Build Spanning tree using Union Find Data Structure.
	*/
	public void BuildSpanningTree(int n){
		int u = 0;
		int v = 0;
		UnionFind UF = new UnionFind();
		spanningTree = new Edge[n-1];
		UF.creatNodeList(n);
		int i = 0;
		int count = 0;
		while(i < edge_list.length){
			u = sorted_elist[i].node1;
			v = sorted_elist[i].node2;
			if(UF.find(this.getId(u)) != UF.find(this.getId(v))){
				spanningTree[count] = sorted_elist[i];
				UF.union(this.getId(u), this.getId(v));
				count++;
			}
			i++;
		}
	}
	
	/**
	* method main controls the program execution. 
	*/
	public static void main(String[] args) {
		int j = 0;
		int count = 0;
		int len = args.length - args.length / 3;
		int[] temp = new int[len];
		while( j < args.length){
			temp[count] = Integer.parseInt(args[j]);
			j++;
			count++;
			temp[count] = Integer.parseInt(args[j]);
			j = j + 2;
			count++;
		}
	int length = args.length;
	int[] array = new int[length];
	for (int i = 0; i < length; i++){
		array[i] = Integer.parseInt(args[i]);
	}
	int m = length / 3;
	Kskl span = new Kskl();
	NonUnionFind f = new NonUnionFind();
	int n = span.listOfNodes(temp);
	System.out.println("Number of nodes is " + n);
	span.ListOfEdges(m, length, array);
	
	final long startTime = System.currentTimeMillis();
	span.BuildSpanningTree(n);
	final long endTime = System.currentTimeMillis();
	System.out.println("number of edges: " + span.sorted_elist.length);
	
	long nonUF_startTime = System.currentTimeMillis();
	f.mergeSets(n, m, length, array, span.vertex_list);
	long nonUF_finishTime = System.currentTimeMillis();
	
	System.out.println("\nEdges: ");
	for(int i = 0; i < span.edge_list.length; i++){
		System.out.print("(" + span.edge_list[i].node1 + "," + span.edge_list[i].node2 + ")" + " " );
		}
	
	System.out.println("\n\nMinimum Spanning Tree (Union-Find)");
	try{
	for(int i = 0; i < span.spanningTree.length; i++){
		System.out.print("(" + span.spanningTree[i].node1 + "," + span.spanningTree[i].node2 + "," + span.spanningTree[i].weight + ")" + " ");
		}
	}catch(NullPointerException e){
		System.out.println("\n");
		System.out.println("GRAPH NOT CONNECTED. Minimum Spanning tree for some edges is printed. Please enter a valid Connected Graph for Complete Minimum Spanning Tree");
	}
	System.out.println("\n ");
	System.out.println("Time taken in milli seconds(Union Find): " + (endTime - startTime));
	
	System.out.println("\nMinimum Spanning Tree (Non Union-Find)");
	try{
	for(int i = 0; i < f.kl.spanningTree.length; i++){
		System.out.print("(" + f.kl.spanningTree[i].node1 + "," + f.kl.spanningTree[i].node2 + "," + f.kl.spanningTree[i].weight + ")" + " " );
		}
	}catch(NullPointerException e){
		System.out.println("\n");
		System.out.println("GRAPH NOT CONNECTED. Minimum Spanning tree for some edges is printed. Please enter a valid Connected Graph for Complete Minimum Spanning Tree");
	}
	System.out.println("\n");
	System.out.println("Time taken in milli seconds(NON Union Find): " + (nonUF_finishTime - nonUF_startTime));
	
	}
}
