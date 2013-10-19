
public class First {
	
		public void method2() {
		System.out.println("First2");
		}
		public void method3() {
		method2();
		}
		}
		public class Second extends First {
		public void method2() {
		System.out.println("Second2");
		}
		}
		public class Third extends Second {
		public void method1() {
		System.out.println("Third1");
		super.method2();
		}
		public void method2() {
		System.out.println("Third2");
		}
		}