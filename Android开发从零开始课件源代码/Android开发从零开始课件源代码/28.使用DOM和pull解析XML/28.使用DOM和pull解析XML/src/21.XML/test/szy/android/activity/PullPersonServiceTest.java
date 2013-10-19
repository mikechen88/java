package szy.android.activity;

import java.io.InputStream;
import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.szy.xml.model.Person;
import com.szy.xml.service.PullPersonService;

/**
 *Administrator
 *2011-1-10
 */

public class PullPersonServiceTest extends AndroidTestCase
{
	private static final String TAG="PersonServiceTest";
	public void testReadXml() throws Exception
	{
		InputStream inStream=PullPersonServiceTest.class.getClassLoader().getResourceAsStream("person.xml");
		List<Person> persons=PullPersonService.readXml(inStream);
		for (Person person : persons)
		{
			Log.i(TAG, person.toString());
		}
	}
}
