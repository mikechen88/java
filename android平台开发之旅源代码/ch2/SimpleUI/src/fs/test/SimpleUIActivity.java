package fs.test;

import android.app.Activity;
import android.os.Bundle;
//Widget
import android.widget.Button;
import android.widget.TextView;
//
import android.view.View;
import android.view.View.OnClickListener;

public class SimpleUIActivity extends Activity implements OnClickListener {
	private TextView tvPanel = null;
	private Button btnGreeting = null;
	private int counter = 0;
	private OnClickListener listenerBtnGreeting = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        tvPanel = (TextView)findViewById(R.id.tvPanel);
        //tvPanel.setBackgroundResource(R.color.bkgColor);
        tvPanel.setBackgroundColor(0xffff0000);
        
        btnGreeting = (Button)findViewById(R.id.btnGreeting);
        //方式一
        //btnGreeting.setOnClickListener(this);
        //方式二
        /*
        btnGreeting.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
            	counter++;
            	tvPanel.setText("You click " + counter + " time(s)!");
        	}
        });
        */
        //方式三
        listenerBtnGreeting = new OnClickListener() {
        	public void onClick(View v) {
        		counter++;
        		tvPanel.setText("You click " + counter + " time(s)!");
        	}
        };
        btnGreeting.setOnClickListener(listenerBtnGreeting);
    }
    
    @Override
    public void onClick(View v) {
    	counter++;
    	tvPanel.setText("You click " + counter + " time(s)!");
    }
}