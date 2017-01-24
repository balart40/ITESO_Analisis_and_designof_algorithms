package ADA_pkg;
import ADA_pkg.euclids_algorithm;
public class testing_euclid {

	public static  int fib(int n)
	{
		int a = 1;
		int b = 1;
		int c = 0;
		if (n==0)
		{
			return 0;
		}
		if ((n==1) || (n==2))
		{
			return 1;
		}
		else
		{
			for(int i=2; i<n;i++)
			{
				c =  a+b;
				a=b;
				b=c;
			}
			return c;
		}
	}
	
	public  static void main(String[] args) {
		euclids_algorithm eu = new euclids_algorithm();
		int n = 38;
		int a = fib(n);
		int b = fib(n-1);
		result euGdc = eu.gdc(a, b,0);
		System.out.println("N= "+n+"\nA = "+a+" B = "+b);
	    System.out.println("Result gdc= "+euGdc.r+" Iterations= "+euGdc.c);
	    System.out.println("\nLog_2(n*a) = "+Math.log10(n*a)/Math.log10(2));
	    System.out.println("Log_10(n*a) = "+Math.log10(n*a));
	    System.out.println("\nLog_2(N) = "+Math.log10(n)/Math.log10(2));
	    System.out.println("Log_10(N) = "+Math.log10(n));
	    System.out.println("\nLog_2(fib(N+1)) = "+Math.log10(fib(n+1))/Math.log10(2));
	    System.out.println("Log_10(fib(n+1)) = "+Math.log10(fib(n+1)));
	}
}
