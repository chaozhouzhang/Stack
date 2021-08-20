package android.stack.surface;

import android.Manifest;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.Surface;
import android.view.TextureView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class TextureViewActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, TextureView.SurfaceTextureListener {
    private TextureView mTextureView;
    private MediaPlayer mMediaPlayer;
    private Surface mSurface;
    private boolean mIsPlayed;

    private final String MP4_PATH = "https://www.usna.edu/Users/cs/adina/teaching/it472/resources/TheoryAlgorithmVideo.mp4";

    private final String  LOCAL_MP4_PATH = "/sdcard/mytest.1920x1080.mp4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texture_view);
        mTextureView = findViewById(R.id.texture_view);
        mTextureView.setSurfaceTextureListener(this);
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setOnPreparedListener(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},0);
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mIsPlayed = false;
        if (mMediaPlayer != null && !mMediaPlayer.isPlaying()) {
            mIsPlayed = true;
            mMediaPlayer.start();
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    @Override
    public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surface, int width, int height) {
        if (mSurface != null) {
            mSurface.release();
        }
        mSurface = new Surface(surface);
        if (mSurface!=null){
            System.out.println("surface:"+mSurface.toString());
        }
        if (!mIsPlayed) {
            try {
                mMediaPlayer.setSurface(mSurface);
                mMediaPlayer.reset();
                mMediaPlayer.setDataSource(LOCAL_MP4_PATH);
                mMediaPlayer.prepareAsync();
                mIsPlayed = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            mMediaPlayer.setSurface(mSurface);
            mMediaPlayer.start();
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surface) {
        if (mMediaPlayer != null) {
            mMediaPlayer.setSurface(null);
        }
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surface) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTextureView.setSurfaceTextureListener(null);
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        if (mSurface != null) {
            mSurface.release();
        }
    }
}
