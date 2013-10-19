package foolstudio.demo;

import android.app.Activity;
import android.os.Bundle;
//import android.util.Log;
import android.widget.TextView;

public class FirstActivityAct extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //通过资源ID获取文本条对象
        TextView textView = (TextView)findViewById(R.id.TEXT_VIEW);
        /*
        if(textView == null) { //异常检查
        	Log.d(getClass().getName(), "Get text view failed!");
        	return;
        }
        */        
        textView.setText("This is my first Android application!");
    }
};