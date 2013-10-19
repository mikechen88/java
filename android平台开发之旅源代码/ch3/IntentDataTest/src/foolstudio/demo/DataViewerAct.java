package foolstudio.demo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class DataViewerAct extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_viewer_view);
        
        EditText txtData = (EditText)findViewById(R.id.TXT_DATA);
        //��ȡ��Activity�����ݵĸ�������
        Intent intent = this.getIntent();
        ArrayList<Kid> kids = 
        	intent.getParcelableArrayListExtra(IntentDataTestAct.EXTRAS_KEY);
        //ArrayList<Kid> kids = IntentDataTestAct.getInstance().getArrayList();        
        
        for(int i = 0; i < kids.size(); ++i) {
        	Kid kid = kids.get(i);
        	txtData.append("#" + i + " ========\n");        	
        	txtData.append(kid.toString() );
        	txtData.append("\n");
        }
        
        //���ؽ��
        Intent result = new Intent();
        result.putExtra("Msg", "Get " + kids.size() + " record(s).");
        this.setResult(IntentDataTestAct.REQ_CODE, result);
    }
};
