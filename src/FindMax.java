class FindMax implements Runnable{
    private int[] arr;
    private int start, end;
    private int localMax;

    public FindMax(int[] arr, int start, int end){
        this.arr = arr;
        this.start = start;
        this.end = end;
    }
    @Override
    public void run(){
        localMax =  arr[start];

        for(int i = start +1; i<end ; i++){
            if(arr[i] > localMax){
                localMax = arr[i];
            }
        }
    }

    public int getLocalMax(){
        return localMax;
    }
}