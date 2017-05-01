package ADA_pkg;

import java.util.LinkedList;

import ADA_pkg.BinaryTree.Node;

public class Tree234 {

	public static class Node234{
		private LinkedList<Integer> values = new LinkedList<Integer>();
		private LinkedList<Integer> indexes = new LinkedList<Integer>();
		private LinkedList<Node234> children = new LinkedList<Node234>();
		private Node234 parent = null;
		
		public Node234(int value, int index){
			values.add(value);
			indexes.add(index);
		}
		
		public int getValue(int i){
			return values.get(i);
		}
		
		public int getIndex(int i){
			return indexes.get(i);
		}
		
		public Node234 getChild(int i){
			return children.get(i);
		}
		
		public int getType(){
			return this.values.size() +1;
		}
		
		public boolean isLeaf(){
			return children.isEmpty();
		}
		
		public int insert(int value, int index){
			if(getType()==2){ //el nodo tiene un solo valor
				if(value < values.get(0)){ //insertar a la izquierda
					values.add(0,value);
					indexes.add(0, index);
					return 0;
				}else{ //insertar a la derecha
					values.add(value);
					indexes.add(index);
					return 1;
				}
			}else if(getType()==3){ // es un nodo 3
				if(value < values.get(0)){ //insertar a la izquierda
					values.add(0,value);
					indexes.add(0,index);
					return 0; 
				}else if (value < values.get(1)){ //en medio
					values.add(1, value);
					indexes.add(1,index);
					return 1;
				} else{ // a la derecha
					values.add(value);
					indexes.add(index);
					return 2;
				}
			}
			
			return -1;
		}
		
		public Node234 getParent(){
			return this.parent;
		}
		
		public void addChild(Node234 child){
			child.parent = this;
			children.add(child);
		}
		
		public void addChildren( Node234 child1, Node234 child2, int index){
			child1.parent =this;
			child2.parent = this;
			children.set(index, child1);
			children.add(index+1, child2);
		}
		
		public String toString(){
			String s = "";
			for (int i =0; i<values.size(); i++){
				s+= values.get(i)+ " ";
			}
			return s;
		}
		
	}
	//no se compara si hay elementos iguales
	static Node234 createTree234(int[] array){
		Node234 root = new Node234(array[0],0);
		for(int i =1; i< array.length; i++){
			Node234 current = root;
			boolean currentIsLeaf = false;
			
			while(!currentIsLeaf){
				if(current.getType()==4){
					Node234 left = new Node234(current.getValue(0),current.getIndex(0));
					Node234 right = new Node234(current.getValue(2),current.getIndex(2));
					if(!current.isLeaf()){
						left.addChild(current.getChild(0));
						left.addChild(current.getChild(1));
						right.addChild(current.getChild(2));
						right.addChild(current.getChild(3));
					}
					if(current ==root){
						root = new Node234(current.getValue(1), current.getIndex(1));
						root.addChild(left);
						root.addChild(right);
						current = root;
					}else{
						Node234  parent = current.getParent();
						 int index = parent.insert(current.getValue(1), current.getIndex(1));
						 parent.addChildren(left, right, index);
						 current = parent;
					}
				}else if (current.isLeaf()){
					current.insert(array[i], i);
					currentIsLeaf = true;
				}else{
					if(current.getType()==2){ //tipo 2
						if(array[i]<current.getValue(0))//por la izquierda
							current = current.getChild(0); 
						else
							current = current.getChild(1);//por la derecha
					}else{ //tipo 3
						if(array[i]<current.getValue(0))//por la izquierda
							current = current.getChild(0); 
						else if (array[i]< current.getValue(1)) //en medio
							current = current.getChild(1);
						else
							current = current.getChild(2);//por la derecha
					}
				}
			}
			
		}	
		
		
		return root;
	}
	
		
	 static void printTree234(Node234 current, String spaces){
		   System.out.println(spaces+current);
		   if(current.isLeaf())return;
		   if(current.getType()==4) System.out.println("Nodo 4");
		   for(int i =0; i<current.getType(); i++){
			  printTree234(current.getChild(i), spaces+"  "); 
		   }
	 }
	
	public static void main(String[] args) {
		//int[] arr = {25,13,4,8,16,29,5,32,30,35};
		//Node234 root =createTree234(arr);
		//printTree234(root, "");*/
		
		//int[] arr = {1,2,3,4,5,6,7,8,9,10};//,11,12,13,14,15,16,17,18,19,20,21,22};
		int[] arr = {9,6,4,10,13,7,8,0,11,5,16,14};
		Node234 root =createTree234(arr);
		printTree234(root, "");
	}
	
}
