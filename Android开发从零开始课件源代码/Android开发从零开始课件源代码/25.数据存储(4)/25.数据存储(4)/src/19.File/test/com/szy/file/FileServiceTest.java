package com.szy.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.test.AndroidTestCase;
import android.util.Log;

import com.szy.file.service.FileService;

/**
 * @author  coolszy
 * @blog    http://blog.csdn.net/coolszy
 */
public class FileServiceTest extends AndroidTestCase
{

	private static final String TAG = "FileService";

	public void testSave() throws IOException
	{
		OutputStream outStream = this.getContext().openFileOutput("coolszy.txt", Context.MODE_PRIVATE);
		FileService service = new FileService();
		service.save(outStream, "coolszy");
	}

	public void testRead() throws IOException
	{
		InputStream inStream = this.getContext().openFileInput("coolszy.txt");
		FileService service = new FileService();
		String content = service.read(inStream);
		Log.i(TAG, content);
	}

}
