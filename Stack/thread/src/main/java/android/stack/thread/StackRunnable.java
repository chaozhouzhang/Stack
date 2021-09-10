package android.stack.thread;

/**
 * @author zhangchaozhou
 * @time 2021/9/9 23:25
 */
public class StackRunnable implements Runnable{
    private int mNum=0;
    @Override
    public void run() {
        mNum++;
        System.out.println(mNum);
    }

    public static void main(String[] args) {
        StackRunnable stackRunnable = new StackRunnable();
        Thread thread = new Thread(stackRunnable);
        thread.start();
        Thread thread1 = new Thread(stackRunnable);
        thread1.start();
    }
}
