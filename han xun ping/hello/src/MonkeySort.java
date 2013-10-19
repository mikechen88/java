/**
 * �����Ŷ�
 * 1���Ϻ�����ð������
 * 2���Ϻ�����ѡ������
 * 3: �Ϻ��ò�������
 */

import java.io.*;
public class MonkeySort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//����һ������װ��ֻ���ӵĶ�������
		int size=3;
		Monkey []monkeys=new Monkey[size];
		
		InputStreamReader isr =new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(isr);
		//��ʼ���������ӣ�ֱ�ӳ�ʼ��
		for(int i=0; i<monkeys.length; i++)
		{
			System.out.println("�������һֻ���ӵĸ߶�");
			try {
				String height=br.readLine();
				monkeys[i]=new Monkey((i+1)+"", Integer.parseInt(height));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		//���Ϻ�������
		Monkey oldMonkey=new Monkey("1000", 8);
		//�Ϻ���ð������
		oldMonkey.bubblesort(monkeys);
		//�Ϻ���ѡ������
		oldMonkey.selectSort(monkeys);
		//�Ϻ���ѡ���������
		oldMonkey.insertSort(monkeys);
		
		//��֤�����
		oldMonkey.show(monkeys);
	}

}
//������
class Monkey
{
	private String monkeyId;
	private int height;
	public  Monkey(String monkey, int height)
	{
		this.monkeyId=monkey;
		this.height=height;
	}
	
	
	
	//��������˼�룺��Ϊ��һ�����Ӿ�������ģ�����ĺ������εĲ��뵽���鵱��
	public void insertSort(Monkey []monkeys)
	{
		//��ʼ���� �����0�����Ӿ�����������԰ѵڶ������Ӳ��뵽���еĶ��鵱��
		for(int i=1; i<monkeys.length; i++)
		{
			//��¼Ҫ������ӵ�����
			int insertHeight=monkeys[i].height;
			String insertNo=monkeys[i].monkeyId;
			//��¼�������ǰһ�����ӵı��
			int insertIndex=i-1;
			
			while(insertIndex>=0&&monkeys[insertIndex].height>insertHeight)
			{
				//��ʱ��  insertIndex+1 ��Ҫ�����ֵ ��Ϊ����ֵС����ǰ���ֵ�����԰���ֵ����������ֵ��
				monkeys[insertIndex+1].height=monkeys[insertIndex].height;
				monkeys[insertIndex+1].monkeyId=monkeys[insertIndex].monkeyId;
				insertIndex--;
			}
			//��ʱ��  insertIndex+1 ��Ҫ�����ֵ��ǰ���ֵ�����Ҫ�����ֵ   ���ֵ�Ľ���
			monkeys[insertIndex+1].height=insertHeight;
			monkeys[insertIndex+1].monkeyId=insertNo;
		}
	}
	
	
	//ѡ������
	public void selectSort(Monkey [] monkeys)
	{
		int tempHeight=0;
		String tempNo="";
		for(int i=0; i<monkeys.length; i++)
		{
			//��Ϊ�±�Ϊi�ĺ�������͵�
			int minHeight=monkeys[i].height;
			int minIndex=i;
			
			//�ͺ���ıȽ�
			for(int j=i; j<monkeys.length; j++)
			{
				if(minHeight>monkeys[j].height)
				{
					//����һ����Сֵ
					minHeight=monkeys[j].height;
					//�����±�
					minIndex=j;
				}
			}
			if(minIndex!=i)
			{
				//���߽���
				tempHeight=monkeys[minIndex].height;
				monkeys[minIndex].height=monkeys[i].height;
				monkeys[i].height=tempHeight;
				//��Ž���
				tempNo=monkeys[minIndex].monkeyId;
				monkeys[minIndex].monkeyId=monkeys[i].monkeyId;
				monkeys[i].monkeyId=tempNo;
			}
		}
	}
	
	
	//ð���Ŷ�
	public void bubblesort(Monkey [] monkeys)
	{
		int tempHeight=0;
		String tempNo="";
		//ð��
		//���ѭ���Ĵ�����length-1
		for(int i=0; i<monkeys.length-1; i++)
		{
			//��{ѭ�� ����źõľͲ���������
			for(int j=0; j<monkeys.length-1-i; j++)
			{
				//�ж�
				if(monkeys[j].height>monkeys[j+1].height)
				{
					//���߽���
					tempHeight=monkeys[j].height;
					monkeys[j].height=monkeys[j+1].height;;
					monkeys[j+1].height=tempHeight;
					//��Ž���
					tempNo=monkeys[j].monkeyId;
					monkeys[j].monkeyId=monkeys[j+1].monkeyId;
					monkeys[j+1].monkeyId=tempNo;
				}
			}
		
				
		}
	}
	
	//��ʾ����
	public void show(Monkey []monkeys)
	{
		for(int i=0; i<monkeys.length; i++)
		{
			System.out.println("���ӱ��:"+monkeys[i].monkeyId+"����"+monkeys[i].height);
		}
	}
}