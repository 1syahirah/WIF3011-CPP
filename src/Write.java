import java.util.Random;

public class Write implements Runnable {
    private Node<Integer> node;
    private Random rand = new Random();

    public Write(Node<Integer> node) {
        this.node = node;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int value = rand.nextInt(5); // 0–4
                node.setValue(value);
                Thread.sleep(500); // slow down for visibility
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
