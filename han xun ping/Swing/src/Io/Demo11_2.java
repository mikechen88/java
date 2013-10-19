package Io;
/**
 * 演示FileInputStream
 *1:把文件读入到计算机内存中
 */

import java.io.*;
public class Demo11_2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File f=new File("d:\\123.java");
		
		FileInputStream fis=null;
		//因为File没有读功能，所以需要一个InputStream
		try {
			fis=new FileInputStream(f);
			
			//定义一个字节数组
			byte []bytes=new byte[1024];
			//循环读取
			int n=0;//得到实际的字节数
			while( (n=fis.read(bytes))!=-1)
			{
				//把字节数组中的内容转换成字符串
				String s=new String(bytes, 0, n);
				//输出字符串中的内容
				System.out.println(s);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关闭文件流必须关闭
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
