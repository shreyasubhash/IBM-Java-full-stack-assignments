class BinEq{
	public static void main(String[] args) {
		float num1;
		int num,a=0,i=1,b;
	
		num1=Float.parseFloat(args[0]);
		num=(int)num1;
		num1=num1-num;
		for (;num>0;)
		{

			b=num%2;
			num=num/2;
			a=a+b*i;
			i=i*10;
		}
		a=a+num*i;
		System.out.print(a+".");
		i=1;
		int d;
		for(int j=0;j<20;j++)
		{
			num1=num1*2;
			d=(int) num1;

			System.out.print(d);
			if(d==1)
			num1=num1-d;

		}
		
	}
			
}
