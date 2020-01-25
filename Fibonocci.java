class Fibonocci{
	public static void main(String[] args) {
		int num=1,fab=1,n=1;
		System.out.print(fab+",");
		System.out.print(fab+",");
		while(fab<=89)
		{
			
    		fab=fab+n;
    		n=fab-n;
    		
  

    		System.out.print(fab+",");
		}
		
	}

}