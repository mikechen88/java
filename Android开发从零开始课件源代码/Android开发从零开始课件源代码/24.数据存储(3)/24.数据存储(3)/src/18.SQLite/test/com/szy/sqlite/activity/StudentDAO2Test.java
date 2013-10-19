package com.szy.sqlite.activity;

import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.szy.sqlite.dao.StudentDAO2;
import com.szy.sqlite.model.Student;

/**
 * @author  coolszy
 * @blog    http://blog.csdn.net/coolszy
 */
public class StudentDAO2Test extends AndroidTestCase
{
	private final static String TAG="StudentDAOTest";
	
	public void testAdd()
	{
		StudentDAO2 StudentDAO2=new StudentDAO2(this.getContext());
		Student student=new Student(1,"coolszy", (short)23);
		StudentDAO2.add(student);
		Log.i(TAG, "add successed");
	}

	public void testUpdate()
	{
		StudentDAO2 StudentDAO2=new StudentDAO2(this.getContext());
		Student student=StudentDAO2.find(1);
		student.setName("kuka");
		StudentDAO2.update(student);
		Log.i(TAG, "update successed");
	}

	public void testFind()
	{
		StudentDAO2 StudentDAO2=new StudentDAO2(this.getContext());
		Student student=StudentDAO2.find(1);
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
		StudentDAO2 StudentDAO2=new StudentDAO2(this.getContext());
		StudentDAO2.detele(1,2,3);
		Log.i(TAG, "delete successed");
	}

	public void testGetScrollData()
	{
		StudentDAO2 StudentDAO2=new StudentDAO2(this.getContext());
		List<Student> students=StudentDAO2.getScrollData(0, 2);
		for (Student student : students)
		{
			Log.i(TAG, student.toString());
		}
	}

	public void testGetCount()
	{
		StudentDAO2 StudentDAO2=new StudentDAO2(this.getContext());
		Long count=StudentDAO2.getCount();
		Log.i(TAG, ""+count);
	}

}
