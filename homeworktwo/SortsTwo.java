package ADA_pkg;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;



public class SortsTwo{
	
	public static int[] shellSort(int[] array)
	{
		int comparaciones = 0;
		int movimientos = 0;
		int k=0;
		int sizeCount = 0;
		// calculate length of knuth gap sequence array
		//System.out.println(array.length);
		for (int i=0;k<array.length;i++)
		{
			k=k*3+1;
			sizeCount+=1;
		}
		sizeCount-=1;
		k=0;
		int[] KnuthGapSequence = new int[sizeCount];
		for (int i=sizeCount-1;i>=0;i--)
		{
			k=k*3+1;
			KnuthGapSequence[i] = k;
		}
		//System.out.println(Arrays.toString(KnuthGapSequence));
		// Start with the largest gap for the array of N size
		// and work down to a gap of 1. Note: we work with the indexes
		for(int i=0;i<KnuthGapSequence.length;i++)
		{
			int gap = KnuthGapSequence[i];
			for(int j=gap;j<array.length;j++)
			{
				int temp = array[j];
				comparaciones+=1;
				for(k=j;k>=gap && array[k-gap]>temp;k-=gap )
				{
					array[k] = array[k-gap];
				}
				array[k] = temp;
			}
		}
		return new int[] {comparaciones, movimientos};
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
		double avShellcomp[] =  new double[60];
		double avShellMov[] =  new double[60];
		int arr[] = null;
		// For of all Nth elements from 1 to 200 elements
		for (int i=1000; i<=60000;i+=1000)
		{
			double tempShellcomp = 0;
			double tempShellmov = 0;
			// we will run N/100 times the algorithm
			for(int j=1;j<=i/100;j++)
			{
				arr = Utils.createIntArray(i, 1, i);
				int[] ShellSortResult = shellSort(arr);
				double numCompShellSort = ShellSortResult[0];
				double numMovShellSort = ShellSortResult[1];
				tempShellcomp+=numCompShellSort;
				tempShellmov+=numMovShellSort;
			}
			avShellcomp[i/1000-1] = tempShellcomp/(i/100);
			avShellMov[i/1000-1] = tempShellmov/(i/100);
		}
		// Shell Sort
		System.out.println("Shell Sort");
		System.out.println(Arrays.toString(arr));
		saveToFile("ShellSortValueComp",avShellcomp);
		saveToFile("ShellSortValueMov",avShellMov);
	}// end of main
}
