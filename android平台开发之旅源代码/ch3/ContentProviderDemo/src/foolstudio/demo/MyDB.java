package foolstudio.demo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

public class MyDB extends ContentProvider {
	
	public static final String URI_AUTHORITY = "foolstudio.demo.MyDB";
	public static final String URI_PATH = "RecordSet";
	public static final String URI_PATH2 = "RecordSet/#";
	public static final int ALL_ROWS = 1;
	public static final int SINGLE_ROW = 2;
	//该URI的授权部分必须为有效类的全名
	public static final Uri CONTENT_URI = 
		Uri.parse("content://foolstudio.demo.MyDB/RecordSet");
	public static final UriMatcher uriMatcher;
	
	//
	public static final String _ID = "_id";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_SEX = "sex";
	
	public static final String COLUMN_NAMES[] = new String[] {
		_ID,
		FIELD_NAME,
		FIELD_SEX
	};
	
	public static MatrixCursor mCursor = new MatrixCursor(COLUMN_NAMES);		
	
	//
	public static final int INDEX_ID = 0;	
	public static final int INDEX_NAME = 1;
	public static final int INDEX_SEX = 2;
	
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(URI_AUTHORITY, URI_PATH, ALL_ROWS);
		uriMatcher.addURI(URI_AUTHORITY, URI_PATH2, SINGLE_ROW);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		switch(uriMatcher.match(uri) ) {
			case ALL_ROWS: {
				break;
			}
			case SINGLE_ROW: {
				break;
			}			
		}		
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		switch(uriMatcher.match(uri) ) {
			case ALL_ROWS: {
				return ("vnd.android.cursor.dir/vnd.foolstudio.MyDB");
				//break;
			}
			case SINGLE_ROW: {
				return ("vnd.android.cursor.item/vnd.foolstudio.MyDB");
				//break;
			}			
		}
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		switch(uriMatcher.match(uri) ) {
			case ALL_ROWS: {
				break;
			}
			case SINGLE_ROW: {
				break;
			}			
		}		
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		mCursor.addRow(new String[] {"1", "Paul", "Female"} );
		mCursor.addRow(new String[] {"2", "Leo", "Male"} );	
		
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		// TODO Auto-generated method stub
		switch(uriMatcher.match(uri) ) {
			case ALL_ROWS: {
				break;
			}
			case SINGLE_ROW: {
				break;
			}			
		}
		
		System.out.println("query: " + uri.toString() );
		
		return (mCursor);
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		switch(uriMatcher.match(uri) ) {
			case ALL_ROWS: {
				break;
			}
			case SINGLE_ROW: {
				break;
			}			
		}		
		return 0;
	}
};
