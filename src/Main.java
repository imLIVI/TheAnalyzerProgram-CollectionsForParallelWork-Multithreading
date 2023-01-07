import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    public static final int NUM_OF_TEXTS = 10_000;
    public static final int TIME_OF_ADDITION = 100;
    public static final int TIME_OF_TAKING = 300;
    public static BlockingQueue<String> arrayBlockingQueueA = new ArrayBlockingQueue<>(100);
    public static BlockingQueue<String> arrayBlockingQueueB = new ArrayBlockingQueue<>(100);
    public static BlockingQueue<String> arrayBlockingQueueC = new ArrayBlockingQueue<>(100);


    public static void main(String[] args) {
        // The thread that fills the queue
        new Thread(() -> {
            Random random = new Random();
            for (int i = 0; i < NUM_OF_TEXTS; i++) {
                try {
                    Thread.sleep(TIME_OF_ADDITION);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    arrayBlockingQueueA.put(generateText("abc", 3 + random.nextInt(3)));
                    arrayBlockingQueueB.put(generateText("abc", 3 + random.nextInt(3)));
                    arrayBlockingQueueC.put(generateText("abc", 3 + random.nextInt(3)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(arrayBlockingQueueA);
            }
        }).start();

        // Thread for 'a'
        new Thread(() -> {
            workOfThread('a', arrayBlockingQueueA);
        }).start();

        // Thread for 'b'
        new Thread(() -> {
            workOfThread('b', arrayBlockingQueueB);
        }).start();

        // Thread for 'c'
        new Thread(() -> {
            workOfThread('c', arrayBlockingQueueC);
        }).start();
    }

    public static void workOfThread(char countLetter, BlockingQueue<String> arrayBlockingQueue) {
        long count = 0;
        long maxCount = 0;
        String maxString = null;
        for (int i = 0; i < NUM_OF_TEXTS; i++) {
            try {
                Thread.sleep(TIME_OF_TAKING);
                String text = arrayBlockingQueue.take();
                for (char letter : text.toCharArray()) {
                    if (letter == countLetter)
                        count++;
                }
                if (maxCount < count) {
                    maxCount = count;
                    maxString = text;
                }
                count = 0;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Max number of '" + countLetter + "': " + maxCount + " (in the text: " + maxString + ")");
        }
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
