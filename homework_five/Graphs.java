package ADA_pkg;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

public class Graphs {

	static String names[] = {"A", "B", "C", "D", "E", "F", "G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	static HashMap<String, Integer> map = new HashMap<String, Integer>();
	static
	{
		for(int i =0; i <names.length; i++)
		{
			map.put(names[i], i);
		}
	}
	
	public static boolean DFS(boolean[][] graph, int start, int end)
	{
		ArrayDeque<Integer> pendingNodes = new ArrayDeque<Integer>();
		boolean[] processed = new boolean[graph.length];
		pendingNodes.push(start);//DFS
		//pendingNodes.offer(start); // BFS
		while(!pendingNodes.isEmpty())
		{
			int node = pendingNodes.pop(); // DFS
			//int node = pendingNodes.poll(); //BFS
			if(node==end)
			{
				//System.out.println(node);
				return true;
			}
			//System.out.println(node);
			if(!processed[node])
			{
				processed[node] = true;
				for(int i=0;i<graph[node].length;i++)
				{
					if(graph[node][i] && !processed[i])
					{
						pendingNodes.push(i);//DFS
						//pendingNodes.offer(i); // BFS
					}
				}
			}
		}// end while cycle
		return false;
	}
	
	// this creates a adjacency matrix with all nodes un-connected
	public static boolean[][] initializedMatrix(int size)
	{
		boolean t = true, f = false;
		boolean[][] graph = new boolean[size][size];
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
				graph[i][j]= f;
		}
		return graph;
	}

	public static void printGraph(boolean[][] graph)
	{
		int graphSize = graph.length;
		System.out.print("[");
		for(int row = 0;row<graphSize;row++)
		{
			for(int column = 0;column<graphSize;column++)
			{
				if((row==0)&&(column==0))
				{
					if(graph[row][column]==true)
					{
						System.out.print("t, ");
					}
					else
					{
						System.out.print("f, ");
					}
				}
				else if((row==(graphSize-1))&&(column==(graphSize-1)))
				{
					if(graph[row][column]==true)
					{
						System.out.print(" t]\n");
					}
					else
					{
						System.out.print(" f]\n");
					}
				}
				else if(graph[row][column]==true)
				{
					System.out.print(" t, ");
				}
				else
				{
					System.out.print(" f, ");
				}
			}
			System.out.print("\n");
		}
	}
	
	public static void isThereAPath()
	{
		// Lets grab stuff from the console..
		Scanner sc = new Scanner(System.in); 
		// this define the size of the matrix
		int NumOfVertexes  = sc.nextInt();
		// create adjacent matrix representing the graph
		boolean[][] graph = initializedMatrix(NumOfVertexes);
		int NumOfEdges = sc.nextInt();
		boolean t = true, f = false;
		// add connections between nodes
		for(int i=0;i<NumOfEdges;i++)
		{
			int r = map.get(sc.next());
			int c = map.get(sc.next());
			// if Node A is connected to node B [A][B] is true
			// since is a non directed graph    [B][A] is also true
			graph[r][c] = t;
			graph[c][r] = t;
		}
		sc.close();
		//printGraph(graph);
		// is there a Path algorithm
		// Create the nodes of the graph
		int[] NodesOfTheGraphArray = new int[NumOfVertexes];
		LinkedList<Integer> nodesOfTheGraphLL = new LinkedList<Integer>();
		for(int i=0;i<NumOfVertexes;i++)
		{
			NodesOfTheGraphArray[i]=i;
			nodesOfTheGraphLL.add(i);
		}
		// get all the keys
		ArrayList<String> my_keys = new ArrayList<String>();
		for(String my_key : map.keySet())
		{
			my_keys.add(my_key);
		}
		Collections.sort(my_keys);
		boolean isTheGraphConnected = DFS(graph,NodesOfTheGraphArray[0],NodesOfTheGraphArray[NumOfVertexes-1]);
		// if the graph is connected
		if(isTheGraphConnected)
		{
			System.out.println("\nT");
			for(int i = 0;i<NumOfVertexes;i++)
			{
				System.out.print(my_keys.get(i));
			}
		}
		// the graph is not connected
		else
		{
			System.out.println("\nF");
		}
		// while we haven´t reach all nodes
		int index = nodesOfTheGraphLL.size()-1;
		while(!nodesOfTheGraphLL.isEmpty())
		{
			//System.out.println("current graph LL: ");
			//System.out.println(nodesOfTheGraphLL);
			// get and remove the first node of the graph
			int startNode =  nodesOfTheGraphLL.get(0);
			int endNode = nodesOfTheGraphLL.get(index);
			if(startNode == endNode)
			{
				System.out.print(my_keys.get(startNode));
				nodesOfTheGraphLL.remove(0);
				index = nodesOfTheGraphLL.size()-1;
			}
			// find a sub set
			else if(DFS(graph,startNode,endNode))
			{
				//System.out.println("subset found: ");
				for(int i = 0;i<=index;i++)
				{	
					System.out.print(my_keys.get(nodesOfTheGraphLL.get(i)));
				}
				System.out.println();
				//System.out.println("graph LL before remove: ");
				//System.out.println(nodesOfTheGraphLL);
				for(int i = 0;i<=index;i++)
				{	
					nodesOfTheGraphLL.remove(0);
				}
				//System.out.println("graph LL after remove: ");
				//System.out.println(nodesOfTheGraphLL);
				index = nodesOfTheGraphLL.size()-1;
			}// END OF FOUND  A SUBSET
			else
			{
				index-=1;
			}
		}
	}
	
	public static void main(String[] args) 
	{
		boolean t = true, f = false;
        boolean[][] graph = { { f, t, t, t, f, f, f },
                              { t, f, t, t, f, f, f },
                              { t, t, f, f, f, f, f },
                              { t, t, f, f, t, f, f },
                              { f, f, f, t, f, f, t },
                              { f, f, f, f, f, f, t },
                              { f, f, f, f, t, t, f }
        };
       
        boolean[][] graph2 = {{ f, t, t, t, f, f, f },
                              { t, f, t, f, t, f, f },
                              { t, t, f, t, t, f, f },
                              { t, f, t, f, f, f, t },
                              { f, t, t, f, f, t, f },
                              { f, f, f, f, t, f, f },
                              { f, f, f, t, f, f, f }
                    };
        //System.out.println(DFS(graph2,0,5));
        isThereAPath();
	}

}
