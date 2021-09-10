package android.stack.thread;

/**
 * @author zhangchaozhou
 * @time 2021/9/9 23:07
 */
public class StackThread extends Thread {
    private int mNum = 0;

    @Override
    public void run() {
        super.run();
        mNum++;
        System.out.println(mNum);
    }

    public static void main(String[] args) {
        StackThread stackThread = new StackThread();
        stackThread.start();
        StackThread stackThread1 = new StackThread();
        stackThread1.start();
    }
}


