package foolstudio.android.MyFirstAndroidApp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
//import android.widget.Button;

public class MyFirstAndroidApp extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.main);
        TextView view = new TextView(this);
        view.setText("Hello, this is my first android application!");
        
        //Button btn = new Button(this);
        
        this.setContentView(view);
    }
};