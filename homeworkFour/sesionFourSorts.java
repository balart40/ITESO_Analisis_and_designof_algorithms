package ADA_pkg;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

public class sesionFourSorts {

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
		int[] arrayForQuickSort = {1,2,5,26,7,14,3,7,12};
		System.out.println("Original array");
		Utils.printArray(arrayForQuickSort);
		// provide initial right to be the last one of the elements
		// Subtracting  1 since the array is from 0 to n-1 not 1 to n in java
		// ******* QUICK SORT************************
		int initialRight = arrayForQuickSort.length-1;
		int initialLeft = 0;
		intQuickComps tempQuickSortComps1 = new intQuickComps();
		intQuickMovs tempQuickSortMovs1 = new intQuickMovs();
		QuickSort qSort = new QuickSort();
		qSort.quickSort(arrayForQuickSort,initialLeft,initialRight,tempQuickSortComps1,tempQuickSortMovs1);
		System.out.println("Array after QuickSort");
		Utils.printArray(arrayForQuickSort);
		// ******* MERGE SORT
		int[] arrayForMergeSort = {1,2,5,26,7,14,3,7,12};
		System.out.println("\nOriginal array");
		Utils.printArray(arrayForMergeSort);
		initialRight = arrayForQuickSort.length-1;
		initialLeft = 0;
		intMergeMovs tempMergeSortMov1 = new intMergeMovs();
		intMergeComps tempMergeSortComp1 = new intMergeComps();
		MergeSort mSort = new MergeSort();
		mSort.mergeSort(arrayForMergeSort, initialLeft, initialRight,tempMergeSortComp1,tempMergeSortMov1);
		System.out.println("Array after MergeSort");
		Utils.printArray(arrayForMergeSort);
		
		double[] avQuickSortComp = new double[200];
		double[] avQuickSortMov = new double[200];
		double[] avMergeSortComp = new double[200];
		double[] avMergeSortMov = new double[200];
		for(int n=1; n <=200; n++)
		{
		    intMergeComps tempMergeSortComp = new intMergeComps();
			intMergeMovs tempMergeSortMov = new intMergeMovs();
			intQuickComps tempQuickSortComp = new intQuickComps();
			intQuickMovs tempQuickSortMov = new intQuickMovs();
			for(int j = 1; j<100*n; j++ )
			{
				int arrForQuickSort[] = Utils.createIntArray(n, 1, n);
				int arrForMergeSort[] = arrForQuickSort.clone();
				QuickSort quicksort = new QuickSort();
				quicksort.quickSort(arrForQuickSort,0,arrForQuickSort.length-1,tempQuickSortComp,tempQuickSortMov);
				MergeSort mergesort = new MergeSort();
				mergesort.mergeSort(arrForMergeSort,0,arrForMergeSort.length-1,tempMergeSortComp,tempMergeSortMov);
			}
			avQuickSortComp[n-1]=(tempQuickSortComp.count/(100*n));
			avQuickSortMov[n-1]=(tempQuickSortMov.count/(100*n));
			avMergeSortComp[n-1]=(tempMergeSortComp.count/(100*n));
			avMergeSortMov[n-1]=(tempMergeSortMov.count/(100*n));
		}
		saveToFile("QuickSortValueComp",avQuickSortComp);
		saveToFile("QuickSortValueMov",avQuickSortMov);
		saveToFile("MergeSortValueComp",avMergeSortComp);
		saveToFile("MergeSortValueMov",avMergeSortMov);
	}
}
