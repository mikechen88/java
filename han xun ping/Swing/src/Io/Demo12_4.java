package Io;
/**
 * ʵ��ͼƬ�Ŀ���\
 * ע�⣺�õ����ļ��ֽ��� 
 */

import java.io.*;
public class Demo12_4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//����������
		//�Ȱ�ͼƬд�뵽�ڴ棬��д�뵽�ļ�
		//��Ϊ�Ƕ������ļ������ֻ�����ֽ�
		FileInputStream fis=null;
		//���������
		FileOutputStream fos=null;
			try {
				//����ͼƬҲ����
				fis= new FileInputStream("d:\\a.jpg");
				fos= new FileOutputStream("e:\\a.jpg");
				// �����ı��ļ�Ҳ����
				fis= new FileInputStream("d:\\ss.txt");
				fos= new FileOutputStream("e:\\ss.txt");
				byte buf[]=new byte[1024];
				//ѭ����ȡ
				int n=0;//��¼ʵ�ʶ�ȡ�����ֽ���
				//ѭ����ȡ
				while((n=fis.read(buf))!=-1)
				{
					//�����ָ���ļ�
					fos.write(buf);
					System.out.println("�ļ��Ѿ���ɸ���");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				//�ر��ļ���
				try {
					fis.close();
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}

}