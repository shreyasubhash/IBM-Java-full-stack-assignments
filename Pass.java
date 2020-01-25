class Pass{
	public static void main(String[] args) {
		int n1,n2,n3,sum;
		n1=Integer.parseInt(args[0]);
		n2=Integer.parseInt(args[1]);
		n3=Integer.parseInt(args[2]);
		sum=n1+n2+n3;
		do {
		if(n1<=40) 
		{
			
						System.out.println("failing");
						break;
		}
		if (n2<=40)
		{
			
						System.out.println("failing");
						break;
		}
		if (n3<=40)
		{
			
						System.out.println("failing");
						break;
		}
		if(sum<=125)
		{
			
						System.out.println("failing");
						break;
					}
			System.out.println("Passing");
}
		while(false);
			
	}
}