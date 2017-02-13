package ADA_pkg;

public class MergeSort {
	// pivot = q
	// right  = r
	// left = p
	// to maintain consistency with the pseudo code of the algorithm depicted
	// in Introduction to algorithms MIT press page 31
	public static void merge(int[] array, int left, int pivot, int right, intMergeComps comps, intMergeMovs movs)
	{
		int n1 = pivot-left+1;
		int n2 = right - pivot;
		if((n1<=0) || (n2<=0))
		{
			return;
		}
		int i;
		int j;
		int[] L = new int[n1];
		int[] R = new int[n2];
		for(i = 0;i<n1;i++)
		{
			L[i] = array[left+i];
		}
		for(j = 0;j<n2;j++)
		{
			R[j] = array[pivot+j+1];
		}
		i = 0;
		j = 0;
		for(int k=left;k<=right;k++)
		{
			comps.count++;
			if(i<L.length && j<R.length)
			{
				if (L[i]<=R[j])
				{
					array[k] = L[i];
					movs.count++;
					i++;
				}
				else
				{
					array[k] = R[j];
					movs.count++;
					j++;
				}
			}
			else
			{
				if(i == L.length && j < R.length)
				{
					array[k] = R[j];
					movs.count++;
					j++;
				}
				if(j == R.length && i < L.length)
				{
					array[k] = L[i];
					movs.count++;
					i++;
				}
			}
		}
	}

		public void mergeSort(int[] array, int left, int right, intMergeComps comp, intMergeMovs mov)
		{
			if(left<right)
			{
				int pivot  = Math.floorDiv(left+right, 2);
				mergeSort(array,left,pivot,comp, mov);
				mergeSort(array,pivot+1,right,comp,mov);
				merge(array,left,pivot,right,comp,mov);
			}
		}
	
	
	
	
}
