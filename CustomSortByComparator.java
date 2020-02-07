
//	1. Add New Employee
//		- Enter name
//		- ENter Age
//	Do you want to add more(y/n) :n

//	Sort by:
//	1. First name
//	2. Last name
//	3. Age


import java.util.Scanner;
//import java.io.flush;
class Employee{
	int age;
	String name,namef,namel;
	
	Employee(String name, int age){
		String []n=new String[3];
		this.name = name;
		this.age = age;
		n=name.split(" ");
		namef=n[0];
		if(n.length>1)
		namel=n[1];
	else
		namel=" ";

	}

	public Integer getAge(){
		return this.age;
	}

	public String getName(){
		return this.name;
	}
	public String getNamef(){
		
		return this.namef;
	}
	public String getNamel(){
		
		return this.namel;
	}

	@Override
	public String toString(){
		return this.getName()+" "+this.getAge();
	}
}






class SortByNamef implements java.util.Comparator{
	@Override
	public int compare(Object firstObj, Object secondObj){
		return (((Employee)firstObj).getNamef()).toUpperCase().compareTo(((((Employee)secondObj).getNamef())).toUpperCase());
	}
}
class SortByNamel implements java.util.Comparator{
	@Override
	public int compare(Object firstObj, Object secondObj){
		return (((Employee)firstObj).getNamel()).toUpperCase().compareTo(((((Employee)secondObj).getNamel())).toUpperCase());
	}
}


class SortByAge implements java.util.Comparator{
	@Override
	public int compare(Object firstObj, Object secondObj){
		return ((Employee)firstObj).getAge().compareTo(((Employee)secondObj).getAge());
	}
}


class CustomSortByComparator{
	public static void main(String[] args) {
		//String name;
		int age;
		Scanner scan=new Scanner(System.in);
		java.util.List employeeList = new java.util.ArrayList();
		char c;
		System.out.println("do u like to add new Employee: 1->yes and 2->no");
		StringBuilder ch = new StringBuilder(scan.next());
		c=ch.charAt(0);
		while(c=='y'){
			System.out.println("enter the name");
			//new CustomSortByComparator().flush();
			scan.nextLine();
			String name=scan.nextLine();
			System.out.println("enter age");
			age=scan.nextInt();
			employeeList.add(new Employee(name, age));
			System.out.println("do u like to add new Employee: 1->yes and 2->no");
		ch = new StringBuilder(scan.next());
		c=ch.charAt(0);
		}

		System.out.println("press 1 to sort acc to first name");
		System.out.println("press 2 to sort acc to Last name");
		System.out.println("press 3 to sort acc to age");
		int i=scan.nextInt();
		switch(i){
			case 1:
			System.out.println("List before sorting : " + employeeList);

		java.util.Collections.sort(employeeList, new SortByNamef());

		System.out.println("list after sorting "+employeeList);
		break;
case 2:
			System.out.println("List before sorting : " + employeeList);

		java.util.Collections.sort(employeeList, new SortByNamel());

		System.out.println("list after sorting "+employeeList);
		break;
case 3:
			System.out.println("List before sorting : " + employeeList);

		java.util.Collections.sort(employeeList, new SortByAge());

		System.out.println("list after sorting "+employeeList);
		break;


			
		

		//employeeList.add(new Employee("as gh Sharma", 615));
		//employeeList.add(new Employee("Rashmi", 3421));
		//employeeList.add(new Employee("Abhi", 3));

	}


	}
}


