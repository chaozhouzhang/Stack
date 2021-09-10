package android.stack.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author zhangchaozhou
 * @time 2021/9/10 22:32
 */
public class CallableFuture {
    public static void main(String[] args) {

        callable();
        runnable();

    }

    public static void callable() {
        FutureTask futureTask = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 100;
            }
        });
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            Integer result = (Integer) futureTask.get();
            System.out.println(result);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void runnable() {
        FutureTask futureTask = new FutureTask<Integer>(new Runnable() {
            @Override
            public void run() {
                System.out.println();
            }
        }, 100);
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            Integer result = (Integer) futureTask.get();
            System.out.println(result);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
