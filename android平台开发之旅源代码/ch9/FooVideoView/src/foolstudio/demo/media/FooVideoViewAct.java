package foolstudio.demo.media;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class FooVideoViewAct extends Activity implements OnCompletionListener, OnPreparedListener {
    private VideoView mVideoView = null;
    private MediaController mController = null;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mVideoView = (VideoView)findViewById(R.id.VV_MAIN);
        mController = (MediaController)findViewById(R.id.MC_MAIN);
        
        //…Ë÷√’ÏÃ˝∆˜
        mVideoView.setOnCompletionListener(this);
        mVideoView.setOnPreparedListener(this);
        //
        mVideoView.setVideoPath("/sdcard/fish.3gp");
        mVideoView.setMediaController(mController);             
    }

	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		if(mVideoView != null) {
			mVideoView.stopPlayback();
		}
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub
	}
}