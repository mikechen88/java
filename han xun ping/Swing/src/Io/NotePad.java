package Io;

/**
 * ���±�����
 */

import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
public class NotePad extends JFrame implements ActionListener{

	//��������Ҫ�����
	JTextArea jta=null;
	//����˵���
	JMenuBar jmb=null;
	//��һ��JMenu
	JMenu jml=null;
	//����JMenuIteam
	JMenuItem jmi1=null;
	JMenuItem jmi2=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NotePad np=new NotePad();
	}
	//���캯��
	public NotePad()
	{
		//����jta�ı���
		jta=new JTextArea();
		jmb=new JMenuBar();
		jml=new JMenu("File(F)");
		
		//�������Ƿ�
		jml.setMnemonic('F');
		jmi1=new JMenuItem("Open");
		//jmi1=new JMenuItem("Open",new ImageIcon("face.jpg");
		//��ͼ��
		jmi2=new JMenuItem("Save");
		
		//�Ա��水ť������
		jmi2.addActionListener(this);
		jmi2.setActionCommand("save");
		
		
		
		
		//�Դ򿪰�ťע�����
		jmi1.addActionListener(this);
		jmi1.setActionCommand("open");
		
		//����
		this.setJMenuBar(jmb);
		//��jml���뵽jmb
		jmb.add(jml);
		//��item���뵽Menu
		jml.add(jmi1);
		jml.add(jmi2);
		
		
		//���뵽JFreme
		this.add(jta);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 300);
		this.setVisible(true);
	}
	
	
	//
	public void actionPerformed(ActionEvent arg0) {
		
		
		
		 if(arg0.getActionCommand().equals("save"))
		{
			//���ֱ���Ի���
			JFileChooser jfc=new JFileChooser();
			jfc.setDialogTitle("Save As....");
			//����Ĭ�ϵķ�ʽ��ʾ
			jfc.showSaveDialog(null);
			jfc.setVisible(true);
			
			//�û�ϣ�����ļ����浽�δ����ļ���ȫ·��
			String file=jfc.getSelectedFile().getAbsolutePath();
			//׼��д�뵽ָ���ļ�
			FileWriter fw=null;
			BufferedWriter bw=null;
			try {
				fw=new FileWriter(file);
				bw=new BufferedWriter(fw);
				
				bw.write(this.jta.getText());
			} catch (Exception e) {
				// TODO: handle exception
			}
			finally
			{
				try {
					bw.close();
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		// TODO Auto-generated method stub
	
		
		 
		 else if (arg0.getActionCommand().equals("open"));
		{
			//System.out.println("ѡ�е��Ǵ�");
			//�Ƽ�JFileChooser
			//�ļ�ѡ����
			JFileChooser jfc1=new JFileChooser();
			//set title
			jfc1.setDialogTitle("Please choose file.....");
			//null ��Ĭ�ϵ�ѡ�п����
			jfc1.showOpenDialog(null);
			//��ʾѡ�п������ ��ʾ�����ѡ�񴰿�
			jfc1.setVisible(true);
			
			//�õ��û�ѡ�е��ļ�ȫ·��
			String filename=jfc1.getSelectedFile().getAbsolutePath();
			//��ʾ�򿪵��ļ�
			System.out.println(filename);
			
			
			
			FileReader fr=null;
			BufferedReader br=null;
			
		
			try {
				fr=new FileReader(filename);
				br=new BufferedReader(fr);
				
				
				//���ļ��ж�ȡ��Ϣ����ʾjta
				String s="";
				String allCon="";
				while((s=br.readLine())!=null)
				{
					allCon+=s+"\r\n";
				}
				jta.setText(allCon);
				//���õ�jta����
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				try {
					br.close();
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		
	}
	
}

