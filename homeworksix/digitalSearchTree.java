package ADA_pkg;

import java.util.LinkedList;

public class digitalSearchTree {
	
	static int getBit(int value, int bitIndex)
	{
		return (value>>bitIndex)& 0x01;
	}
	
	static class NodeDT
	{
		int value, index;
		NodeDT left =null;
		NodeDT right = null;
		
		public NodeDT(int value, int index)
		{
			this.value = value;
			this.index = index;
		}
	}
	
	static NodeDT createDT(int[] array)
	{
		NodeDT root = new NodeDT(array[0],0);
		for(int i = 1; i < array.length; i++)
		{
			NodeDT current = root;
			NodeDT newNode = new NodeDT(array[i],i);
			int bitIndex =0;
			while(true)
			{
				/*if(current.value == newNode.value)
				{
					// si el valor del newNode es decir un nuevo repetido
					break;
				}*/
				int currentBit = getBit(array[i],bitIndex);
				if(currentBit == 0)
				{
					if(current.left == null)
					{ 	//no tiene hijo izquierdo
						current.left = newNode;
						break;
					}else
					{
						current = current.left;
					}
				}else // curren bit == 1
				{
					if(current.right == null)
					{ 	//no tiene hijo derecho
						current.right = newNode;
						break;
					}else
					{
						current = current.right;
					}
				}
				bitIndex++;
			}
		}
		return root;
	}
	
	static void printDT(NodeDT root, String spaces){
		if( root == null){
			System.out.println(spaces + "-");
			return;
		}
		
		System.out.println(spaces + root.value);
		printDT(root.left, spaces + "  ");
		printDT(root.right,spaces + "  ");
		
	}
	
	static int searchDT(NodeDT root, int value, int bitIndex)
	{
		if(root == null)
		{
			return -1;
		}
		if(root.value ==value)
		{
			return root.index;
		}
		if(getBit(value, bitIndex)== 0) //buscar a la izquierda
		{
			return searchDT(root.left, value, bitIndex+1);
		}
		else //buscar a la derecha
		{	
			return searchDT(root.right, value, bitIndex+1);
		}	
	}
	
	static LinkedList<NodeDT> BFSDST(NodeDT root, int value, int bitIndex)
	{
		LinkedList<NodeDT> findings = new LinkedList<NodeDT>();
		LinkedList<NodeDT> queue = new LinkedList<NodeDT>();
		LinkedList<NodeDT> visited = new LinkedList<NodeDT>();
		NodeDT currentNode;
		if(root == null)
		{
			findings.add(new NodeDT(-1,-1));
			return findings;
		}
		queue.add(root);
		visited.add(root);
		while(!queue.isEmpty())
		{
			currentNode = queue.get(0);
			queue.remove(0);
			//System.out.println("Current node"+currentNode.value);
			if(currentNode.value==value)
			{
				findings.add(currentNode);
			}
			if(!visited.contains(currentNode.left) && (currentNode.left!=null))
			{
				queue.add(currentNode.left);
				visited.add(currentNode.left);
			}
			if(!visited.contains(currentNode.right) && (currentNode.right!=null))
			{
				queue.add(currentNode.right);
				visited.add(currentNode.right);
			}
		}
		return findings;
	}
	
	public static void main(String[] args) {
		 int[] arr = {1,2,3,4,5,6,7,8,9,10};
		 int[] arr2 = {10,18,10,34,10,4,34,2};
		 int[] arr3 = {23,45,56,34,12,34,56,23,45,34,76};
		 NodeDT root = createDT(arr3);
		 printDT(root,"");
		 // i. al buscar el número 23 debe aparecer 23-0, 23-7,
		 // ii. al buscar el número 34 debe aparecer 34-3, 34-5,34-9
		 System.out.println("Encontrado en pos: "); 
		 LinkedList<NodeDT> result= BFSDST(root, 23, 0);
		 for(NodeDT node : result)
		 {
			 System.out.println(node.value+"-"+node.index);
		 }
	}
}
