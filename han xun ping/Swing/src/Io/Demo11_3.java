package Io;
/**
 * ��ʾ FileOutputStream
 * 1:�м�һ��Ҫ���ж��ļ��Ƿ��Ѿ����ڣ�������ԭ�е��ļ�����
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
				
				String s="��ɣ���������ʺ�\r\n";
				//  \r\n    ����
				String s1="��Ů���������ʺ�";
				//�����ֽ�����
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
			System.out.println("�ļ��Ѿ�����");
		}
	}

}