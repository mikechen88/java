package ExceptionEx;
import java.io.*;
import java.net.Socket;
public class ExceptionExample1 {
	public static void main(String[] args) {
	//1.编译器检查到文件异常
		//FileReader fr=new FileReader("d:\\aa.text");
	
		
	//2.连接一个192.168.12.12 ip 端口4567
	//Socket s=new Socket("192.168.1.23",78);
	
	//运行异常
	//int a=4/0;
		//运行异常
	//int arr[]={1,2,3,4};
	//System.out.println(arr[1234]);
		FileReader fr=null;
		try {
			 fr=new FileReader("d:\\aa.txt");
			//在出现异常的地方，就终止执行代码，
			//然后进入到catch
			//如果有多个 catch语句，则进入匹配异常那个catch
			//但要把小的异常排在前，大的排在后
			Socket s=new Socket("192.168.1.23",78);
		//} catch (Exception e) {
			// TODO: handle exception
			//把异常的信息输出来，这样有利于排除错误
			//e.getMessage();
			//e.printStackTrace();
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e2){
			e2.printStackTrace();
			//这行能捕获所有的异常
		}
		//一旦捕获到异常，就不会执行后面的catch,更不会处理后面的代码
		//除非 用finally
		finally{
			//这个语句块，不管有没有 异常，都会执行
			//一般说，把需要关闭的资源（文件，连接，内存。。。。）
			System.out.println("enter finally。");
			// it means enter finally
			if(fr!=null)
			{
				try {
					fr.close();//保证文件流被关闭
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	}
}
