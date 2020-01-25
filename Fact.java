class Fact{
	public static void main(String[] args) {
		int num,fact=1;
		num=Integer.parseInt(args[0]);
		while(num!=0)
		{
    		fact=fact*num;
    		num--;
		}
		System.out.println("fcatorial of given  no is"+fact);
	}

}