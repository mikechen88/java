package com.szy.sqlite.activity;

import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.szy.sqlite.dao.StudentDAO;
import com.szy.sqlite.model.Student;

/**
 * @author  coolszy
 * @blog    http://blog.csdn.net/coolszy
 */
public class StudentDAOTest extends AndroidTestCase
{
	private final static String TAG="StudentDAOTest";
	
	public void testAdd()
	{
		StudentDAO studentDAO=new StudentDAO(this.getContext());
		Student student=new Student(1,"coolszy", (short)23);
		studentDAO.add(student);
		Log.i(TAG, "add successed");
	}

	public void testUpdate()
	{
		StudentDAO studentDAO=new StudentDAO(this.getContext());
		Student student=studentDAO.find(1);
		student.setName("kuka");
		studentDAO.update(student);
		Log.i(TAG, "update successed");
	}

	public void testFind()
	{
		StudentDAO studentDAO=new StudentDAO(this.getContext());
		Student student=studentDAO.find(1);
		if (null==student)
		{
			Log.i(TAG, "not find");
		}
		else
		{
			Log.i(TAG, student.toString());
		}
	}

	public void testDetele()
	{
		StudentDAO studentDAO=new StudentDAO(this.getContext());
		studentDAO.detele(1,2,3);
		Log.i(TAG, "delete successed");
	}

	public void testGetScrollData()
	{
		StudentDAO studentDAO=new StudentDAO(this.getContext());
		List<Student> students=studentDAO.getScrollData(0, 1);
		for (Student student : students)
		{
			Log.i(TAG, student.toString());
		}
	}

	public void testGetCount()
	{
		StudentDAO studentDAO=new StudentDAO(this.getContext());
		Long count=studentDAO.getCount();
		Log.i(TAG, ""+count);
	}

}
