package ExceptionEx;
import java.io.*;
import java.net.Socket;
public class ExceptionExample1 {
	public static void main(String[] args) {
	//1.��������鵽�ļ��쳣
		//FileReader fr=new FileReader("d:\\aa.text");
	
		
	//2.����һ��192.168.12.12 ip �˿�4567
	//Socket s=new Socket("192.168.1.23",78);
	
	//�����쳣
	//int a=4/0;
		//�����쳣
	//int arr[]={1,2,3,4};
	//System.out.println(arr[1234]);
		FileReader fr=null;
		try {
			 fr=new FileReader("d:\\aa.txt");
			//�ڳ����쳣�ĵط�������ִֹ�д��룬
			//Ȼ����뵽catch
			//����ж�� catch��䣬�����ƥ���쳣�Ǹ�catch
			//��Ҫ��С���쳣����ǰ��������ں�
			Socket s=new Socket("192.168.1.23",78);
		//} catch (Exception e) {
			// TODO: handle exception
			//���쳣����Ϣ������������������ų�����
			//e.getMessage();
			//e.printStackTrace();
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e2){
			e2.printStackTrace();
			//�����ܲ������е��쳣
		}
		//һ�������쳣���Ͳ���ִ�к����catch,�����ᴦ�����Ĵ���
		//���� ��finally
		finally{
			//������飬������û�� �쳣������ִ��
			//һ��˵������Ҫ�رյ���Դ���ļ������ӣ��ڴ档��������
			System.out.println("enter finally��");
			// it means enter finally
			if(fr!=null)
			{
				try {
					fr.close();//��֤�ļ������ر�
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	}
}
