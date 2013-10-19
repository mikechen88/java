package Io;

/**字符流
 * FileReader和 FileWriter的用法
 */

import java.io.*;
public class Demo15_5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FileReader fr=null;
		FileWriter fw=null;
		
		try {
			//创建fr对象
			fr=new FileReader("d:\\123.txt");
			//创建fw对象
			fw=new FileWriter("e:\\123.txt");
			//创建字符数组读入到内存
			int n=0;
			char c[]=new char[1024];
			//当读取的字符数组不是文件的末尾
			while((n=fr.read(c))!=-1)
			{
			//	String s=new String(c,0,n);
			//	System.out.print(s);
			//	fw.write(c);
				
				fw.write(c,0,n);
				//因为末尾不足  1024 位，如果用fw.write(c);会产生乱码，
				//所以 用n,可以控制末尾的位置，从而不致于产生乱码。
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally
		{
			try {
				fr.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
