import com.olf.openjvs.Table;
import com.olf.openjvs.Util;

class A {

 public int foo(double me) {
	  String param2; 
	  param2 = "aaaa"; 
	  string param6 = callMe(param2);
	  System.out.println(param6);
	  String param3;
	  param3 = "bbbb";
	  string param7 = callMe(param3);
	  System.out.println(param7);
	  String param4;
	  param4 = null;
	  string param8 = callMe(param4);
	  System.out.println(param8);
	  
	  param2.substring(0, 3);
	  Table table1;
	  table1 = Util.NULL_TABLE;
	  
	  try {
		  Table tbl; 
		  tbl = com.olf.openjvs.Table.tableNew(); // Noncompliant {{message}}
		  tbl.destroy();
		  
	  }catch(Exception exp) {
		  System.out.println("Exception in Test class");
	  }
	  
  }
	 
	 public String callMe(String param5) {
		 System.out.println(" \n =========== Call Me method called with parameter " + param5 );
		 
		 return "\n===== Returning your call======";
	 }
}