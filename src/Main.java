
public class Main  {
    public static void main(String[] args) throws Exception {
        RoomSemaphore sharedRoom = new RoomSemaphore();

        for(int i =1; i<=8; i++){
            new Thread(new GuestTask(sharedRoom, i)).start();
        }
        for(int i =1; i<=2; i++){
            new Thread(new CleanerTask(sharedRoom, i)).start();
        }

    }
}
