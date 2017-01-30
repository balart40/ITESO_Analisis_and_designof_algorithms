
package ADA_pkg;

import java.util.Arrays;
import java.util.Random;

public class Utils {
	public static int[] createIntArray(int N, int min, int max)
	{
		int [] array = new int[N];
		Random r =  new Random();
		//r.setSeed(1234);
		for (int i=0; i<array.length; i++)
		{
			array[i] =  r.nextInt(max+1);
		}
		return array;
	}
	
	public static void printArray(int [] array)
	{
		System.out.println(Arrays.toString(array));
	}
	
	public static void swap(int [] array, int index1, int index2)
	{
		if (((index1>=0) && (index1<array.length)) && ((index2>=0) && (index2<array.length)))
		{
			int temp = array[index2];
			array[index2]=array[index1];
			array[index1]=temp;
		}
	}
	
	public static boolean isSorted(int[] array)
	{
		for(int i=0; i<array.length-1;i++)
		{
			if (array[i]>array[i+1])
			{
				return false;
			}
		}
		return true;
	}
	
	public static void main (String [] args)
	{
		int [] arr =  createIntArray(10, -100, 100);
		printArray(arr);
		swap(arr, 0, 9);
		System.out.println("swap");
		printArray(arr);
	}
	
}
