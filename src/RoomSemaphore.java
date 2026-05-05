import java.util.concurrent.Semaphore;

public class RoomSemaphore {
    private final Semaphore roomCapacity = new Semaphore(6, true);
    private final Semaphore cleaner = new Semaphore(1,true);

    public void guestEnter(int id){
       try {
            System.out.println("Guest " + id + " is waiting to enter...");
            
            roomCapacity.acquire(1);       // Take one of the 6 available spots
            
            System.out.println("Guest " + id + " entered the room. Total Guest in the room now are " + (6 - roomCapacity.availablePermits()));
            Thread.sleep(3000);            // Simulating stay time
            
            System.out.println("Guest " + id + " left the room");
            roomCapacity.release(1);       // Free up one spot
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void cleanerEnter(int id){
       try {
            System.out.println("Cleaner " + id + " is waiting to clean...");
            
            cleaner.acquire();        // Ensure only one cleaner acts at a time
            roomCapacity.acquire(6);       // Wait for all 6 guest slots to be empty
            
            System.out.println(">>> Cleaner " + id + " is now CLEANING the room.");
            Thread.sleep(2000);            // Simulating cleaning time
            
            System.out.println(">>> Cleaner " + id + " has finished cleaning.");
            roomCapacity.release(6);       // Open the room for guests again
            cleaner.release();
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } 
    }
}
