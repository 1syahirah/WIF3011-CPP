public class Operate implements Runnable {
    private Node<Integer> node;
    private Integer target;
    private Runnable task;
    private int count = 0;

    public Operate(Node<Integer> node, Integer target, Runnable task) {
        this.node = node;
        this.target = target;
        this.task = task;
    }

    @Override
    public void run() {
        while (count < 2) {
            node.executeOnValue(target, task);
            count++;
        }

        System.out.println("Task executed twice. Program terminating.");
        System.exit(0); // stop program after 2 executions
    }
}
