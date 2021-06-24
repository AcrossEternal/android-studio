package cn.itcast.videoview;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;
public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {
    private EditText et_path;
    private ImageView bt_play;
    private VideoView videoView;
    private MediaController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_path = (EditText) findViewById(R.id.et_path);
        bt_play = (ImageView) findViewById(R.id.bt_play);
        videoView = (VideoView) findViewById(R.id.video_view);
        controller = new MediaController(this);
        videoView.setMediaController(controller);
        bt_play.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_play:
                play();
                break;
        }
    }
    // 播放视频
    private void play() {
        if (videoView != null && videoView.isPlaying()) {
            bt_play.setImageResource(android.R.drawable.ic_media_play);
            videoView.stopPlayback();
            return;
        }
        videoView.setVideoPath(et_path.getText().toString());
        videoView.start();
        bt_play.setImageResource(android.R.drawable.ic_media_pause);
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                bt_play.setImageResource(android.R.drawable.ic_media_play);
            }
        });
    }
}
