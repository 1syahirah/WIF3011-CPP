public class PrintChar implements Runnable {
    private String letter;
    private int time;

    public PrintChar(String letter, int time){
        this.letter = letter;
        this.time = time;
    }

    public void printChar(String letter, int time ){
        while(time > 0){
            System.out.println(letter);
            time--;
            // try { Thread.sleep(10); } catch (Exception e) {}
        }
    }

    @Override
    public void run(){
        printChar(letter, time);
    }
}
