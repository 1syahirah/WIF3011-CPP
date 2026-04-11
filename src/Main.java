
public class Main  {
    public static void main(String[] args) throws Exception {
       Node<Integer> node = new Node<>();

        Thread writer = new Thread(new Write(node));
        Thread operator = new Thread(new Operate(node, 2, new Dummy())); 

        writer.start();
        operator.start();
    }
}
