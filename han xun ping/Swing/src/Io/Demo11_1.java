package Io;

/**
 * ���ܣ� File��Ĺ���
 * 1:����һ���ļ������ж��ļ��Ƿ��������治���ھʹ������ļ���ǰ��������ļ����ڵ��ļ��б������
 * 2�������ļ���
 * 3�����ļ�������ĵ��ļ�����Ϣ��ʾ���������ߵݹ���ļ��������ҵ�
 */

import java.io.*;
public class Demo11_1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		//����һ���ļ�����
//		File f=new File("d:\\aa.txt");
//		//�õ��ļ�·��
//		System.out.println("�ļ�·����"+f.getAbsolutePath());
//		//�õ��ļ��Ĵ�С
//		System.out.println("�ļ���С"+f.length());
//		//�ж��ļ��Ƿ�ɶ�
//		System.out.println("�ɶ�"+f.canRead());
		
		

		//�ж��ļ��Ƿ����
//		File f=new File("d:\\bb.txt");
//		if(!f.exists())
//		{
//			//����
//			System.out.println("�����ļ�");
//			try {
//				f.createNewFile();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}else
//		{
//			System.out.println("�ļ��Ѿ�����");
//		}
		

//		//�ж��ļ����Ƿ����
//		File f=new File("d:\\ff");
//		if(f.isDirectory())  //����ļ��д���
//		{
//			System.out.println("�ļ����Ѿ�����");
//		}else{
//			//�����ļ���
//			f.mkdir();
//		}
		
		
		
		//�г�һ���ļ����������е��ļ�
		File f=new File("d:\\�鼮");
		if(f.isDirectory())
		{
			//����һ�����ڴ���ļ����ֵ�����
			File lists[]=f.listFiles();
			//��ӡ�ļ���������ļ�����
			for(int i=0; i<lists.length; i++)
			{
				//�õ��ļ�������
				System.out.println("�ļ�����"+lists[i].getName());
			}
		}
		
		
		
		
	}

}