class Tabels{
	public static void main(String[] args) {
		int num,mul=1,limit,i=1;
		limit=Integer.parseInt(args[1]);
		num=Integer.parseInt(args[0]);
		for(i=1;i<=limit;i++)
		{
			mul=num*i;
			System.out.println(num+"*"+i+"="+mul);
		}
	}
}