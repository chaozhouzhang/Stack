package android.stack.record;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;

/**
 * @author zhangchaozhou
 * @time 2021/10/13 23:15
 */
public class StackAudioRecord extends Thread {
    //音频录制对象
    private AudioRecord mAudioRecord;
    //音频最小缓冲区字节大小
    private int mAudioMinBufferSize;
    //音频来源：麦克风
    private final static int AUDIO_SOURCE = MediaRecorder.AudioSource.MIC;
    //音频采样率：44100HZ
    private final static int AUDIO_SAMPLE_RATE = 44100;
    //音频声道：单声道
    private final static int AUDIO_CHANNEL = AudioFormat.CHANNEL_IN_MONO;
    //音频编码格式：16位PCM
    private final static int AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT;
    private Status mStatus = Status.STATUS_NO_READY;

    /**
     * 缓冲区大小>0则初始化成功
     *
     * @return 缓冲区大小
     */
    public int init() {
        mAudioMinBufferSize = AudioRecord.getMinBufferSize(AUDIO_SAMPLE_RATE, AUDIO_CHANNEL, AUDIO_FORMAT);
        mAudioRecord = new AudioRecord(AUDIO_SOURCE, AUDIO_SAMPLE_RATE, AUDIO_CHANNEL, AUDIO_FORMAT, mAudioMinBufferSize);
        mStatus = Status.STATUS_READY;
        return mAudioMinBufferSize;
    }

    @Override
    public void run() {
        super.run();
        if (mStatus == Status.STATUS_NO_READY) {
            throw new IllegalStateException("录音尚未初始化，请检查是否禁止了录音权限。");
        }
        if (mStatus == Status.STATUS_START) {
            throw new IllegalStateException("正在录音。");
        }
        mAudioRecord.startRecording();
        mStatus = Status.STATUS_START;
        byte[] readBuffer = new byte[mAudioMinBufferSize];
        while (mStatus==Status.STATUS_START){
           int readSize =  mAudioRecord.read(readBuffer,0,mAudioMinBufferSize);
           if (readSize<=0){
               //
               break;
           }
        }
    }


    public void startRecord(){
        start();
    }

    public void stopRecord(){
        mStatus = Status.STATUS_STOP;
    }

    public void pauseRecord(){
        mStatus = Status.STATUS_PAUSE;
    }

    /**
     * 录音对象的状态
     */
    public enum Status {
        //未开始
        STATUS_NO_READY,
        //预备
        STATUS_READY,
        //录音
        STATUS_START,
        //暂停
        STATUS_PAUSE,
        //停止
        STATUS_STOP
    }
}
