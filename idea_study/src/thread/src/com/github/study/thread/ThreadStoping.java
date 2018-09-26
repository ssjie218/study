package thread.src.com.github.study.thread;

/**
 * Created by pc on 2018/7/18.
 */
public class ThreadStoping {

    public static void main(String[] args) {
        class Mythread extends Thread {
            private boolean isStop;

            @Override
            public void run() {
                while (!isStop) {
                    System.out.println("running uuu");
                }
                System.out.println("running ssstop");
            }

            private void setStop() {
                isStop = true;
            }
        }
        Mythread t = new Mythread();
        t.start();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.setStop();


    }
}
