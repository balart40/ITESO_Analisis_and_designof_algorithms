package ADA_pkg;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;



public class Sorts{
	
	public static int[] selectionSort(int[] array)
	{
		int comparaciones = 0;
		int movimientos = 0;
		for (int i=0; i<array.length-1;i++)
		{
			int minIndex = i;
			for(int j=i+1;j<array.length;j++)
			{
				comparaciones +=1;
				if (array[j]<array[minIndex])
				{
					minIndex =j;
				}
			}
			if(minIndex!=i)
			{
				movimientos +=1;
				Utils.swap(array, i, minIndex);
			}
		}
		return new int[] {comparaciones,movimientos};
	}
	
	public static int[] insertionSort(int[] array)
	{
		int comparaciones = 0;
		int movimientos = 0;
		for (int i=1; i<array.length;i++)
		{
			int value = array[i];
			int j=i-1;
			while (j>=0 && value<array[j])
			{
				movimientos++;
				comparaciones++;
				array[j+1]=array[j];
				j--;
			}
			movimientos++;
			array[j+1]=value;
		}
		
		return new int[] {comparaciones,movimientos};
	}
	
	public static int[] bubbleSort(int[] array)
	{
		int comparaciones = 0;
		int movimientos = 0;
		boolean swapped = true;
		for (int i=0;i<array.length-1 && swapped;i++)
		{
			swapped = false;
			for (int j=0;j<array.length-i-1;j++)
			{
				comparaciones++;
				if (array[j]>array[j+1])
				{
					Utils.swap(array, j, j+1);
					swapped = true;
					movimientos++;
				}
			}
		}
		return new int[] {comparaciones,movimientos};
	}
	
	public static void saveToFile(String fileName, double[] array)
	{
		PrintWriter pr;
		try 
		{
			pr = new PrintWriter(fileName);
			pr.print(Arrays.toString(array));
			pr.close();
		} catch (FileNotFoundException e) 
			{
			 	e.printStackTrace();
			 	System.out.println("No such file exists.");
			}
	}
	
	public static void main(String[] args)
	{
		// Define variables
		double avSelect[] =  new double[200];
		double avBubble[] =  new double[200];
		double avInsert[] =  new double[200];
		double avSelectMov[] =  new double[200];
		double avBubbleMov[] =  new double[200];
		double avInsertMov[] =  new double[200];
		// For of all Nth elements from 1 to 200 elements
		for (int i=1; i<=200;i++)
		{
			double tempSelect = 0;
			double tempSelectMov = 0;
			double tempBubble = 0;
			double tempBubbleMov = 0;
			double tempInsert  = 0;
			double tempInsertMov = 0;
			// we will run 100*N times the algorithm
			for(int j=1;j<=100*i;j++)
			{
				int arr1[] = Utils.createIntArray(i, 1, i);
				int arr2[] = arr1.clone();
				int arr3[] = arr2.clone();
				int[] selectionSortResult = selectionSort(arr1);
				double numCompSelectionSort = selectionSortResult[0];
				double numMovSelectionSort = selectionSortResult[1];
				tempSelect+=numCompSelectionSort;
				tempSelectMov+=numMovSelectionSort;
				int[] bubbleSortResult = bubbleSort(arr2);
				double numCompBubbleSort = bubbleSortResult[0];
				double numMovBubbleSort = bubbleSortResult[1];
				tempBubble+=numCompBubbleSort;
				tempBubbleMov+=numMovBubbleSort;
				int[] insertionSortResult = insertionSort(arr3);
				double numCompInsertionSort = insertionSortResult[0];
				double numMovInsertionSort = insertionSortResult[1];
				tempInsert+=numCompInsertionSort;
				tempInsertMov+=numMovInsertionSort;
			}
			avSelect[i-1] = tempSelect/(100*i);
			avBubble[i-1] = tempBubble/(100*i);
			avInsert[i-1] = tempInsert/(100*i);
			avSelectMov[i-1] = tempSelectMov/(100*i);
			avBubbleMov[i-1] = tempBubbleMov/(100*i);
			avInsertMov[i-1] = tempInsertMov/(100*i);
		}
		// Select Sort
		System.out.println("Select Sort");
		System.out.println(Arrays.toString(avSelect));
		saveToFile("SelectSortValueComp",avSelect);
		saveToFile("SelectSortValueMov",avSelectMov);
		// Bubble Sort
		System.out.println("Bubble Sort");
		System.out.println(Arrays.toString(avBubble));
		saveToFile("BubbleSortValueComp",avBubble);
		saveToFile("BubbleSortValueMov",avBubbleMov);
		// Insertion Sort
		System.out.println("Insert Sort");
		System.out.println(Arrays.toString(avInsert));
		saveToFile("InsertionSortValueComp",avInsert);
		saveToFile("InsertionSortValueMov",avInsertMov);
	}// end of main
}
