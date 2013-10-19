package list;
import java.util.*;
import java.io.*;
public class Employee {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		
		EmpManage em=new EmpManage();
		BufferedReader br=new BufferedReader( new InputStreamReader(System.in));
		//作出一个简易菜单
		while(true)
		{
			System.out.println("please selset :");
			System.out.println("1add,  2find , 3.edit, 4delete ,5exit:");
			
			String operType=br.readLine();
			
			if (operType.equals("1"))
			{
				System.out.println("please input number:  ");
				String empNo=br.readLine();
				System.out.println("please input name: ");
				String name=br.readLine();
				System.out.println("please input sal: ");
				Float sal=Float.parseFloat(br.readLine());
				
				Emp emp=new Emp(empNo,name,sal);
				em.addEmp(emp);
				
			}else if(operType.equals("2")) {
				System.out.println("please input number:  ");
				String empNo=br.readLine();
				
				em.showInfo(empNo);
			}else if(operType.equals("3")) {
					
				}else if(operType.equals("4")) {
						
					}else if (operType.equals("5")){
						System.exit(0);
						//0 退出系统
						//非0 是异常退出，
					}
		}
		
	}

}
class EmpManage{
	private ArrayList al2=null;
	public EmpManage()
	{
		al2=new ArrayList();
	}
	public void addEmp(Emp emp)
	{
		al2.add(emp);
	}
	//显示员工的相关信息
	public void showInfo(String empNo)	
	{
		//遍历整个ArrayList
		for(int i=0;i<al2.size();i++)
		{
			//取出emp对象
			Emp emp=(Emp)al2.get(i);
			if (emp.getEmpNo().equals(empNo))
			{
				System.out.println("got it.his information is : ");
				System.out.println("no=:"+empNo);
			}
		}
	}
	//修改
	public void updateSal (String empNo, float newSal)
	
	{
		for (int i=0;i<al2.size(); i++)
		{
			Emp emp=(Emp)al2.get(i);
			if (emp.getEmpNo().equals(empNo))
			{
				emp.setSal(newSal);
			}
		}
	}
	//删除某员工
	public void delEmp(String empNo)
	{
		for (int i=0;i<al2.size(); i++)
		{
			Emp emp=(Emp)al2.get(i);
			if (emp.getEmpNo().equals(empNo))
			{
				al2.remove(emp);
			}
		}
	}
}
class Emp{	
	private String empNo;
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getSal() {
		return sal;
	}
	public void setSal(float sal) {
		this.sal = sal;
	}
	private String name;
	private float sal;
	public Emp(String empNo, String name,float sal)
	{
		this .empNo=empNo;
		this.name=name;
		this.sal=sal;
	}
}