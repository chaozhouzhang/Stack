package android.stack.surface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SurfaceViewActivity extends AppCompatActivity implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {

    private SurfaceView mSurfaceView;
    private MediaPlayer mMediaPlayer;
    private SurfaceHolder mSurfaceHolder;
    private final String MP4_PATH = "https://www.usna.edu/Users/cs/adina/teaching/it472/resources/TheoryAlgorithmVideo.mp4";
    private final String LOCAL_MP4_PATH = "/sdcard/mytest.1920x1080.mp4";
    private boolean mIsPlayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_view);
        mSurfaceView = findViewById(R.id.surface_view);

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnErrorListener(this);

        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        //create media player after surface created
        try {
            // connect surface view and media player
            if (!mIsPlayed) {
                mMediaPlayer.setDisplay(mSurfaceHolder);
                Surface surface = mSurfaceHolder.getSurface();
                if (surface!=null){
                    System.out.println("surface:"+surface.toString());
                }
                mMediaPlayer.setDataSource(LOCAL_MP4_PATH);
                mMediaPlayer.prepareAsync();
                mIsPlayed = true;
            } else {
                mMediaPlayer.setDisplay(mSurfaceHolder);
                mMediaPlayer.start();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Surface:" + exception.getMessage());
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        // start media player after prepared
        mp.start();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // release media player after destroy
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            }
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        System.out.println("Surface:" + what + ":" + extra);
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mIsPlayed = false;
        if (mMediaPlayer != null && !mMediaPlayer.isPlaying()) {
            mIsPlayed = true;
            mMediaPlayer.start();
        }
    }
}