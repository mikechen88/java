package Io;
/**
 * ��ʾFileInputStream
 *1:���ļ����뵽������ڴ���
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
		//��ΪFileû�ж����ܣ�������Ҫһ��InputStream
		try {
			fis=new FileInputStream(f);
			
			//����һ���ֽ�����
			byte []bytes=new byte[1024];
			//ѭ����ȡ
			int n=0;//�õ�ʵ�ʵ��ֽ���
			while( (n=fis.read(bytes))!=-1)
			{
				//���ֽ������е�����ת�����ַ���
				String s=new String(bytes, 0, n);
				//����ַ����е�����
				System.out.println(s);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//�ر��ļ�������ر�
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}