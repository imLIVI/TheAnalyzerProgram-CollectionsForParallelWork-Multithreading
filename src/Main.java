import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    public static final int NUM_OF_TEXTS = 10_000;
    public static BlockingQueue<String> arrayBlockingQueueA = new ArrayBlockingQueue<>(100);
    public static BlockingQueue<String> arrayBlockingQueueB = new ArrayBlockingQueue<>(100);
    public static BlockingQueue<String> arrayBlockingQueueC = new ArrayBlockingQueue<>(100);


    public static void main(String[] args) {
        // The thread that fills the queue
        new Thread(() -> {
            Random random = new Random();
            for (int i = 0; i < NUM_OF_TEXTS; i++) {
                try {
                    arrayBlockingQueueA.put(generateText("abc", 3 + random.nextInt(3)));
                    arrayBlockingQueueB.put(generateText("abc", 3 + random.nextInt(3)));
                    arrayBlockingQueueC.put(generateText("abc", 3 + random.nextInt(3)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // Thread for 'a'
        new Thread(() -> {

        }).start();

        // Thread for 'b'
        new Thread(() -> {

        }).start();

        // Thread for 'c'
        new Thread(() -> {

        }).start();


    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}
