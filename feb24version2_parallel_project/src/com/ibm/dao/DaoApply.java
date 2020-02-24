////package com.ibm.dao;
////
////import java.sql.Connection;
////import java.sql.DriverManager;
////import java.sql.PreparedStatement;
////import java.sql.ResultSet;
////import java.sql.SQLException;
////import java.sql.Timestamp;
////import java.util.ArrayList;
////
////import com.ibm.bean.Customer;
////import com.ibm.bean.Transaction;
////
////public class DaoApply implements DaoClass {
////
////Connection dbCon;
////PreparedStatement pstmt;
////
////public DaoApply(){
////
////try {
////dbCon = DriverManager.getConnection("jdbc:mysql://localhost:3307/banking?serverTimezone=UTC", "root",
////"");
////} catch (SQLException e) {
////System.out.println("Error connecting database..." + e.getMessage());
////}
////
////}
////public int fetchCustID(Customer c) {
////int k = 0;
////String fetchQry = "select * from customer where phno=?";
////



package com.ibm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.ibm.bean.Customer;
import com.ibm.bean.Transaction;

public class DaoApply implements DaoClass {
	
	Connection dbCon;
	PreparedStatement pstmt;
	
	public DaoApply(){
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		dbCon = DriverManager.getConnection("jdbc:mysql://localhost:3307/banking?serverTimezone=UTC", "root",
				"");
	} catch (SQLException e) {
		System.out.println("Error connecting database..." + e.getMessage());
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		System.out.println("class not found");
	}

	}
	public int fetchCustID(Customer c) {
		int k = 0;
		String fetchQry = "select * from customer where phno=?";

		try {
		pstmt = dbCon.prepareStatement(fetchQry);
		pstmt.setString(1, c.getPhone());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
		k = rs.getInt("cust_id");
		}

		} catch (SQLException e) {
		
		e.printStackTrace();
		}
		return k;
		}
	
	@Override
	public boolean isAccountExist(int to_account) {
	
		String fetchQry = "select * from account";

		try {
		pstmt = dbCon.prepareStatement(fetchQry);
		//pstmt.setString(1, c.getPhone());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
		if(rs.getInt("account_no")==to_account) 
			return true;

		}
		
		}
		catch (SQLException e) {
		
		e.printStackTrace();
		}
		return false;
		}

		
	
	
	
	
	
	
	public int fetchAccountNumber(Customer c) {
		int k = 0,m=0;
		if (c.getId() == 0)
		k = this.fetchCustID(c);
		else
		k = c.getId();

		String fetchQry = "select * from account where cust_id="+k;

		try {
		pstmt = dbCon.prepareStatement(fetchQry);
	
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
		m = rs.getInt("account_no");
		}

		} catch (SQLException e) {
		
		e.printStackTrace();
		}
		return m;
		}

		@Override
		public boolean createAccount(Customer c) {
		// Write the query to insert a new row in table
		String CinsertQry = "insert into customer(password,cust_name,dob,phno,email) values(?,?,?,?,?)";
		String AinsertQry = "insert into account(cust_id,balance,balance_s) values(?,?,?)";

		// Create the Statement
		try {

		pstmt = dbCon.prepareStatement(CinsertQry);
		pstmt.setString(1, c.getPassword());
		pstmt.setString(2, c.getName());
		pstmt.setString(3,  c.getDob());
		pstmt.setString(4, c.getPhone());
		pstmt.setString(5, c.getEmail());

		// Execute the query
		if (pstmt.executeUpdate() > 0) {
		
System.out.println("reg to cu");
		} else {
		
		return false;
		}

		pstmt = dbCon.prepareStatement(AinsertQry);

		pstmt.setInt(1, new DaoApply().fetchCustID(c));
		pstmt.setInt(2, c.a.getBalance());
		if (c.a.getBalance() < 500)
		pstmt.setBoolean(3, false);
		else
		pstmt.setBoolean(3, true);

		if (pstmt.executeUpdate() > 0) {
		return true;
		} else {
		
		return false;
		}

		} catch (SQLException e) {
		System.out.println("Issues creating the statement :" + e.getMessage());
		}

		return false;
		}

		@Override
		public boolean login(Customer c) {
		
		String qryFetch = "select * from customer";

		PreparedStatement pstmt;
		try {
		pstmt = dbCon.prepareStatement(qryFetch);

		// Execute the query
		ResultSet rs = pstmt.executeQuery();

		// Traverse through the data
		while (rs.next()) {
		if (rs.getInt("cust_id") == c.getId() && rs.getString("password").trim().equals(c.getPassword()))
		return true;
		}
		} catch (SQLException e) {
		System.out.println("Error in logging in: " + e.getMessage());
		}
		return false;
		}

		@Override
		public ArrayList<Customer> viewBalance(Customer c) {
		ArrayList<Customer> details = new ArrayList<Customer>();

		int k = 0;
		if (c.getId() == 0)
		k = this.fetchCustID(c);
		else
		k = c.getId();
		String fetchQry1 = "SELECT * FROM `customer` WHERE cust_id=" + k;
		String fetchQry2 = "SELECT * FROM `account` WHERE cust_id=" + k;

		try {
		pstmt = dbCon.prepareStatement(fetchQry1);

		ResultSet rs = pstmt.executeQuery(fetchQry1);
		Customer t = new Customer();
		while (rs.next()) {
		t.setId(rs.getInt("cust_id"));
		t.setName(rs.getString("cust_name"));
		t.setDob(rs.getString("dob"));
		t.setPhone(rs.getString("phno"));
		t.setEmail(rs.getString("email"));

		}

		pstmt = dbCon.prepareStatement(fetchQry2);

		rs = pstmt.executeQuery(fetchQry2);

		while (rs.next()) {

		t.a.setAccount_no(rs.getInt("account_no"));
		t.a.setBalance(rs.getInt("balance"));

		}
		details.add(t);

		} catch (SQLException e) {
		System.out.println("Issues creating the statement :" + e.getMessage());
		
		}
		return details;

		}

		@Override
		public boolean isLowBalance(Customer c) {

		int k = 0;
		if (c.getId() == 0)
		k = this.fetchCustID(c);
		else
		k = c.getId();

		String query = "Select * from account where cust_id=" + k;
		try {
		pstmt = dbCon.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
		if (rs.getBoolean(4) == false)
		return true;
		else
		return false;
		}
		} catch (SQLException e) {
		System.out.println("Error in check balance : " + e.getMessage());
		return false;

		}
		return false;

		}

		@Override
		public boolean deposit(Customer c, int amount) {
		int k = 0;
		if (c.getId() == 0)
		k = this.fetchCustID(c);
		else
		k = c.getId();

		String query = "select * from account where cust_id= " + k;
		int amt = 0;

		try {
		pstmt = dbCon.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		int acc = 0;
		// Execute the query
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
		if (rs.getInt("cust_id") == k) {
		amt = amount + rs.getInt("balance");
		acc = rs.getInt("account_no");
		rs.updateInt(3, amt);
		if (amt > 500)
			rs.updateBoolean(4, true);
		else
			rs.updateBoolean(4, false);
		rs.updateRow();
		
		}
		}
		String transact = "Insert into transaction values (" + acc + "," + acc + "," + " 'Deposit' ," + amount
		+ ",? )";
		pstmt = dbCon.prepareStatement(transact);
		pstmt.setTimestamp(1, new Timestamp(new java.util.Date().getTime()));
		if (pstmt.executeUpdate() > 0)
				return true;

		} catch (SQLException e) {
		System.out.println("Error in depositting : " + e.getMessage());
		}
			return false;
		}

		@Override
		public boolean withdrawal(Customer c, int amount) {
		int k = 0;
		if (c.getId() == 0)
		k = this.fetchCustID(c);
		else
		k = c.getId();

		String query = "select * from account where cust_id= " + k;
		int amt = 0;

		try {
		pstmt = dbCon.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		int acc = 0;
		// Execute the query
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
		if (rs.getInt("cust_id") == k) {
		if (rs.getInt("balance") >= amount) {

		amt = rs.getInt("balance") - amount;
		acc = rs.getInt("account_no");
		rs.updateInt(3, amt);

		if (amt > 500)
		rs.updateBoolean(4, true);
		else
		rs.updateBoolean(4, false);

		rs.updateRow();
		} else {
	
		return false;
		}
		}
		}
		String transact = "Insert into transaction values (" + acc + "," + acc + "," + " 'Withdrawal' ," + amount
		+ ",? )";
		pstmt = dbCon.prepareStatement(transact);
		pstmt.setTimestamp(1, new Timestamp(new java.util.Date().getTime()));
		if (pstmt.executeUpdate() > 0)
		
		return true;

		} catch (SQLException e) {
		System.out.println("Error in withdrawing : " + e.getMessage());
		
		}
		return false;
		}

//		@Override
//		public boolean send(Customer c, int account_no_to, int amount) {
//			// TODO Auto-generated method stub	
//			int k = 0;
//			if (c.getId() == 0)
//			k = this.fetchCustID(c);
//			else
//			k = c.getId();
//
//			String query = "select * from account where cust_id= " + k;
//			int amt = 0;
//
//			try {
//			pstmt = dbCon.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//			int acc=0;
//			// Execute the query
//			ResultSet rs = pstmt.executeQuery();
//			while (rs.next()) {
//			if (rs.getInt("cust_id") == k) {
//			if (rs.getInt("balance") >= amount) {
//
//			amt = rs.getInt("balance") - amount;
//			acc = rs.getInt("account_no");
//			rs.updateInt(3, amt);
//			if (rs.getInt("balance") > 500)
//			rs.updateBoolean(4, true);
//			else
//			rs.updateBoolean(4, false);
//			rs.updateRow();
//
//			}
//			}
//			}
//			String transact = "Insert into transaction values (" + acc + "," + account_no_to + "," + " 'Sent' ," + amount+ ",? )";
//			pstmt = dbCon.prepareStatement(transact);
//			pstmt.setTimestamp(1, new Timestamp(new java.util.Date().getTime()));
//			if (pstmt.executeUpdate() > 0)
//			System.out.println("Successfully Sent...");
//			else
//			System.out.println("not sent..");
//
//			query = "select * from account where account_no= " + account_no_to;
//			pstmt = dbCon.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//
//			// Execute the query
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//			if (rs.getInt("account_no") == account_no_to) {
//			amt = rs.getInt("balance") + amount;
//			rs.updateInt(3, amt);
//			if (rs.getInt("balance") > 500)
//			rs.updateBoolean(4, true);
//			else
//			rs.updateBoolean(4, false);
//			rs.updateRow();
//			}
//			}
//			transact = "Insert into transaction values (" + acc + "," + account_no_to + "," + "'Recieve' ,"+ amount + ", ? )" ;
//			pstmt = dbCon.prepareStatement(transact);
//			pstmt.setTimestamp(1, new Timestamp(new java.util.Date().getTime()));
//
//
//			if (pstmt.executeUpdate() > 0)
//			System.out.println("Successfully recieve...");
//			else
//			System.out.println("not recieve..");
//
//			return true;
//
//			} catch (SQLException e) {
//			System.out.println("Error in depositting : " + e.getMessage());
//			return false;
//			}
//			}

		
		
	public boolean send(Customer c, int account_no_to, int amount) {
int a_t=account_no_to;
	String q = "select * from account where account_no = "+a_t;
	try {
		pstmt = dbCon.prepareStatement(q);
		ResultSet rs1 = pstmt.executeQuery();
		System.out.println("exctr query");
		while(!rs1.next()) {
			System.out.println("invalid acc no");
			return false;
		}
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		//e1.printStackTrace();
		
	}
	
// TODO Auto-generated method stub
int k = 0;
if (c.getId() == 0)
k = this.fetchCustID(c);
else
k = c.getId();

String query = "select * from account where cust_id= " + k;
int amt = 0;

try {
pstmt = dbCon.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
int acc=0;
// Execute the query
ResultSet rs = pstmt.executeQuery();

while (rs.next()) {
	System.out.println("inside while");
if (rs.getInt(2) == k) {
	System.out.println("cust id"+k);
if (rs.getInt("balance") >= amount) {

amt = rs.getInt("balance") - amount;
acc = rs.getInt("account_no");
System.out.println("cust occ"+acc);
rs.updateInt(3, amt);
if (rs.getInt("balance") > 500)
rs.updateBoolean(4, true);
else
rs.updateBoolean(4, false);
System.out.println("updating row");
rs.updateRow();
System.out.println("updated row");

}
}
}
String transact = "Insert into transaction values (" + acc + "," + account_no_to + "," + " 'Sent' ," + amount+ ",? )";
pstmt = dbCon.prepareStatement(transact);
pstmt.setTimestamp(1, new Timestamp(new java.util.Date().getTime()));
if (pstmt.executeUpdate() > 0)
System.out.println("Successfully Sent...");
else
System.out.println("not sent..");
k=account_no_to;
System.out.println(k);
query= "select * from account where account_no= " +k;
pstmt = dbCon.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

// Execute the query
ResultSet rs2 = pstmt.executeQuery();
while (rs2.next()) {
if (rs2.getInt("account_no") == account_no_to) {
amt = rs2.getInt("balance") + amount;
System.out.println(amt);
System.out.println(amount);
System.out.println(amt);
rs2.updateInt(3, amt);
if (rs2.getInt("balance") > 500)
rs2.updateBoolean(4, true);
else
rs2.updateBoolean(4, false);
rs2.updateRow();
}
}
transact = "Insert into transaction values (" + acc + "," + account_no_to + "," + "'Recieve' ,"+ amount + ", ? )" ;
pstmt = dbCon.prepareStatement(transact);
pstmt.setTimestamp(1, new Timestamp(new java.util.Date().getTime()));


if (pstmt.executeUpdate() > 0)
System.out.println("Successfully recieve...");
else
System.out.println("not recieve..");

return true;

} catch (SQLException e) {
System.out.println("Error in depositting : " + e.getMessage());
return false;
}
}
		
		
		
		@Override
		public ArrayList<Transaction> printTransaction(Customer c) {
		
			
			int k=this.fetchAccountNumber(c);
		String qryFetch = "select * from transaction where from_acc="+k;
		ArrayList<Transaction> details = new ArrayList<Transaction>();

		PreparedStatement pstmt;
		try {
		pstmt = dbCon.prepareStatement(qryFetch);

		// Execute the query
		ResultSet rs = pstmt.executeQuery();

		// Traverse through the data
		while (rs.next()) {
		if (rs.getInt("from_acc") == k) {
			Transaction t = new Transaction();
			
		t.setAmt(rs.getInt("amount"));
		t.setDtime(rs.getTimestamp("date_time"));
		t.setFrom_acc(rs.getInt("from_acc"));
		t.setTo_acc(rs.getInt("to_acc"));
		t.setTran_type(rs.getString("type"));
		details.add(t);
		}
		}
		} catch (SQLException e) {
		System.out.println("Error in transaction printing in: " + e.getMessage());
		}
		return details;

		}

		}
////try {
////pstmt = dbCon.prepareStatement(fetchQry);
////pstmt.setString(1, c.getPhone());
////ResultSet rs = pstmt.executeQuery();
////while (rs.next()) {
////k = rs.getInt("cust_id");
////}
////
////} catch (SQLException e) {
////// TODO Auto-generated catch block
////e.printStackTrace();
////}
////return k;
////}
////public int fetchAccountNumber(Customer c) {
////int k = 0,m=0;
////if (c.getId() == 0)
////k = this.fetchCustID(c);
////else
////k = c.getId();
////
////String fetchQry = "select * from account where cust_id="+k;
////
////try {
////pstmt = dbCon.prepareStatement(fetchQry);
////
////ResultSet rs = pstmt.executeQuery();
////while (rs.next()) {
////m = rs.getInt("account_no");
////}
////
////} catch (SQLException e) {
////// TODO Auto-generated catch block
////e.printStackTrace();
////}
////return m;
////}
////
////@Override
////public boolean createAccount(Customer c) {
////// Write the query to insert a new row in table
////String CinsertQry = "insert into customer(password,cust_name,dob,phno,email) values(?,?,?,?,?)";
////String AinsertQry = "insert into account(cust_id,balance,balance_s) values(?,?,?)";
////
////// Create the Statement
////try {
////
////pstmt = dbCon.prepareStatement(CinsertQry);
////pstmt.setString(1, c.getPassword());
////pstmt.setString(2, c.getName());
////pstmt.setDate(3, (java.sql.Date) c.getDob());
////pstmt.setString(4, c.getPhone());
////pstmt.setString(5, c.getEmail());
////
////// Execute the query
////if (pstmt.executeUpdate() > 0) {
////System.out.println("Successfully inserted a new row in customer...");
////
////} else {
////System.out.println("Some issues while inserting in customer...");
////return false;
////}
////
////pstmt = dbCon.prepareStatement(AinsertQry);
////
////pstmt.setInt(1, new DaoApply().fetchCustID(c));
////pstmt.setInt(2, c.a.getBalance());
////if (c.a.getBalance() < 500)
////pstmt.setBoolean(3, false);
////else
////pstmt.setBoolean(3, true);
////
////if (pstmt.executeUpdate() > 0) {
////System.out.println("Successfully inserted a new row account in ...");
////return true;
////} else {
////System.out.println("Some issues while inserting in account...");
////return false;
////}
////
////} catch (SQLException e) {
////System.out.println("Issues creating the statement :" + e.getMessage());
////}
////
////return false;
////}
////
////@Override
////public boolean login(Customer c) {
////// TODO Auto-generated method stub
////String qryFetch = "select * from customer";
////
////PreparedStatement pstmt;
////try {
////pstmt = dbCon.prepareStatement(qryFetch, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
////
////// Execute the query
////ResultSet rs = pstmt.executeQuery();
////
////// Traverse through the data
////while (rs.next()) {
////if (rs.getInt("cust_id") == c.getId() && rs.getString("password").trim().equals(c.getPassword()))
////return true;
////}
////} catch (SQLException e) {
////System.out.println("Error in logging in: " + e.getMessage());
////}
////return false;
////}
////
////@Override
////public ArrayList<Customer> viewBalance(Customer c) {
////ArrayList<Customer> details = new ArrayList<Customer>();
////
////int k = 0;
////if (c.getId() == 0)
////k = this.fetchCustID(c);
////else
////k = c.getId();
////String fetchQry1 = "SELECT * FROM `customer` WHERE cust_id=" + k;
////String fetchQry2 = "SELECT * FROM `account` WHERE cust_id=" + k;
////
////try {
////pstmt = dbCon.prepareStatement(fetchQry1);
////
////ResultSet rs = pstmt.executeQuery(fetchQry1);
////Customer t = new Customer();
////while (rs.next()) {
////t.setId(rs.getInt("cust_id"));
////t.setName(rs.getString("cust_name"));
////t.setDob(rs.getDate("dob"));
////t.setPhone(rs.getString("phno"));
////t.setEmail(rs.getString("email"));
////
////}
////
////pstmt = dbCon.prepareStatement(fetchQry2);
////
////rs = pstmt.executeQuery(fetchQry2);
////
////while (rs.next()) {
////
////t.a.setAccount_no(rs.getInt("account_no"));
////t.a.setBalance(rs.getInt("balance"));
////
////}
////details.add(t);
////
////} catch (SQLException e) {
////System.out.println("Issues creating the statement :" + e.getMessage());
////// details.add(100);
////}
////return details;
////
////}
////
////@Override
////public boolean isLowBalance(Customer c) {
////
////int k = 0;
////if (c.getId() == 0)
////k = this.fetchCustID(c);
////else
////k = c.getId();
////
////String query = "Select * from account where cust_id=" + k;
////try {
////pstmt = dbCon.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
////ResultSet rs = pstmt.executeQuery();
////while (rs.next()) {
////if (rs.getBoolean(4) == false)
////return true;
////else
////return false;
////}
////} catch (SQLException e) {
////System.out.println("Error in check balance : " + e.getMessage());
////return false;
////
////}
////return false;
////
////}
////
////@Override
////public boolean deposit(Customer c, int amount) {
////int k = 0;
////if (c.getId() == 0)
////k = this.fetchCustID(c);
////else
////k = c.getId();
////
////String query = "select * from account where cust_id= " + k;
////int amt = 0;
////
////try {
////pstmt = dbCon.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
////int acc = 0;
////// Execute the query
////ResultSet rs = pstmt.executeQuery();
////while (rs.next()) {
////if (rs.getInt("cust_id") == k) {
////amt = amount + rs.getInt("balance");
////acc = rs.getInt("account_no");
////rs.updateInt(3, amt);
////if (amt > 500)
////rs.updateBoolean(4, true);
////else
////rs.updateBoolean(4, false);
////rs.updateRow();
////
////}
////}
////String transact = "Insert into transaction values (" + acc + "," + acc + "," + " 'Deposit' ," + amount
////+ ",? )";
////pstmt = dbCon.prepareStatement(transact);
////pstmt.setTimestamp(1, new Timestamp(new java.util.Date().getTime()));
////if (pstmt.executeUpdate() > 0)
////System.out.println("Successfully Depositted...");
////return true;
////
////} catch (SQLException e) {
////System.out.println("Error in depositting : " + e.getMessage());
////return false;
////}
////
////}
////
////@Override
////public boolean withdrawal(Customer c, int amount) {
////int k = 0;
////if (c.getId() == 0)
////k = this.fetchCustID(c);
////else
////k = c.getId();
////
////String query = "select * from account where cust_id= " + k;
////int amt = 0;
////
////try {
////pstmt = dbCon.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
////int acc = 0;
////// Execute the query
////ResultSet rs = pstmt.executeQuery();
////while (rs.next()) {
////if (rs.getInt("cust_id") == k) {
////if (rs.getInt("balance") >= amount) {
////
////amt = rs.getInt("balance") - amount;
////acc = rs.getInt("account_no");
////rs.updateInt(3, amt);
////
////if (amt > 500)
////rs.updateBoolean(4, true);
////else
////rs.updateBoolean(4, false);
////
////rs.updateRow();
////} else {
////System.out.println("Insufficient Balance...");
////return false;
////}
////}
////}
////String transact = "Insert into transaction values (" + acc + "," + acc + "," + " 'Withdrawal' ," + amount
////+ ",? )";
////pstmt = dbCon.prepareStatement(transact);
////pstmt.setTimestamp(1, new Timestamp(new java.util.Date().getTime()));
////if (pstmt.executeUpdate() > 0)
////System.out.println("Successfully Withdrawing...");
////return true;
////
////} catch (SQLException e) {
////System.out.println("Error in withdrawing : " + e.getMessage());
////return false;
////}
////}
////
////@Override
////public boolean send(Customer c, int account_no_to, int amount) {
//////	int a_t=account_no_to;
//////	String q = "select * from account where account_no = "+a_t;
//////	try {
//////		pstmt = dbCon.prepareStatement(q);
//////		ResultSet rs1 = pstmt.executeQuery();
//////		System.out.println("exctr query");
//////		while(!rs1.next()) {
//////			System.out.println("invalid acc no");
//////			return false;
//////		}
//////	} catch (SQLException e1) {
//////		// TODO Auto-generated catch block
//////		//e1.printStackTrace();
//////		
//////	}
////	
////// TODO Auto-generated method stub
////int k = 0;
////if (c.getId() == 0)
////k = this.fetchCustID(c);
////else
////k = c.getId();
////
////String query = "select * from account where cust_id= " + k;
////int amt = 0;
////
////try {
////pstmt = dbCon.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
////int acc=0;
////// Execute the query
////ResultSet rs = pstmt.executeQuery();
////
////while (rs.next()) {
////	System.out.println("inside while");
////if (rs.getInt(2) == k) {
////	System.out.println("cust id"+k);
////if (rs.getInt("balance") >= amount) {
////
////amt = rs.getInt("balance") - amount;
////acc = rs.getInt("account_no");
////System.out.println("cust occ"+acc);
////rs.updateInt(3, amt);
////if (rs.getInt("balance") > 500)
////rs.updateBoolean(4, true);
////else
////rs.updateBoolean(4, false);
////System.out.println("updating row");
////rs.updateRow();
////System.out.println("updated row");
////
////}
////}
////}
////String transact = "Insert into transaction values (" + acc + "," + account_no_to + "," + " 'Sent' ," + amount+ ",? )";
////pstmt = dbCon.prepareStatement(transact);
////pstmt.setTimestamp(1, new Timestamp(new java.util.Date().getTime()));
////if (pstmt.executeUpdate() > 0)
////System.out.println("Successfully Sent...");
////else
////System.out.println("not sent..");
////k=account_no_to;
////pstmt = dbCon.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
////
////// Execute the query
////ResultSet rs2 = pstmt.executeQuery();
////while (rs2.next()) {
////if (rs2.getInt("account_no") == account_no_to) {
////amt = rs.getInt("balance") + amount;
////System.out.println(amount);
////System.out.println(amt);
////rs.updateInt(3, amt);
////if (rs2.getInt("balance") > 500)
////rs.updateBoolean(4, true);
////else
////rs.updateBoolean(4, false);
////rs.updateRow();
////}
////}
////transact = "Insert into transaction values (" + acc + "," + account_no_to + "," + "'Recieve' ,"+ amount + ", ? )" ;
////pstmt = dbCon.prepareStatement(transact);
////pstmt.setTimestamp(1, new Timestamp(new java.util.Date().getTime()));
////
////
////if (pstmt.executeUpdate() > 0)
////System.out.println("Successfully recieve...");
////else
////System.out.println("not recieve..");
////
////return true;
////
////} catch (SQLException e) {
////System.out.println("Error in depositting : " + e.getMessage());
////return false;
////}
////}
////
////@Override
////public ArrayList<Transaction> printTransaction(Customer c) {
////// TODO Auto-generated method stub
////
////int k=this.fetchAccountNumber(c);
////String qryFetch = "select * from transaction where from_acc="+k;
////ArrayList<Transaction> details = new ArrayList<Transaction>();
////
////PreparedStatement pstmt;
////try {
////pstmt = dbCon.prepareStatement(qryFetch);
////
////// Execute the query
////ResultSet rs = pstmt.executeQuery();
////
////// Traverse through the data
////while (rs.next()) {
////if (rs.getInt("from_acc") == k) {
////Transaction t = new Transaction();
////
////t.setAmt(rs.getInt("amount"));
////t.setDtime(rs.getTimestamp("date_time"));
////t.setFrom_acc(rs.getInt("from_acc"));
////t.setTo_acc(rs.getInt("to_acc"));
////t.setTran_type(rs.getString("type"));
////details.add(t);
////}
////}
////} catch (SQLException e) {
////System.out.println("Error in transaction printing in: " + e.getMessage());
////}
////return details;
////
////}
////
////
////}
//
//
//
//
//package com.ibm.dao;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//
//import com.ibm.bean.Customer;
//import com.ibm.bean.Transaction;
//
//public class DaoApply implements DaoClass {
//
//Connection dbCon;
//PreparedStatement pstmt;
//
//public DaoApply(){
//
//try {
//dbCon = DriverManager.getConnection("jdbc:mysql://localhost:3307/banking?serverTimezone=UTC", "root",
//"");
//} catch (SQLException e) {
//System.out.println("Error connecting database..." + e.getMessage());
//}
//
//}
//public int fetchCustID(Customer c) {
//int k = 0;
//String fetchQry = "select * from customer where phno=?";
//
//try {
//pstmt = dbCon.prepareStatement(fetchQry);
//pstmt.setString(1, c.getPhone());
//ResultSet rs = pstmt.executeQuery();
//while (rs.next()) {
//k = rs.getInt("cust_id");
//}
//
//} catch (SQLException e) {
//// TODO Auto-generated catch block
//e.printStackTrace();
//}
//return k;
//}
//public int fetchAccountNumber(Customer c) {
//int k = 0,m=0;
//if (c.getId() == 0)
//k = this.fetchCustID(c);
//else
//k = c.getId();
//
//String fetchQry = "select * from account where cust_id="+k;
//
//try {
//pstmt = dbCon.prepareStatement(fetchQry);
//
//ResultSet rs = pstmt.executeQuery();
//while (rs.next()) {
//m = rs.getInt("account_no");
//}
//
//} catch (SQLException e) {
//// TODO Auto-generated catch block
//e.printStackTrace();
//}
//return m;
//}
//
//@Override
//public boolean createAccount(Customer c) {
//// Write the query to insert a new row in table
//String CinsertQry = "insert into customer(password,cust_name,dob,phno,email) values(?,?,?,?,?)";
//String AinsertQry = "insert into account(cust_id,balance,balance_s) values(?,?,?)";
//
//// Create the Statement
//try {
//
//pstmt = dbCon.prepareStatement(CinsertQry);
//pstmt.setString(1, c.getPassword());
//pstmt.setString(2, c.getName());
//pstmt.setDate(3, (java.sql.Date) c.getDob());
//pstmt.setString(4, c.getPhone());
//pstmt.setString(5, c.getEmail());
//
//// Execute the query
//if (pstmt.executeUpdate() > 0) {
//System.out.println("Successfully inserted a new row in customer...");
//
//} else {
//System.out.println("Some issues while inserting in customer...");
//return false;
//}
//
//pstmt = dbCon.prepareStatement(AinsertQry);
//
//pstmt.setInt(1, new DaoApply().fetchCustID(c));
//pstmt.setInt(2, c.a.getBalance());
//if (c.a.getBalance() < 500)
//pstmt.setBoolean(3, false);
//else
//pstmt.setBoolean(3, true);
//
//if (pstmt.executeUpdate() > 0) {
//System.out.println("Successfully inserted a new row account in ...");
//return true;
//} else {
//System.out.println("Some issues while inserting in account...");
//return false;
//}
//
//} catch (SQLException e) {
//System.out.println("Issues creating the statement :" + e.getMessage());
//}
//
//return false;
//}
//
//@Override
//public boolean login(Customer c) {
//// TODO Auto-generated method stub
//String qryFetch = "select * from customer";
//
//PreparedStatement pstmt;
//try {
//pstmt = dbCon.prepareStatement(qryFetch, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//
//// Execute the query
//ResultSet rs = pstmt.executeQuery();
//
//// Traverse through the data
//while (rs.next()) {
//if (rs.getInt("cust_id") == c.getId() && rs.getString("password").trim().equals(c.getPassword()))
//return true;
//}
//} catch (SQLException e) {
//System.out.println("Error in logging in: " + e.getMessage());
//}
//return false;
//}
//
//@Override
//public ArrayList<Customer> viewBalance(Customer c) {
//ArrayList<Customer> details = new ArrayList<Customer>();
//
//int k = 0;
//if (c.getId() == 0)
//k = this.fetchCustID(c);
//else
//k = c.getId();
//String fetchQry1 = "SELECT * FROM `customer` WHERE cust_id=" + k;
//String fetchQry2 = "SELECT * FROM `account` WHERE cust_id=" + k;
//
//try {
//pstmt = dbCon.prepareStatement(fetchQry1);
//
//ResultSet rs = pstmt.executeQuery(fetchQry1);
//Customer t = new Customer();
//while (rs.next()) {
//t.setId(rs.getInt("cust_id"));
//t.setName(rs.getString("cust_name"));
//t.setDob(rs.getDate("dob"));
//t.setPhone(rs.getString("phno"));
//t.setEmail(rs.getString("email"));
//
//}
//
//pstmt = dbCon.prepareStatement(fetchQry2);
//
//rs = pstmt.executeQuery(fetchQry2);
//
//while (rs.next()) {
//
//t.a.setAccount_no(rs.getInt("account_no"));
//t.a.setBalance(rs.getInt("balance"));
//
//}
//details.add(t);
//
//} catch (SQLException e) {
//System.out.println("Issues creating the statement :" + e.getMessage());
//// details.add(100);
//}
//return details;
//
//}
//
//@Override
//public boolean isLowBalance(Customer c) {
//
//int k = 0;
//if (c.getId() == 0)
//k = this.fetchCustID(c);
//else
//k = c.getId();
//
//String query = "Select * from account where cust_id=" + k;
//try {
//pstmt = dbCon.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//ResultSet rs = pstmt.executeQuery();
//while (rs.next()) {
//if (rs.getBoolean(4) == false)
//return true;
//else
//return false;
//}
//} catch (SQLException e) {
//System.out.println("Error in check balance : " + e.getMessage());
//return false;
//
//}
//return false;
//
//}
//
//@Override
//public boolean deposit(Customer c, int amount) {
//int k = 0;
//if (c.getId() == 0)
//k = this.fetchCustID(c);
//else
//k = c.getId();
//
//String query = "select * from account where cust_id= " + k;
//int amt = 0;
//
//try {
//pstmt = dbCon.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//int acc = 0;
//// Execute the query
//ResultSet rs = pstmt.executeQuery();
//while (rs.next()) {
//if (rs.getInt("cust_id") == k) {
//amt = amount + rs.getInt("balance");
//acc = rs.getInt("account_no");
//System.out.println(amt+amount+k);
//rs.updateInt(3, amt);
//if (amt > 500)
//rs.updateBoolean(4, true);
//else
//rs.updateBoolean(4, false);
//rs.updateRow();
//
//}
//}
//String transact = "Insert into transaction values (" + acc + "," + acc + "," + " 'Deposit' ," + amount
//+ ",? )";
//pstmt = dbCon.prepareStatement(transact);
//pstmt.setTimestamp(1, new Timestamp(new java.util.Date().getTime()));
//if (pstmt.executeUpdate() > 0)
//System.out.println("Successfully Depositted...");
//return true;
//
//} catch (SQLException e) {
//System.out.println("Error in depositting : " + e.getMessage());
//return false;
//}
//
//}
//
//@Override
//public boolean withdrawal(Customer c, int amount) {
//int k = 0;
//if (c.getId() == 0)
//k = this.fetchCustID(c);
//else
//k = c.getId();
//
//String query = "select * from account where cust_id= " + k;
//int amt = 0;
//
//try {
//pstmt = dbCon.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//int acc = 0;
//// Execute the query
//ResultSet rs = pstmt.executeQuery();
//while (rs.next()) {
//if (rs.getInt("cust_id") == k) {
//if (rs.getInt("balance") >= amount) {
//
//amt = rs.getInt("balance") - amount;
//acc = rs.getInt("account_no");
//rs.updateInt(3, amt);
//
//if (amt > 500)
//rs.updateBoolean(4, true);
//else
//rs.updateBoolean(4, false);
//
//rs.updateRow();
//} else {
//System.out.println("Insufficient Balance...");
//return false;
//}
//}
//}
//String transact = "Insert into transaction values (" + acc + "," + acc + "," + " 'Withdrawal' ," + amount
//+ ",? )";
//pstmt = dbCon.prepareStatement(transact);
//pstmt.setTimestamp(1, new Timestamp(new java.util.Date().getTime()));
//if (pstmt.executeUpdate() > 0)
//System.out.println("Successfully Withdrawing...");
//return true;
//
//} catch (SQLException e) {
//System.out.println("Error in withdrawing : " + e.getMessage());
//return false;
//}
//}
//
//@Override
//public boolean send(Customer c, int account_no_to, int amount) {
//// TODO Auto-generated method stub	
//int k = 0;
//if (c.getId() == 0)
//k = this.fetchCustID(c);
//else
//k = c.getId();
//
//String query = "select * from account where cust_id= " + k;
//int amt = 0;
//
//try {
//pstmt = dbCon.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//int acc=0;
//// Execute the query
//ResultSet rs = pstmt.executeQuery();
//while (rs.next()) {
//if (rs.getInt("cust_id") == k) {
//if (rs.getInt("balance") >= amount) {
//
//amt = rs.getInt("balance") - amount;
//acc = rs.getInt("account_no");
//rs.updateInt(3, amt);
//if (rs.getInt("balance") > 500)
//rs.updateBoolean(4, true);
//else
//rs.updateBoolean(4, false);
//rs.updateRow();
//
//}
//}
//}
//String transact = "Insert into transaction values (" + acc + "," + account_no_to + "," + " 'Sent' ," + amount+ ",? )";
//pstmt = dbCon.prepareStatement(transact);
//pstmt.setTimestamp(1, new Timestamp(new java.util.Date().getTime()));
//if (pstmt.executeUpdate() > 0)
//System.out.println("Successfully Sent...");
//else
//System.out.println("not sent..");
//
//query = "select * from account where account_no= " + account_no_to;
//pstmt = dbCon.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//
//// Execute the query
//rs = pstmt.executeQuery();
//while (rs.next()) {
//if (rs.getInt("account_no") == account_no_to) {
//amt = rs.getInt("balance") + amount;
//rs.updateInt(3, amt);
//if (rs.getInt("balance") > 500)
//rs.updateBoolean(4, true);
//else
//rs.updateBoolean(4, false);
//rs.updateRow();
//}
//}
//transact = "Insert into transaction values (" + acc + "," + account_no_to + "," + "'Recieve' ,"+ amount + ", ? )" ;
//pstmt = dbCon.prepareStatement(transact);
//pstmt.setTimestamp(1, new Timestamp(new java.util.Date().getTime()));
//
//
//if (pstmt.executeUpdate() > 0)
//System.out.println("Successfully recieve...");
//else
//System.out.println("not recieve..");
//
//return true;
//
//} catch (SQLException e) {
//System.out.println("Error in depositting : " + e.getMessage());
//return false;
//}
//}
//
//@Override
//public ArrayList<Transaction> printTransaction(Customer c) {
//// TODO Auto-generated method stub
//
//int k=this.fetchAccountNumber(c);
//String qryFetch = "select * from transaction where from_acc="+k;
//ArrayList<Transaction> details = new ArrayList<Transaction>();
//
//PreparedStatement pstmt;
//try {
//pstmt = dbCon.prepareStatement(qryFetch);
//
//// Execute the query
//ResultSet rs = pstmt.executeQuery();
//
//// Traverse through the data
//while (rs.next()) {
//if (rs.getInt("from_acc") == k) {
//Transaction t = new Transaction();
//
//t.setAmt(rs.getInt("amount"));
//t.setDtime(rs.getTimestamp("date_time"));
//t.setFrom_acc(rs.getInt("from_acc"));
//t.setTo_acc(rs.getInt("to_acc"));
//t.setTran_type(rs.getString("type"));
//details.add(t);
//}
//}
//} catch (SQLException e) {
//System.out.println("Error in transaction printing in: " + e.getMessage());
//}
//return details;
//
//}
//@Override
//public boolean isAccountExist(int to_account) {
//
//	String fetchQry = "select * from account";
//
//	try {
//	pstmt = dbCon.prepareStatement(fetchQry);
//	//pstmt.setString(1, c.getPhone());
//	ResultSet rs = pstmt.executeQuery();
//	while (rs.next()) {
//	if(rs.getInt("account_no")==to_account) 
//		return true;
//
//	}
//	
//	}
//	catch (SQLException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//	}
//	return false;
//	}
//
//}
