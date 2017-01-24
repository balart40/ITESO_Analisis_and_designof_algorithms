package ADA_pkg;

class result{
	int r;
	int c;
}

public class euclids_algorithm {
	int counter = 0;
	result res = new result();
	public result gdc(int a, int b, int counter) 
	{
		if (b==0) 
		{
			res.r=a; 
			res.c=counter; 
			return res;
		}
		else 
		{
			counter+=1; 
			return gdc(b, a%b, counter);
		}
	}
}
