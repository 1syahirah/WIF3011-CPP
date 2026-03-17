public class PrintNum implements Runnable {

    private int num;
    private int id;
    private Control lock;

    public PrintNum(int num, int id, Control lock){
        this.num = num;
        this.id = id;
        this.lock = lock;
    }

    public void printNum(int num, int id, Control lock){
        for(int i =1; i<= num; i++){
                if (i <=5) {
                    synchronized(lock){
                     while (lock.turn != this.id) {
                    try {
                        lock.wait(); 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            System.out.println(i);
            lock.turn=3;
            lock.notifyAll();
                    }
            // try { Thread.sleep(10); } catch (Exception e) {}
            }else{
            System.out.println(i);
            }
        }
    
        }
    

    @Override
    public void run(){
        printNum(num, id, lock);
    }
}
