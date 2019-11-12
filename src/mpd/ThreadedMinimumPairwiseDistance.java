package mpd;

public class ThreadedMinimumPairwiseDistance implements MinimumPairwiseDistance {
    private int size;
    private long result;
    private long result1;
    private long result2;
    private long result3;
    private int[] values;



    @Override
    public long minimumPairwiseDistance(int[] values) throws InterruptedException {
        size = values.length;
        this.values = values;
        Thread[] ourThreads = new Thread[4];
        ourThreads[0] = new Thread(new LowerLeft());
        ourThreads[1] = new Thread(new BottomRight());
        ourThreads[2] = new Thread(new TopRight());
        ourThreads[3] = new Thread(new Center());
        this.result=Integer.MAX_VALUE;
        this.result1=Integer.MAX_VALUE;
        this.result2=Integer.MAX_VALUE;
        this.result3=Integer.MAX_VALUE;
        for (int i = 0; i < ourThreads.length; i++){
            ourThreads[i].start();
        }
        //throw new UnsupportedOperationException();
        for(int i=0; i<ourThreads.length;i++){
            ourThreads[i].join();
        }
        long finalresult;
        
        for(int i =0; i < values.length; i++){

        }

        //return global number
        return finalresult;
    }

    public class LowerLeft implements Runnable{

        public void run() {
            for (int i = 0; i < size/2; ++i) {
                for (int j = 0; j < i; ++j) {
                    // Gives us all the pairs (i, j) where 0 <= j < i < values.length
                    long diff = Math.abs(values[i] - values[j]);
                    if (diff < result) {
                        result = diff;
                    }
                }
            }
        }
    }

    public class BottomRight implements Runnable{

        public void run() {
            for (int i = size/2; i < size; ++i) {
                for (int j = 0; j + size/2 < i; ++j) {
                    // Gives us all the pairs (i, j) where 0 <= j < i < values.length
                    long diff = Math.abs(values[i] - values[j]);
                    if (diff < result1) {
                        result1 = diff;
                    }
                }
            }
        }
    }

    public class TopRight implements Runnable{

        public void run() {
            for (int i = size/2; i < size; ++i) {
                for (int j = 0; j < i; ++j) {
                    // Gives us all the pairs (i, j) where 0 <= j < i < values.length
                    long diff = Math.abs(values[i] - values[j]);
                    if (diff < result2) {
                        result2 = diff;
                    }
                }
            }
        }
    }

    public class Center implements Runnable{

        public void run() {
            for (int i = size/2; i < size; ++i) {
                for (int j = 0; j + size/2 >= i; ++j) {
                    // Gives us all the pairs (i, j) where 0 <= j < i < values.length
                    long diff = Math.abs(values[i] - values[j]);
                    if (diff < result3) {
                        result3 = diff;
                    }
                }
            }
        }
    }


}
