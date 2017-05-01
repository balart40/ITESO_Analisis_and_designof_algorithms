package ADA_pkg;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

import ADA_pkg.KnapSack.Item;

public class examenEjercicioUno 
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
		double maxCapDisp = 0.0;
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
	
	
	static int [] obtenerTorreGreedy (int[] pesos, int[] capacidades)
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
		int idxOfFirstBlock = select(myListOfBlocks);
		block firstBlock = myListOfBlocks.remove(idxOfFirstBlock);
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
			boolean isRepeated = blockCapDispIsRepeated(x, S);
			// todavia hay espacio
			boolean elPesoEsValido = pesoEsValido(x, S);
			if((elPesoEsValido) && (!isRepeated))
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
		return resultado;
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
		int [] resultado;
		resultado = obtenerTorreGreedy (pesos, capacidades);
		System.out.println("\nTorre Valida "+Arrays.toString(resultado)+"\n");
		for(int i=resultado.length-1;i>=0;i--)
		{
			int idx = resultado[i];
			int capDisp = capacidades[idx]-pesos[idx];
			System.out.println(names[idx]);
		}
	}
	
}
