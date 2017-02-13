package ADA_pkg;

public class QuickSort {

	public static int Partition(int[] array, int left, int right, intQuickComps comps, intQuickMovs swaps)
	{
		int x = array[right];
		int i = left -1;
		// the pseudo code in the book show from j to r-1, in a for that is
		// j<right again
		// need to be carefull about the bounds being inclusive or not
		// as well as the language program regarding arrays if its
		//1--N or 0-N-1 fashion
		for(int j=left;j<right;j++)
		{
			comps.count++;
			if(array[j]<=x)
			{
				i++;
				Utils.swap(array, i, j);
				swaps.count++;
			}
		}
		Utils.swap(array, i+1, right);
		swaps.count++;
		return i+1;
	}
	
	// From introduction to algorithms MIT press Page 171
	// left = p
	// right = r
	// pivot = q
	// Assuming an array A[left...........right]
	public void quickSort(int[] array, int left, int right, intQuickComps comps, intQuickMovs swaps)
	{
		if (left<right)
		{	
			int pivot = Partition(array, left, right, comps, swaps);
			// Here we divide de array to sort to
			// A[left......pivot-1]
			quickSort(array, left, pivot-1, comps, swaps);
			// and A[pivot+1......r]
			quickSort(array,pivot+1,right, comps, swaps);
		}
	}
	
	
}
