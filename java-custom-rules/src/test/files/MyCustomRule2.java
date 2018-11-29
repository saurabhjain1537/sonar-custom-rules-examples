import com.olf.openjvs.Table;

class A {

//	 @Zuper // Noncompliant {{Avoid using annotation @Zuper}}
 public int foo(double me) {
	  String param2; 
	  param2 = "paras"; 
	  string param6 = callMe(param2);
	  System.out.println(param6);
	  String param3;
	  param3 = "yadav";
	  string param7 = callMe(param3);
	  System.out.println(param7);
	  String param4;
	  param4 = null;
	  string param8 = callMe(param4);
	  System.out.println(param8);
	  
	  param2.substring(0, 3);
	  Table table1;
	  
	  try {
		  Table tbl = com.olf.openjvs.Table.tableNew();
		  tbl.destroy();
		  
	  }catch(Exception exp) {
		  System.out.println("Exception in Test class");
	  }
	  
  }
	 
	 public String callMe(String param5) {
		 System.out.println(" \n =========== Call Me method called with parameter " + param5 );
		 table1.des
		 
		 return "\n===== Returning your call======";
	 }
/*  int foo(int a) {} 
  int foo(int a, int b) {}

  int foo(Object a){} 
  String foo(String a){} // Noncompliant {{message}}
  String foo(Object a){}*/
}