public class PrintNum implements Runnable {

    private int num;

    public PrintNum(int num){
        this.num = num;
    }

    public void printNum(int num){
        for(int i =1; i<= num; i++){
            System.out.println(i);
            // try { Thread.sleep(10); } catch (Exception e) {}
        }
    }

    @Override
    public void run(){
        printNum(num);
    }
}
