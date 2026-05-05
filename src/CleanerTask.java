public class CleanerTask implements Runnable{
    private final RoomSemaphore room;
    private final int id;

    public CleanerTask(RoomSemaphore room, int id){
        this.room = room;
        this.id = id;
    }

    @Override
    public void run() {
            room.cleanerEnter(id);
     
    }

}
