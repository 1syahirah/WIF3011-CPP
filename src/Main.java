
public class Main  {
    public static void main(String[] args) throws Exception {
       Node<Integer> node = new Node<>();

        Thread writer = new Thread(new Write(node));
        Thread operator = new Thread(new Operate(node, 3, new Dummy())); // target = 3

        writer.start();
        operator.start();
    }
}
