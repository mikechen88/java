package foolstudio.demo;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;

public class PictureReunionGameAct extends Activity {
	private final int MESH_COUNT = 16;
	private final int ROW_COUNT = 4;
	private final int COL_COUNT = 4;
	private final int NOT_FOUND = -1;
	
	private int mPictureIds[] = {
			R.drawable.sisters1x1,
			R.drawable.sisters1x2,
			R.drawable.sisters1x3,
			R.drawable.sisters1x4,
			R.drawable.sisters2x1,
			R.drawable.sisters2x2,
			R.drawable.sisters2x3,
			R.drawable.sisters2x4,
			R.drawable.sisters3x1,
			R.drawable.sisters3x2,
			R.drawable.sisters3x3,
			R.drawable.sisters3x4,
			R.drawable.sisters4x1,
			R.drawable.sisters4x2,
			R.drawable.sisters4x3,
			R.drawable.sisters4x4			
	};
	
	private int mImageViewIds[] = {
			R.id.iv11,
			R.id.iv12,
			R.id.iv13,
			R.id.iv14,
			R.id.iv21,
			R.id.iv22,
			R.id.iv23,
			R.id.iv24,
			R.id.iv31,
			R.id.iv32,
			R.id.iv33,
			R.id.iv34,
			R.id.iv41,
			R.id.iv42,
			R.id.iv43,
			R.id.iv44		
	};
	
	private ImageMesh[] mImageMeshes = null;
	private ImageMesh mOuterImageMesh = null;
	private Random mRandomFactor = null;
	private int mSpaceIndex = 0;	
	
	private Button mBtnRestart = null;	
	
	//--------------------------------------------------------------------------
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_game);       
        
        //��ʼ�����������
        mRandomFactor = new Random();       
        mImageMeshes = new ImageMesh[MESH_COUNT];
        
        //��ʼ��ͼƬ��ʾ
        initPictures();   
        
        mBtnRestart = (Button)findViewById(R.id.btnRestart);
        mBtnRestart.setOnClickListener(new OnClickListener() {
        	//------------------------------------------------------------------
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PictureReunionGameAct.this.finish();
			}        	
        });        
    }
    
    
    //--------------------------------------------------------------------------
    //��ʼ��ͼƬ��ʾ
    private void initPictures() {
    	//ͼƬ���������У�������ɵģ�������
    	int picIndexesSeq[] = new int[MESH_COUNT];
    	
    	for(int k = 0; k < MESH_COUNT; ++k) { //��ʼ��Ԫ�ض�Ϊ-1
    		picIndexesSeq[k] = -1;
    	}
    	
    	int no = 0;
    	//�Ҳ���-1��ʾ��������������
    	while(getIndexOf(picIndexesSeq, -1) != NOT_FOUND) {
    		int index = generateIndex();
    		
    		if(getIndexOf(picIndexesSeq, index) == NOT_FOUND) { //��������û����
    			picIndexesSeq[no++] = index;
    		}
    	}
    	
    	//������
    	for(int k = 0; k < MESH_COUNT; ++k) { //��ʼ��Ԫ�ض�Ϊ-1
    		System.out.println("#" + (k+1) + " is NO." + picIndexesSeq[k]);
    	}
    	
        for(int i = 0; i < MESH_COUNT; ++i) { //Ϊ����ImageView����ͼƬ��Դ     
        	mImageMeshes[i] = new ImageMesh();
        	mImageMeshes[i].setImageView(
        			(ImageView)findViewById(mImageViewIds[i]) );        	
        	mImageMeshes[i].setImageResId(mPictureIds[picIndexesSeq[i]]);
        	mImageMeshes[i].getImageView().setOnTouchListener(
        			new OnTouchListener() {
    			//--------------------------------------------------------------
        		//ͼƬ����¼���Ӧ
				@Override
				public boolean onTouch(View v, MotionEvent event) {					
					if(event.getAction() == MotionEvent.ACTION_DOWN) {
						ImageView iv = (ImageView)v;
						int id = iv.getId();
						 //��ȡ��ͼƬ�ı��
						int index = getIndexOf(mImageViewIds, id);
						
						//���ƶ���������ƶ��ĵ�Ԫ����ͼƬ
						if(isAdjMoveable(index) == true) {
							mImageMeshes[mSpaceIndex].setImageResId(
									mImageMeshes[index].getImageResId() );
							
							mImageMeshes[index].setImageResId(-1);
							mSpaceIndex = index;	
						}
						else { //�����ƶ�
							//�����ȡ�������Ѿ�������ǰͼƬ��䵽���λ��
							if(mSpaceIndex == -1) { 
								mOuterImageMesh.setImageResId(
										mImageMeshes[index].getImageResId() );
								
								mImageMeshes[index].setImageResId(-1);
								mSpaceIndex = index;	
							}
						}
						
						//�����Ϸ�Ƿ�OK
						checkOK();
					}
					
					// TODO Auto-generated method stub
					return false;
				}    			
    		});
        }
        
        //��1��ͼƬ����Ϊ��
        mImageMeshes[mSpaceIndex].setImageResId(-1);
        
        //�����1��ͼƬ
        mOuterImageMesh = new ImageMesh();
        mOuterImageMesh.setImageView((ImageView)findViewById(R.id.ivMiss) );
        mOuterImageMesh.setImageResId(mPictureIds[picIndexesSeq[0]]);
        mOuterImageMesh.getImageView().setOnTouchListener(
        		new OnTouchListener() {
			//--------------------------------------------------------------
        	//ͼƬ����¼���Ӧ
			@Override
			public boolean onTouch(View v, MotionEvent event) {				
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					if(mSpaceIndex != -1) { //���Mesh�е�ͼƬ��Ϊ��
						//�����Mesh��ͼƬ���뵽��ǰ�Ŀ�λ��
						mImageMeshes[mSpaceIndex].setImageResId(
								mOuterImageMesh.getImageResId() );
						mOuterImageMesh.setImageResId(-1);
						mSpaceIndex = -1;
						
						//�����Ϸ�Ƿ�OK
						checkOK();						
					}
				}
				
				// TODO Auto-generated method stub
				return false;
			}    			
		});        
    }
    
    //--------------------------------------------------------------------------
    //��ȡָ��ֵ�������е�λ�ã����Բ��ң�Ч�ʵͣ�
    private int getIndexOf(int indexes[], int val) {
    	for(int i = 0; i < indexes.length; ++i) {
    		if(indexes[i] == val) {
    			return (i);
    		}
    	}
    	
    	return (-1);
    }
    
    //--------------------------------------------------------------------------
    //��ȡ���ɵ�ͼƬ����
    private int generateIndex() {      
        return (mRandomFactor.nextInt(MESH_COUNT) );
    }
    
    //--------------------------------------------------------------------------
    //�ж�ָ��������Mesh�Ƿ���ƶ������������ĸ�����
    private boolean isAdjMoveable(int selfIndex) {
    	int rowNo = selfIndex/ROW_COUNT;
    	int colNo = selfIndex%COL_COUNT;
    	
    	//Top
    	if(rowNo > 0) {
    		int topRowNo = rowNo-1;
    		if( (topRowNo*ROW_COUNT+colNo) == mSpaceIndex) {
    			return (true);
    		}
    	}
    	
    	//Right
    	if(colNo < (COL_COUNT-1) ) {
    		int rightColNo = colNo+1;    		
    		if( (rowNo*ROW_COUNT+rightColNo) == mSpaceIndex) {
    			return (true);
    		}    		
    	}
    	
    	//Bottom
    	if(rowNo < (ROW_COUNT-1) ) {
    		int topRowNo = rowNo+1;
    		if( (topRowNo*ROW_COUNT+colNo) == mSpaceIndex) {
    			return (true);
    		}
    	}
    	
    	//Left
    	if(colNo > 0) {
    		int rightColNo = colNo-1;    		
    		if( (rowNo*ROW_COUNT+rightColNo) == mSpaceIndex) {
    			return (true);
    		}    		
    	}   	
    	
    	return (false);
    }
    
    //--------------------------------------------------------------------------
    //��Ϸ�����ж�
    private void checkOK() {
    	int i = 0;
    	
    	for(; i < MESH_COUNT; ++i) {
    		if(mImageMeshes[i].getImageResId() != mPictureIds[i]) {
    			break;
    		}
    	}
    	
    	if(i >= MESH_COUNT) { //OK
    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
    		builder.setMessage("Congratulations!!");
    		builder.setCancelable(false);
    		builder.setPositiveButton("OK", null);
    		
    		AlertDialog dlg = builder.create();
    		dlg.show();
    	}
    }    

	//--------------------------------------------------------------------------    
};
