package Io;
/**
 * ��ʾ������
 * ע�⣺�ȶ�����ļ��� �ڶ���һ�������� ���ļ���������뵽 ��������������
 *  ��������д��ʱ�򲻻��У���ע���д��ʱ����� "\r\n"
 *   */

import java.io.*;
public class Demo12_6 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader br=null;
		BufferedWriter bw=null;
		try {
			//�ȴ���FileReader����
			FileReader fr=new FileReader("d:\\123.txt");
			br=new BufferedReader(fr);
			
			//����FileWriter����
			FileWriter fw=new FileWriter("e:\\123.txt");
			bw=new BufferedWriter(fw);
			//ѭ����ȡ�ļ�
			String s="";
			while((s=br.readLine())!=null)
			{//null ��ͬ��    -1
				//���ж�ȡ�������������з���

				System.out.println(s);
				//������ļ�
				bw.write(s+ "\r\n");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally
		{
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}