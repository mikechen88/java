package com.example.a72;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

	private static final int CAMERA_REQUEST = 1;
	private static final int GALLERY_REQUEST = 2;
	SharedPreferences settings;

	Button submit, help;
	EditText editText;
	ImageButton imageButton;
	TextView message;

	// Editor editor=settings.edit();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		settings = getSharedPreferences(SETTINGS_PREFS, Context.MODE_PRIVATE);
		submit = (Button) findViewById(R.id.submit);
		help = (Button) findViewById(R.id.menual);
		editText = (EditText) findViewById(R.id.name);
		imageButton = (ImageButton) findViewById(R.id.picture);
		message = (TextView) findViewById(R.id.help_text);
	
		initImage();
		initButton();
		initText();

	}

	public void initButton() {
		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String input = editText.getText().toString();
				if (input.equals("")) {
					message.setText(R.string.help_text);
				} else {
					savePreferences();
					startActivity(new Intent(MainActivity.this,
							SecondActivity.class));
				}
			}

		});

		help.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(MainActivity.this, HelpActivity.class));
				message.setText("");
			}

		});
	}

	protected void savePreferences() {
		// TODO Auto-generated method stub
		Editor editor = settings.edit();
		String firstName = editText.getText().toString();
		editor.putString(FIRST_NAME, firstName);
		editor.commit();

	}

	public void initText() {
		if (settings.contains(FIRST_NAME)) {
			String firstName = settings.getString(FIRST_NAME, "");
			editText.setText(firstName);
		}
	}

	public void initImage() {
		if (settings.contains(AVATAR)) {
			String uriString = settings.getString(AVATAR, "");
			if (!uriString.equals("")) {
				Uri imageUri = Uri.parse(uriString);
				imageButton.setImageURI(imageUri);
			}
		}

		if (settings.contains(AVATAR)) {
			String guriString = settings.getString(AVATAR, "");
			if (!guriString.equals("")) {
				Uri gimageUri = Uri.parse(guriString);
				imageButton.setImageURI(gimageUri);
			}
		}

		imageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent cameraIntent = new Intent(
						android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(
						Intent.createChooser(cameraIntent, "Take one photo"),
						CAMERA_REQUEST);

			}
		});

		imageButton.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View arg0) {
				// TODO Auto-generated method stub
				Intent pickGallery = new Intent(Intent.ACTION_PICK);
				pickGallery.setType("image/*");
				startActivityForResult(Intent.createChooser(pickGallery,
						"Choose form your gallery"), GALLERY_REQUEST);
				return true;
			}

		});
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (resultCode == Activity.RESULT_CANCELED)
			return;

		if (requestCode == CAMERA_REQUEST) {
			if (resultCode == Activity.RESULT_OK) {
				Bitmap cameraPic = (Bitmap) intent.getExtras().get("data");
				if (cameraPic == null)
					return;
				saveAvatar(cameraPic);
			}
		}

		else if (requestCode == GALLERY_REQUEST) {
			Uri galleryUri = intent.getData();
			if (galleryUri == null)
				return;

			try {
				Bitmap bitmap = Media.getBitmap(getContentResolver(),
						galleryUri);
				bitmap = scaleBitmap(bitmap, 100);
				saveAvatar(bitmap);
			} catch (Exception e) {
				return;
			}
		}

	}

	private Bitmap scaleBitmap(Bitmap bitmap, int maxSize) {
		int originalWidth = bitmap.getWidth();
		int originalHeight = bitmap.getHeight();

		int width = scaleSide(originalWidth, originalHeight, maxSize);
		int height = scaleSide(originalHeight, originalWidth, maxSize);

		return Bitmap.createScaledBitmap(bitmap, width, height, false);
	}

	private int scaleSide(int side1, int side2, int max) {
		if (side1 > side2)
			return max;
		return (int) ((double) side1 * (double) max / (double) side2);
	}

	public void saveAvatar(Bitmap pic) {
		String imageFile = "pic.png";
		try {
			pic.compress(CompressFormat.PNG, 50,
					openFileOutput(imageFile, MODE_PRIVATE));
		} catch (Exception e) {
			return;
		}
		Uri newUri = Uri.fromFile(new File(this.getFilesDir(), imageFile));
		imageButton.setImageURI(null);
		imageButton.setImageURI(newUri);

		Editor editor = settings.edit();
		editor.putString(AVATAR, newUri.getPath());
		editor.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		menu.findItem(R.id.settings).setIntent(
				new Intent(this, About.class));
		menu.findItem(R.id.options).setIntent(
				new Intent(this, FourthActivity.class));
		return true;
	}

}
