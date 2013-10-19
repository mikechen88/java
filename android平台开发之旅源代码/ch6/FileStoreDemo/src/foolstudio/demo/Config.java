package foolstudio.demo;

import java.io.File;

public interface Config {
	//Context
	public static final String CONTEXT_FILE = "FileStoreDemoContext.dat";
	//FileSystem
	public static final String BASE_DIR = "/sdcard";
	public static final String NEW_DIR = "foolstudio";
	public static final String NEW_FILE_NAME = "readme.txt";
	
	public static final String DEST_DIR = BASE_DIR + File.separator + NEW_DIR;
	public static final String DEST_FILE_NAME = DEST_DIR + File.separator + NEW_FILE_NAME;
};
