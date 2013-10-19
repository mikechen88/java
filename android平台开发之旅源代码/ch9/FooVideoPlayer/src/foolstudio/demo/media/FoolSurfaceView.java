package foolstudio.demo.media;

import java.io.IOException;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class FoolSurfaceView extends SurfaceView implements SurfaceHolder.Callback,
															MediaPlayer.OnPreparedListener,
															OnCompletionListener {
	//��Ƶ������
	private MediaPlayer mVideoPlayer = null;
	
	public FoolSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.getHolder().addCallback(this);
		this.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		// TODO Auto-generated method stub
	}

	//���洴�����
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		try {
			initPlayer();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	//��ʼ����Ƶ������
	private void initPlayer() throws IllegalArgumentException, IllegalStateException, IOException {
		// TODO Auto-generated method stub
		mVideoPlayer = new MediaPlayer();
		mVideoPlayer.reset();
    	mVideoPlayer.setDataSource("/sdcard/fish.3gp");
    	mVideoPlayer.setDisplay(this.getHolder() );
    	mVideoPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    	//��������
    	mVideoPlayer.setVolume(80, 100);
    	//���ò���Ԥ��������
    	mVideoPlayer.setOnPreparedListener(this);
    	//���ò������������
    	mVideoPlayer.setOnCompletionListener(this);
    	mVideoPlayer.prepare();		
    	
    	Log.d(this.getClass().getSimpleName(), 
    			"Width="+mVideoPlayer.getVideoWidth()+ 
    			", height=" +mVideoPlayer.getVideoHeight() );
	}	

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
	}
	
	//������׼������
	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub
		if(mVideoPlayer != null) {
			mVideoPlayer.start();
		}
	}

	//�������
	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		if(mVideoPlayer != null) {		
			mVideoPlayer.stop();
			mVideoPlayer.release();
			mVideoPlayer = null;
		}		
	}	
};
