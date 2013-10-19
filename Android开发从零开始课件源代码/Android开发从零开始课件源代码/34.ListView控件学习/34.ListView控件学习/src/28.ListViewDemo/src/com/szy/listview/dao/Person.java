package com.szy.listview.dao;

/**
 * @author coolszy
 * @blog http://blog.csdn.net/coolszy
 */
public class Person
{
	private String pid;
	private String pname;
	private String pgender;

	public Person()
	{
		super();
	}

	public Person(String pid, String pname, String pgender)
	{
		super();
		this.pid = pid;
		this.pname = pname;
		this.pgender = pgender;
	}

	public String getPid()
	{
		return pid;
	}

	public void setPid(String pid)
	{
		this.pid = pid;
	}

	public String getPname()
	{
		return pname;
	}

	public void setPname(String pname)
	{
		this.pname = pname;
	}

	public String getPgender()
	{
		return pgender;
	}

	public void setPgender(String pgender)
	{
		this.pgender = pgender;
	}

	@Override
	public String toString()
	{
		return "pid=" + pid + ";pname=" + pname + ";pgender=" + pgender;
	}
}
