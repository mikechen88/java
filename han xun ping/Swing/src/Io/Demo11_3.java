package Io;
/**
 * 演示 FileOutputStream
 * 1:切记一定要先判断文件是否已经存在，否则会把原有的文件覆盖
 */

import java.io.*;
public class Demo11_3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		File f=new File("d:\\ss.txt");
		FileOutputStream fos=null;
		if(!f.exists())
		{
			try {
				fos=new FileOutputStream(f);
				
				String s="老桑，向世界问好\r\n";
				//  \r\n    换行
				String s1="美女，向世界问好";
				//定义字节数组
			//	byte [] bytes=new byte[1024];
				
				fos.write(s.getBytes());
				fos.write(s1.getBytes());
			} catch (Exception e) {
				// TODO: handle exception
			}finally
			{
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else
		{
			System.out.println("文件已经存在");
		}
	}

}
