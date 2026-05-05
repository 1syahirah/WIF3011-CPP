public class GuestTask implements Runnable{
    private final RoomSemaphore room;
    private final int id;

    public GuestTask(RoomSemaphore room, int id){
        this.room = room;
        this.id = id;
    }
    
    @Override
    public void run() {
            room.guestEnter(id);
    }
}
