package ADA_pkg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayDeque;
import java.util.Collections;

// con modificaciones de  http://stackoverflow.com/questions/10269300/convert-int-into-arraylist
// http://stackoverflow.com/questions/2920315/permutation-of-array
public class examenEjercicioDos 
{
	
    static String names[] = {"A", "B", "C", "D", "E", "F", "G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	static HashMap<String, Integer> map = new HashMap<String, Integer>();
	static
	{
		for(int i =0; i <names.length; i++)
		{
			map.put(names[i], i);
		}
	}
	
	static class block
	{
		int indice=0;
		int peso=0;
		int capacidad=0;
		int capacidadDisponible = 0;
		double pesoEcap = 0.0;
		public block(int idx, int p, int c)
		{
			this.indice = idx;
			this.peso = p;
			this.capacidad  = c;
			this.capacidadDisponible = c-p;
			this.pesoEcap = (double) p/c;
		}
	}// end of class Item
		
	static boolean checkLessThanTwoHundred(int[] pesos)
	{
		boolean isLessThanTwoHundred = true;
		for(int i=0; i<pesos.length;i++)
		{
			if(pesos[i]>200)
			{
				isLessThanTwoHundred=false;
				return isLessThanTwoHundred;
			}
		}
		return isLessThanTwoHundred;
	}
	
	static boolean isPositiveIntGreaterThanItsWeightAndLessThanThreeHundred(int[] pesos, int[] capacidades)
	{
		boolean isPosGThanWandLessThanThreeHundred = true;
		for(int i=0;i<pesos.length;i++)
		{
			if((!(capacidades[i] == (int) capacidades[i])) && (capacidades[i]<pesos[i]) && (capacidades[i]>300))
			{
				isPosGThanWandLessThanThreeHundred=false;
				return isPosGThanWandLessThanThreeHundred;
			}
		}
		return isPosGThanWandLessThanThreeHundred;
	}
	
	static boolean blockCapDispIsRepeated(block myBlock, List<block> solution)
	{
		List<block> tempSolution = new ArrayList(solution);
		boolean isRepeated = false;
		for(int i=0;i<solution.size();i++)
		{
			if(myBlock.capacidadDisponible==tempSolution.get(i).capacidadDisponible)
			{
				isRepeated = true;
				return isRepeated;
			}
		}
		return isRepeated;
	}
	
	static int select(List<block> C)
	{
		int index = -1;
		int maxCapDisp = 0;
		for(int i=0;i<C.size();i++)
		{
			block item = C.get(i);
			int capDisp = item.capacidadDisponible;
			//System.out.println(capDisp);
			if(capDisp>maxCapDisp)
			{
				maxCapDisp = capDisp;
				index = i;
			}
		}
		return index;
	}// end of function select
	
	static boolean pesoEsValido(block blockToCheck, List<block> S)
	{
		boolean isValid = true;
		for(int i=0;i<S.size();i++)
		{
			if(blockToCheck.peso>S.get(i).capacidadDisponible)
			{
				return false;
			}
		}
		return isValid;
	}
	
	static void actualizasCapDisp(int pesoAañadir, List<block> S)
	{
		for(int i=0;i<S.size();i++)
		{
			S.get(i).capacidadDisponible-=pesoAañadir;
		}
	}	
	
	
	static int[] obtenerTorreGreedy (int[] pesos, int[] capacidades, int solucion, int[] solucionArr)
	{
		int[] resultado =null;
		boolean isLessThanTwoHundredCheck = false;
		boolean isIntPosGreaterThanWeightAndLessThanThreeHundred = false;
		List<block> myListOfBlocks = new ArrayList<block>();
		// Primera restriccion: 1)	No se esperan más de 30 bloques de entrada.
		int maximaCapacidad = 0;
		if(pesos.length>30 || capacidades.length>30)
		{
			// return null
			return resultado;
		}
		// segunda restricción: 2)	Todos los pesos son enteros positivos menores que 200.
		isLessThanTwoHundredCheck = checkLessThanTwoHundred(pesos);
		if(!isLessThanTwoHundredCheck)
		{
			// return null
			return resultado;
		}
		// tercera restriccion 3) La capacidad de un bloque es un entero positivo mayor a su peso y no mayor que 300
		isIntPosGreaterThanWeightAndLessThanThreeHundred = isPositiveIntGreaterThanItsWeightAndLessThanThreeHundred(pesos, capacidades);
		if(!isIntPosGreaterThanWeightAndLessThanThreeHundred)
		{
			// return null
			return resultado;
		}
		// for processing data we will create an object which we will use for merge sort
		// this way we will have the maximum tower plus with nlogn (V) (;....;) (V)
		for(int i=0;i<pesos.length;i++)
		{
			block myNewBlock =  new block(i, pesos[i],capacidades[i]);
			myListOfBlocks.add(myNewBlock);
		}
		//********* knap sack  ********
		List<block> S = new ArrayList<block>(myListOfBlocks.size());
		int capacidadAlmacenada = 0;
		//int idxOfFirstBlock = 0;
		block firstBlock = myListOfBlocks.remove(0);
		maximaCapacidad = firstBlock.capacidadDisponible;
		S.add(firstBlock);
		/*System.out.println("\nmax capacidad "+maximaCapacidad);
		System.out.println("From block "+names[firstBlock.indice]);
		System.out.print("\nexisting blocks ");
		for(int i=0;i<myListOfBlocks.size();i++)
		{
			System.out.print(names[myListOfBlocks.get(i).indice]+" ");
		}*/
		while(capacidadAlmacenada<maximaCapacidad || !myListOfBlocks.isEmpty())
		{
			int k =  select(myListOfBlocks);
			if(k<0)
			{
				break;
			}
			//System.out.println(k);
			// da y remueve item
			block x = myListOfBlocks.remove(k);
			//boolean isRepeated = blockCapDispIsRepeated(x, S);
			// todavia hay espacio
			boolean elPesoEsValido = pesoEsValido(x, S);
			if((elPesoEsValido))
			{
				capacidadAlmacenada+=x.peso;
				actualizasCapDisp(x.peso, S);
				S.add(x);
			}
			// Si el elemento rebasa el espacio es descartado y seguimos
			else
			{
				continue;
			}
		}
		resultado = new int[S.size()];
		for(int i=0;i<S.size();i++)
		{
			resultado[i]=S.get(i).indice;
		}
		if(resultado.length>solucion)
		{
			solucion = resultado.length;
			//System.out.println("Solucion so far = "+solucion);
			return resultado;
		}
		return solucionArr;
	}
    
	static int factorial(int n)
    {
    	int factorial = n;
    	for(int i=n;i>=1;i--)
    	{
    		factorial*=i;
    	}
    	return factorial;
    }
	
	static void permute(List<Integer> arr, int k, List<List<Integer>> permutations)
    {
        for(int i = k; i < arr.size(); i++)
        {
            java.util.Collections.swap(arr, i, k);
            permute(arr, k+1, permutations);
            java.util.Collections.swap(arr, k, i);
        }
        if (k == arr.size() -1)
        {
	    	List<Integer> tempPerm = new ArrayList<>(arr.size());
	    	for(int i=0; i<arr.size();i++)
	    	{
	    		tempPerm.add(arr.get(i));
	    	}
        	permutations.add(tempPerm);
        }
    }
	
	static int maximaAlturaBT(int[] pesos, int[] capacidades)
	{
		int solucion = 0;
		int[] soluciontArr = new int[pesos.length];
		List<Integer> pesosListInt = new ArrayList<>(pesos.length);
		// get indices
		for(int i=0; i<pesos.length;i++)
		{
			pesosListInt.add(i);
		}
		List<List<Integer>> permutations = new ArrayList<List<Integer>>(factorial(pesos.length));
		// get all permutations with no repetition
		permute(pesosListInt, 0,permutations);
		 for(List<Integer> permutation : permutations)
	     {
	      	int[] tempPesos =  new int[pesos.length];
	      	int[] tempCapacidades = new int[pesos.length]; 
			for(int i=0; i<permutation.size();i++)
			{
				tempPesos[i] = pesos[permutation.get(i)];
			    tempCapacidades[i] = capacidades[permutation.get(i)];
			}
			//System.out.println("\nOperando con permutacion ");
			//System.out.println("pesos: "+Arrays.toString(tempPesos));
			//System.out.println("Capacidades: "+Arrays.toString(tempCapacidades));
			soluciontArr = obtenerTorreGreedy (tempPesos, tempCapacidades, solucion,soluciontArr);
			solucion=soluciontArr.length;
			//System.out.println("Solucion: "+solucion);
	     }
		return solucion;
	}
	
	public static void main(String[] args)
	{
		//int[] pesos = {1,5,3};
		//int [] capacidades = {7,10,4};
		int [] pesos = {4,3,1,5,10,2,7,8};
		int [] capacidades = {6,5,7,6,16,5,9,12};
		int maximaAltura =0;
		System.out.println("Torre    Peso   Capacidad    Capacidad Disponible");
		for(int i=0; i<pesos.length;i++)
		{
			System.out.println(String.format("%s        %d      %d            %d", names[i],pesos[i],capacidades[i],capacidades[i]-pesos[i]));
		}
		int resultado =0;
		resultado = maximaAlturaBT(pesos, capacidades);
		System.out.println("\nTorre Mas alta de: "+resultado);
		
		/*resultado = obtenerTorreGreedy (pesos, capacidades);
		System.out.println("\nTorre Valida "+Arrays.toString(resultado)+"\n");
		for(int i=resultado.length-1;i>=0;i--)
		{
			int idx = resultado[i];
			int capDisp = capacidades[idx]-pesos[idx];
			System.out.println(names[idx]);
		}*/
	}

}
