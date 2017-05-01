package ADA_pkg;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
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
		boolean isTheGraphConnected = true;
		for(int i=1;i<NumOfVertexes;i++)
		{
			isTheGraphConnected= DFS(graph,NodesOfTheGraphArray[0],NodesOfTheGraphArray[i]);
			if(!isTheGraphConnected)
			{
				break;
			}
		}
		//System.out.print("\nGRAPHLL SIZE ");
		//System.out.println(nodesOfTheGraphLL.size());
		// if the graph is connected
		if(isTheGraphConnected)
		{
			System.out.println("T");
			for(int i = 0;i<NumOfVertexes;i++)
			{
				System.out.print(my_keys.get(i));
			}
		}
		// the graph is not connected
		else
		{
			System.out.println("F");
			// while we haven´t reach all nodes
			while(!nodesOfTheGraphLL.isEmpty())
			{
				LinkedList<Integer> SUBSET = new LinkedList<Integer>();
				LinkedList<Integer> INDEXES = new LinkedList<Integer>();
				int startNode = nodesOfTheGraphLL.get(0);
				SUBSET.add(0);
				INDEXES.add(0);
				//System.out.print("\ncurrent start node ");
				//System.out.println(my_keys.get(startNode));
				// subset found
				if(nodesOfTheGraphLL.size()>1)
				{
					for(int i=1;i<nodesOfTheGraphLL.size();i++)
					{	
						int endNode = nodesOfTheGraphLL.get(i);
						//System.out.print("\nCHEKING DFS OF ");
						//System.out.print(my_keys.get(nodesOfTheGraphLL.get(0)));
						//System.out.print(my_keys.get(endNode));
						if(DFS(graph,startNode,endNode))
						{
							//System.out.print("\nconnected\n");
							SUBSET.add(i);
							INDEXES.add(i);
						}
					}
					//System.out.println("\nSUBSET");
					for(int i=0;i<SUBSET.size();i++)
					{
						System.out.print(my_keys.get(nodesOfTheGraphLL.get(SUBSET.get(i))));
					}
					System.out.println();
					Collections.sort(INDEXES);
					Collections.reverse(INDEXES);
					for(int index : INDEXES)
					{
						nodesOfTheGraphLL.remove(index);
					}
				}
				else
				{
					startNode = nodesOfTheGraphLL.get(0);
					System.out.print(my_keys.get(startNode));
					nodesOfTheGraphLL.remove(0);
				}
			}
		}
	}
	
	public static boolean DFSwithNoDeadEnds()
	{
		// Lets grab stuff from the console..
		Scanner sc = new Scanner(System.in); 
		// this define the size of the matrix
		int NumOfVertexes  = sc.nextInt();
		// create adjacent matrix representing the graph
		boolean[][] graph = initializedMatrix(NumOfVertexes);
		int NumOfEdges = sc.nextInt();
		// Capture start ad end
		int start = map.get(sc.next());
		int end = map.get(sc.next());
		boolean t = true;
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
		ArrayList<String> my_keys = new ArrayList<String>();
		for(String my_key : map.keySet())
		{
			my_keys.add(my_key);
		}
		Collections.sort(my_keys);
		// DFS algorithm
		ArrayDeque<Integer> pendingNodes = new ArrayDeque<Integer>();
		boolean[] processed = new boolean[graph.length];
		pendingNodes.push(start);
		//DFS
		while(!pendingNodes.isEmpty())
		{
			int node = pendingNodes.pop(); // DFS
			if(node==end)
			{
				System.out.print(my_keys.get(node));
				return true;
			}
			System.out.print(my_keys.get(node));
			if(!processed[node])
			{
				processed[node] = true;
				for(int i=0;i<graph[node].length;i++)
				{	// NODE has to be connected either with start or end to be pushed
					// this way avoids dead ends
					if(graph[start][i] || graph[end][i])
					{
						if(graph[node][i] && !processed[i])
						{
							pendingNodes.push(i);//DFS
						}
					}
				}// END FOR
			}
		}// end while cycle
		return false;
	}
	
	// ************************************** PRIM y KRUSKAL
	static class Edge implements Comparable<Edge>
	{
		int v1;
		int v2;
		double weight;
		
		public Edge(int v1, int v2, double weight)
		{
			this.v1 = v1;
			this.v2 = v2;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge e) 
		{
			if(this.weight < e.weight) return -1; // arista tiene menor peso
			if(this.weight > e.weight) return 1; // arista tiene mayor peso
			return 0;// son iguales
		}
		
		public String toString()
		{
			return "("+names[v1]+", "+names[v2]+")_<"+weight+">";
		}
		
	}
	
	public static List<Edge> mstPrim(double[][] graph)
	{
		// Lista que almacenara las aristas que pertenecen al MST
		// si son 3 nodos 2 aristas los unen, etc
		ArrayList<Edge> mst =  new ArrayList<Edge>(graph.length-1);
		// arreglo Lista que almacenara los nodos que integran al mst
		boolean visited [] =  new boolean[graph.length];
		// Almacenar las aristas candidatas a pertenecer al MST
		PriorityQueue<Edge> candidates =  new PriorityQueue<Edge>();
		// El nodo de inicio sera 0
		visited[0]= true;
		// añadir los vecinos del nodo 0 a la cola de prioridad
		for(int i=1;i<graph[0].length;i++)
		{
			if(graph[0][i]<Double.MAX_VALUE)
			{
				candidates.add(new Edge(0,i,graph[0][i]));
			}
		}
		// ciclo que se repite N-1 veces
		while((mst.size()<graph.length-1))
		{
			// sacar arista con menor peso
			Edge minEdge = candidates.poll();
			// si conecta un nodo con el MST con un afuera del MST
			if(visited[minEdge.v1] != visited[minEdge.v2])
			{
				// añadir arista a la solucion
				mst.add(minEdge);
				// añadir al nodo a la lista de visitadas
				int newNode = !visited[minEdge.v1] ? minEdge.v1 : minEdge.v2;
				visited[newNode] = true;
				// añadir todas las aritas que parten del nodo nuevo a la lista de candidatos
				for(int j=0;j<graph[newNode].length;j++)
				{
					if(graph[newNode][j]< Double.MAX_VALUE)
					{
						candidates.add(new Edge(newNode,j,graph[newNode][j]));
					}
				}
			}
		}
		return mst;
	}
	
	//KRUSKAL
	
	public static int findLeader(int [] parents, int x)
	{
		while(parents[x]!=x)
		{
			x = parents[x];
		}
		return x;
	}
	// metodo que une 2 conjuntos siempre que no formen ciclo
	public static boolean join(int[] parents, int[] rank, int i, int j)
	{
		int leaderI = findLeader(parents,i);
		int leaderJ = findLeader(parents,j);
		if(leaderI==leaderJ)
		{
			return false; // estan en mismo conjunto
		}
		// si no no estan en mismo conjunto veremos 
		int rankLeaderI = rank[leaderI];
		int rankLeaderJ = rank[leaderJ];
		if(rankLeaderI > rankLeaderJ || rankLeaderI == rankLeaderJ && rankLeaderI > rankLeaderJ)
		{
			parents[leaderJ] = leaderI;
			rank[leaderI]+= rank[leaderJ];
		}
		else
		{
			parents[leaderI] = leaderJ;
			rank[leaderJ]+= rank[leaderI];
		}
		return true;
	}
	
	static final double INF = Double.MAX_VALUE;
	
	public static List<Edge> mstKruskal(double[][] graph)
	{
		PriorityQueue<Edge> candidates =  new PriorityQueue<Edge>();
		List<Edge> mst = new ArrayList<Edge>(graph.length-1);
		// inicializar los edges
		for(int i=0; i<graph.length;i++)
		{
			for(int j=i+1;j<graph[0].length;j++)
			{
				if(graph[i][j]!=INF)
				{
					Edge e = new Edge(i,j,graph[i][j]);
					candidates.offer(e);
				}
			}
		}
		int[] parents = new int[graph.length];
		//inicialmente cada uo es padre de si mismo
		for(int i=0;i<graph.length;i++)
		{
			parents[i]=i;
		}
		int[] rank = new int[graph.length];
		//inicialmdnte todos tienen un rank de 1
		for(int i=0;i<graph.length;i++)
		{
			rank[i]=1;
		}		
		while(mst.size()<graph.length-1)
		{
			Edge minEdge =  candidates.poll();
			if(join(parents,rank,minEdge.v1,minEdge.v2))
			{
				mst.add(minEdge);
			}
		}
		return mst;
	}

	static double [][] initGraph(int size, double initVal)
	{
		double[][] graph =  new double[size][size];
		for(double[] row:graph)
		{
			Arrays.fill(row, initVal);
		}
		return graph;
	}

	static void setEdge(double[][] graph, String v1, String v2, double weight)
	{
		graph[map.get(v1)][map.get(v2)] = weight;
		graph[map.get(v2)][map.get(v1)] = weight;
	}
	
	public static void main(String[] args) 
	{
		final double INF = Double.MAX_VALUE;
		boolean t = true, f = false;
		double[][] graph = initGraph(7,INF);
		setEdge(graph, "A","D", 6.0);
		setEdge(graph, "A","G", 3.0);
		setEdge(graph, "B","C", 3.0);
		setEdge(graph, "B","D", 3.0);
		setEdge(graph, "B","F", 2.0);
		setEdge(graph, "C","F", 2.0);
		setEdge(graph, "C","G", 5.0);
		setEdge(graph, "E","C", 4.0);
		setEdge(graph, "E","G", 1.0);
		
		List<Edge> mst = mstPrim(graph);
		//List<Edge> mst = mstKruskal(graph);
		for(Edge e: mst)
		{
			System.out.println(e);
		}
		
        /*boolean[][] graph = { { f, t, t, t, f, f, f },
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
                    };*/
        //System.out.println(DFS(graph2,0,5));
        //isThereAPath();
        //DFSwithNoDeadEnds();
		
	}

}
