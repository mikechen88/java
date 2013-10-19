package szy.android.activity;

import java.io.InputStream;
import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.szy.xml.model.Person;
import com.szy.xml.service.DOMPersonService;

/**
 * @author  coolszy
 * @blog    http://blog.csdn.net/coolszy
 */
public class DOMPersonServiceTest extends AndroidTestCase
{
	private static final String TAG="PersonServiceTest";
	public void testReadXml() throws Exception
	{
		InputStream inStream=DOMPersonServiceTest.class.getClassLoader().getResourceAsStream("person.xml");
		List<Person> persons=DOMPersonService.readXml(inStream);
		for (Person person : persons)
		{
			Log.i(TAG, person.toString());
		}
	}
}
