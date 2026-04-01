import java.util.concurrent.Semaphore;

public class RoomSemaphore {
   private final Semaphore roomAccess = new Semaphore(1); 
    private final Semaphore guestLimit = new Semaphore(6); 
    private int guestCount = 0;
    private final Object lock = new Object();

    public void enterGuest(String name) throws InterruptedException {
        guestLimit.acquire(); 
        synchronized(lock) {
            if (guestCount == 0) roomAccess.acquire(); 
            guestCount++;
        }
        System.out.println(name + " entered. Guests in room: " + guestCount);
    }

    public void exitGuest(String name) {
        synchronized(lock) {
            guestCount--;
            if (guestCount == 0) roomAccess.release(); 
        }
        System.out.println(name + " left. Guests in room: " + guestCount);
        guestLimit.release();
    }

    public void enterCleaner(String name) throws InterruptedException {
        roomAccess.acquire(); 
        System.out.println(name + " (Cleaner) is now cleaning the room.");
    }

    public void exitCleaner(String name) {
        System.out.println(name + " (Cleaner) has finished and left.");
        roomAccess.release();
    }

}
