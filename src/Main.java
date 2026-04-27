

public class Main  {
    public static void main(String[] args) throws Exception {
     SharedData sharedData = new SharedData();

        // Create the tasks
        Thread incrementThread = new Thread(new IncrementTask(sharedData));
        Thread checkThread = new Thread(new CheckTask(sharedData));

        // Start the tasks
        checkThread.start();     // Start checker first so it's ready to observe
        incrementThread.start();
    }
}
