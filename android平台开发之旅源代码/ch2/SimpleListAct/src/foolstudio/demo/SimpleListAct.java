package foolstudio.demo;

import android.app.ListActivity;
import android.os.Bundle;

public class SimpleListAct extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ERROR/AndroidRuntime(725): 
        //Caused by: java.lang.RuntimeException: 
        //Your content must have a ListView whose id attribute is 'android.R.id.list'
        setContentView(R.layout.main);
    }
}