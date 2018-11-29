class A {

	
 public int foo(double me) {
	  String param2; 
	  param2 = "paras"; // Noncompliant {{message}}
	  String param3;
	  param3 = "yadav"; 
  }
/*  int foo(int a) {} 
  int foo(int a, int b) {}

  int foo(Object a){} 
  String foo(String a){} // Noncompliant {{message}}
  String foo(Object a){}*/
}