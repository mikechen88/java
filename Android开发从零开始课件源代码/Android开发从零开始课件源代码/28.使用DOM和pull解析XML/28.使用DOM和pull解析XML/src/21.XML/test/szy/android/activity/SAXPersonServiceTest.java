package szy.android.activity;

import java.io.InputStream;
import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.szy.xml.model.Person;
import com.szy.xml.service.SAXPersonService;

/**
 * @author  coolszy
 * @blog    http://blog.csdn.net/coolszy
 */
public class SAXPersonServiceTest extends AndroidTestCase
{
	private static final String TAG="SAXPersonServiceTest";
	public void testReadXml() throws Exception
	{
		InputStream inStream=SAXPersonServiceTest.class.getClassLoader().getResourceAsStream("person.xml");
		List<Person> persons=SAXPersonService.readXml(inStream);
		for (Person person : persons)
		{
			Log.i(TAG, person.toString());
		}
	}
}
