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
        
        //初始化随机发生器
        mRandomFactor = new Random();       
        mImageMeshes = new ImageMesh[MESH_COUNT];
        
        //初始化图片显示
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
    //初始化图片显示
    private void initPictures() {
    	//图片索引的序列（随机生成的，非有序）
    	int picIndexesSeq[] = new int[MESH_COUNT];
    	
    	for(int k = 0; k < MESH_COUNT; ++k) { //初始化元素都为-1
    		picIndexesSeq[k] = -1;
    	}
    	
    	int no = 0;
    	//找不到-1表示所有索引都产出
    	while(getIndexOf(picIndexesSeq, -1) != NOT_FOUND) {
    		int index = generateIndex();
    		
    		if(getIndexOf(picIndexesSeq, index) == NOT_FOUND) { //该索引还没产出
    			picIndexesSeq[no++] = index;
    		}
    	}
    	
    	//调试用
    	for(int k = 0; k < MESH_COUNT; ++k) { //初始化元素都为-1
    		System.out.println("#" + (k+1) + " is NO." + picIndexesSeq[k]);
    	}
    	
        for(int i = 0; i < MESH_COUNT; ++i) { //为各个ImageView设置图片资源     
        	mImageMeshes[i] = new ImageMesh();
        	mImageMeshes[i].setImageView(
        			(ImageView)findViewById(mImageViewIds[i]) );        	
        	mImageMeshes[i].setImageResId(mPictureIds[picIndexesSeq[i]]);
        	mImageMeshes[i].getImageView().setOnTouchListener(
        			new OnTouchListener() {
    			//--------------------------------------------------------------
        		//图片点击事件响应
				@Override
				public boolean onTouch(View v, MotionEvent event) {					
					if(event.getAction() == MotionEvent.ACTION_DOWN) {
						ImageView iv = (ImageView)v;
						int id = iv.getId();
						 //获取该图片的编号
						int index = getIndexOf(mImageViewIds, id);
						
						//可移动，则与可移动的单元交换图片
						if(isAdjMoveable(index) == true) {
							mImageMeshes[mSpaceIndex].setImageResId(
									mImageMeshes[index].getImageResId() );
							
							mImageMeshes[index].setImageResId(-1);
							mSpaceIndex = index;	
						}
						else { //不可移动
							//如果抽取的那张已经填入则当前图片填充到抽出位置
							if(mSpaceIndex == -1) { 
								mOuterImageMesh.setImageResId(
										mImageMeshes[index].getImageResId() );
								
								mImageMeshes[index].setImageResId(-1);
								mSpaceIndex = index;	
							}
						}
						
						//检查游戏是否OK
						checkOK();
					}
					
					// TODO Auto-generated method stub
					return false;
				}    			
    		});
        }
        
        //第1张图片设置为空
        mImageMeshes[mSpaceIndex].setImageResId(-1);
        
        //抽出的1张图片
        mOuterImageMesh = new ImageMesh();
        mOuterImageMesh.setImageView((ImageView)findViewById(R.id.ivMiss) );
        mOuterImageMesh.setImageResId(mPictureIds[picIndexesSeq[0]]);
        mOuterImageMesh.getImageView().setOnTouchListener(
        		new OnTouchListener() {
			//--------------------------------------------------------------
        	//图片点击事件响应
			@Override
			public boolean onTouch(View v, MotionEvent event) {				
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					if(mSpaceIndex != -1) { //抽出Mesh中的图片不为空
						//将抽出Mesh的图片放入到当前的空位中
						mImageMeshes[mSpaceIndex].setImageResId(
								mOuterImageMesh.getImageResId() );
						mOuterImageMesh.setImageResId(-1);
						mSpaceIndex = -1;
						
						//检查游戏是否OK
						checkOK();						
					}
				}
				
				// TODO Auto-generated method stub
				return false;
			}    			
		});        
    }
    
    //--------------------------------------------------------------------------
    //获取指定值在数组中的位置（线性查找，效率低）
    private int getIndexOf(int indexes[], int val) {
    	for(int i = 0; i < indexes.length; ++i) {
    		if(indexes[i] == val) {
    			return (i);
    		}
    	}
    	
    	return (-1);
    }
    
    //--------------------------------------------------------------------------
    //获取生成的图片索引
    private int generateIndex() {      
        return (mRandomFactor.nextInt(MESH_COUNT) );
    }
    
    //--------------------------------------------------------------------------
    //判断指定索引的Mesh是否可移动（上下左右四个方向）
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
    //游戏结束判断
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
