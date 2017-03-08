package ADA_pkg;

import java.io.BufferedReader;
import java.io.FileReader;

public class HashSearch {

	static int HASH_LIST_SIZE = 0;
	
	
	static class Customer
	{
		String rfc;
		String name, address;
		int index;
		public Customer(String rfc, String name, String address, int index) 
		{
			this.rfc     = rfc;
			this.name    = name;
			this.address = address;
			this.index   = index;
		}
	}
	
	static Customer[] readCustomers(String filename) throws Exception
	{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line = br.readLine().trim();
		final int COUNT = Integer.parseInt(line);
		Customer[] customers = new Customer[COUNT];
		for(int i = 0; i < COUNT; i ++) 
		{
			line = br.readLine();
			String[] rowData = line.split("\t");
			customers[i] = new Customer(rowData[1].trim(), rowData[0].trim(), rowData[2].trim(), i);
		}
		br.close();
		return customers;
	}
	
	static int getValue(char c)
	{	//0..9 --> 0..9
		//A..Z --> 10..35
		if(Character.isDigit(c))
		{
			return c-'0';
		}
		return c-'A'+10; //rango de 10 a 35
	}
	
	static int hashCode(String rfc)
	{
		int h = getValue(rfc.charAt(0));
		for(int i=1; i<rfc.length(); i++)
		{
			h=(h*36+getValue(rfc.charAt(i))) % HASH_LIST_SIZE;
		}
		return h;
	}
	
	static class CustomerNode
	{
		Customer customer;
		CustomerNode next;
		CustomerNode(Customer customer)
		{
			this.customer = customer;
			next = null;
		}
	}
	
	static CustomerNode[] createCustomerHashList(Customer[] customers)
	{
		CustomerNode[] hashList = new CustomerNode[HASH_LIST_SIZE];
		for(Customer c: customers)
		{
			int h = hashCode(c.rfc);
			CustomerNode newNode = new CustomerNode(c);
			CustomerNode current = hashList[h];
			if(current == null)
			{
				hashList[h] = newNode;
			}
			else
			{
				while(current.next!=null)
				{
					current = current.next;
				}
				current.next = newNode;
			}
		}
		return hashList;
	}
	
	static void printHashRfcs(CustomerNode[] customersHashList)
	{
		// Navigate through each hash
		for(int i=0;i<HASH_LIST_SIZE;i++)
		{
			CustomerNode currentCustomerNode = customersHashList[i];
			if(currentCustomerNode!=null)
			{
				System.out.print("["+i+"] - ");
				System.out.print(currentCustomerNode.customer.rfc+", ");
				while(currentCustomerNode.next!=null)
				{
					System.out.print(currentCustomerNode.next.customer.rfc+", ");
					currentCustomerNode = currentCustomerNode.next;
				}
			}
			System.out.println();
		}
	}
	
	static Customer searchHash(CustomerNode[] hash, String rfc)
	{
		int h = getValue(rfc.charAt(0));
		for(int i=1; i<rfc.length(); i++)
		{
			h=(h*36+getValue(rfc.charAt(i))) % HASH_LIST_SIZE;
		}
		CustomerNode customerfinding = hash[h];
		while(true)
		{
			if(customerfinding.customer.rfc.equalsIgnoreCase(rfc))
			{
				break;
			}
			else if(customerfinding.next!=null)
			{
				customerfinding = customerfinding.next;
			}
		}
		return customerfinding.customer;
	}
	
	public static void main(String[] args) throws Exception 
	{
		Customer[] customers = readCustomers("Clientes.txt");
		HASH_LIST_SIZE = customers.length;
		System.out.println(HASH_LIST_SIZE); 
		System.out.println("Printing RFCs function: \n");
		CustomerNode[] customershashlist = createCustomerHashList(customers);
		printHashRfcs(customershashlist);
		String RfcToSearch = "PEIC810810IH0";
		System.out.println("Searching customer with RFC: "+RfcToSearch);
		Customer customerFounded = searchHash(customershashlist,RfcToSearch);
		System.out.println("Customer Found: "+customerFounded.name);
	}
}
