public class PrintChar implements Runnable {
    private String letter;
    private int time;
    private Control lock;
    private int id;

    public PrintChar(String letter, int time, int id, Control lock){
        this.letter = letter;
        this.time = time;
        this.id = id;
        this.lock = lock;
    }

    public void printChar(String letter, int time, int id, Control lock ){
        // while(time > 0){
        //     System.out.println(letter);
        //     time--;
        //     // try { Thread.sleep(10); } catch (Exception e) {}
        // }
        for(int i = 0; i < time; i++){
            synchronized(lock){
                while (lock.turn != this.id) {
                    try{
                        lock.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    
                }

                System.out.println(letter);

                if(this.id == 1 ){
                    lock.turn =2;
                }else if (this.id == 3){
                    lock.turn = 1;
                }

                lock.notifyAll();
            }
        }
    }

    @Override
    public void run(){
        printChar(letter, time, id, lock);
    }
}
