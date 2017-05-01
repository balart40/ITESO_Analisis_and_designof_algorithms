package ADA_pkg;

import java.util.LinkedList;

public class Node234 {
	
	private LinkedList<Integer> values = new LinkedList<Integer>();
	private LinkedList<Integer> indexes = new LinkedList<Integer>();
	private LinkedList<Node234> children = new LinkedList<Node234>();
	private Node234 parent =null;
	
	public Node234(int value, int index)
	{
		values.add(value);
		values.add(index);
		System.out.println("Node created");
		System.out.println(this.toString());
	}
	
	public int getValue(int i)
	{
		return values.get(i);
	}

	public int getIndex(int i)
	{
		return indexes.get(i);
	}
	
	public Node234 getChild(int i)
	{
		return children.get(i);
	}
	
	public int getType()
	{
		return this.values.size()+1;
	}
	
	public boolean isLeaf()
	{
		return children.isEmpty();
	}
	
	public int insert(int value, int index)
	{
		if(getType()==2)
		{
			// el nodo tiene un solo valor
			if(value<values.get(0))
			{
				values.add(0, value);
				indexes.add(0,index);
				return 0;
			}
			else
			{
				values.add(value);
				indexes.add(index);
				return 1;
			}
		}
		else if(getType()==3)
		{
			if(value < values.get(0))
			{
				values.add(0,value);
				indexes.add(0,index);
				return 0;
			}
			else if(value < values.get(1))
			{
				values.add(1,value);
				indexes.add(1,index);
				return 1;
			}
			else
			{
				values.add(value);
				indexes.addLast(index);
				return 2;
			}
		}
		return -1;
	}

	public Node234 getParent()
	{
		return this.parent;
	}
	
	public void addChild(Node234 child)
	{
		child.parent = this;
		children.add(child);
	}
	
	public void addChildren(Node234 child1, Node234 child2, int index)
	{
		child1.parent =  this;
		child1.parent = this;
		children.set(index, child1);
		children.add(index+1, child2);
	}
	
	public String toString()
	{
		String s = "";
		for(int i = 0; i<values.size(); i++)
		{
			s+=values.get(i)+" ";
		}
		return s;
	}
	
}

