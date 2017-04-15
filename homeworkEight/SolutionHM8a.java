package ADA_pkg;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


public class SolutionHM8a {
	
	static void findPossibleCombinations(int sum, int n, int pos, List<Integer> combination, int optimalSolution, int[] coins,List<List<Integer>> solutions) 
	{
        for (int i = pos; i < coins.length; i++) 
        {
            int coin = coins[i];
            sum += coin; // Add the coin to the sum
            // If the sum is larger than n, then we have reached an invalid combination.
            if ((sum > n)) 
            {
                return;
            }
            combination.add(coin); // Add the coin to the current combination
            // If the sum is equal to n, then we have reached a valid
            // combination. Return from the recursive call
            // because any continuation would be unnecessary as adding more
            // coins or a larger coin would cause the sum to be larger than n.
            if ((sum == n) && (combination.size()==optimalSolution)) 
            {
            	List<Integer> tempCombination = new ArrayList<>(combination);
            	Collections.sort(tempCombination);
            	Collections.reverse(tempCombination);
            	/**for(int coinn=0;coinn<tempCombination.size();coinn++)
            	{
            		if(coinn!=tempCombination.size()-1)
            		{
            			System.out.print(tempCombination.get(coinn)+" ");
            		}
            		else
            		{
            			System.out.println(tempCombination.get(coinn));
            		}
            	}**/
            	solutions.add(tempCombination);
            	combination.remove(combination.size() - 1);
                return;
            }
            findPossibleCombinations(sum, n, pos, combination, optimalSolution, coins, solutions);
            // Remove the last coin
            combination.remove(combination.size() - 1);
            sum -= coin; // remove the coin from the sum
            pos++;
        }
    }
	
	public static void main(String[] args) {
		// Lets grab stuff from the console..
		Scanner sc = new Scanner(System.in);
		// Grab number of coins
		int numOfCoins = sc.nextInt();
		// Grab de change we want of
		int changeOf = sc.nextInt();
		int[] valueOfCoins =  new int[numOfCoins];
		for(int i=0;i<numOfCoins;i++)
		{
			valueOfCoins[i] = sc.nextInt();
		}
		sc.close();
		int[] M = valueOfCoins;
		int C = changeOf;
		int[][] mat =  new int[M.length][C+1];
		for(int m=0; m<M.length;m++)
		{
			for(int c=1; c<=C; c++)
			{
				if(m==0)
				{
					mat[m][c] = c;
				}
				else if(c==M[m])
				{
					mat[m][c]=1;
				}
				else if(c<M[m])
				{
					mat[m][c]=mat[m-1][c];
				}
				else
				{
					int nuevo  = mat[m][c-M[m]]+1;
					if(nuevo < mat[m-1][c])
					{
						mat[m][c]= nuevo;
					}
					else
					{
						mat[m][c]=mat[m-1][c];
					}
				}
			}
		}
		int optimalSolution = mat[M.length-1][C];
		List<List<Integer>> solutions = new ArrayList<List<Integer>>();
		findPossibleCombinations(0, C, 0, new ArrayList<Integer>(),optimalSolution, M, solutions);
		Collections.sort(solutions, new Comparator<List<Integer>>() {
		    @Override
		    public int compare(List<Integer> o1, List<Integer> o2) {
		        // Sort the lists using the starting position (second element in the list)
		        return o1.get(1).compareTo(o2.get(1));
		    }
		});
		for(List<Integer> psolution : solutions)
		{
			for(int item=0;item<psolution.size();item++)
			{
				if(item==psolution.size()-1)
				{
					System.out.print(psolution.get(item));
				}
				else
				{
					System.out.print(psolution.get(item)+" ");
				}
			}
			System.out.println();
		}
	}
}
