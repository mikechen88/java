package Io;

/**�ַ���
 * FileReader�� FileWriter���÷�
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
			//����fr����
			fr=new FileReader("d:\\123.txt");
			//����fw����
			fw=new FileWriter("e:\\123.txt");
			//�����ַ�������뵽�ڴ�
			int n=0;
			char c[]=new char[1024];
			//����ȡ���ַ����鲻���ļ���ĩβ
			while((n=fr.read(c))!=-1)
			{
			//	String s=new String(c,0,n);
			//	System.out.print(s);
			//	fw.write(c);
				
				fw.write(c,0,n);
				//��Ϊĩβ����  1024 λ�������fw.write(c);��������룬
				//���� ��n,���Կ���ĩβ��λ�ã��Ӷ������ڲ������롣
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
