
public class Main  {
    public static void main(String[] args) throws Exception {
         RoomSemaphore room = new RoomSemaphore();

        // Thread for a Cleaner
        Thread cleaner = new Thread(() -> {
            try {
                room.enterCleaner("Cleaner_1");
                Thread.sleep(2000); // Simulating cleaning time
                room.exitCleaner("Cleaner_1");
            } catch (InterruptedException e) { e.printStackTrace(); }
        });

        // Threads for Guests
        for (int i = 1; i <= 8; i++) {
            String name = "Guest_" + i;
            new Thread(() -> {
                try {
                    room.enterGuest(name);
                    Thread.sleep(1000); // Simulating stay time
                    room.exitGuest(name);
                } catch (InterruptedException e) { e.printStackTrace(); }
            }).start();
        }

        cleaner.start();
    }
}
