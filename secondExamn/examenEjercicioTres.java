package ADA_pkg;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class examenEjercicioTres 
{

	public static boolean BFS(boolean[][] graph, int start, int end)
	{
		ArrayDeque<Integer> pendingNodes = new ArrayDeque<Integer>();
		Deque<Integer> path =  new ArrayDeque<Integer>();
		boolean[] processed = new boolean[graph.length];
		//pendingNodes.push(start);//DFS
		pendingNodes.offer(start); // BFS
		while(!pendingNodes.isEmpty())
		{
			//int node = pendingNodes.pop(); // DFS
			int node = pendingNodes.poll(); //BFS
			if(node==end)
			{
				//System.out.println(node);
				path.add(node);
				//System.out.println(path);
				return true;
			}
			//System.out.println(node);
			if(!processed[node])
			{
				path.add(node);
				processed[node] = true;
				for(int i=0;i<graph[node].length;i++)
				{
					if(graph[node][i] && !processed[i])
					{
						//pendingNodes.push(i);//DFS
						pendingNodes.offer(i); // BFS
					}
				}
			}
		}// end while cycle
		return false;
	}
	
	static boolean existsRoute(int[][]map, int T, int inicio, int destino)
	{
		boolean isThereaRoute = false;
		// we are going to make sure to dissapear the edge that does not have enough capacity for tourist
		boolean [][] booleanMap = new boolean[map.length][map.length];
		// initialize new graph
		for(int i=0;i<map[0].length;i++)
		{
			for(int j=0;j<map[0].length;j++)
			{
				booleanMap[i][j]=false;
			}
		}
		// restrict the graph we dont need that edges less than T
		for(int i=0;i<map[0].length;i++)
		{
			for(int j=0;j<map[0].length;j++)
			{
				if(map[i][j]>=T)
				{
					booleanMap[i][j]=true;
				}
			}
		}
		isThereaRoute =  BFS(booleanMap,  inicio, destino);
		return isThereaRoute;
	}
	
	public static void main(String[] args)
	{	//				 0 1   2 3				
		int[][] map = { {0,20,15,0},	//0
				        {20,0,0,10},	//1
				        {15,0,0,25},	//2
				        {0,10,25,0}};   //3
		int Turistas =  12;
		System.out.println(existsRoute(map,Turistas,0,3));
	}
}
