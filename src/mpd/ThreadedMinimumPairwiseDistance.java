package mpd;

public class ThreadedMinimumPairwiseDistance implements MinimumPairwiseDistance {
    private int size;
    private long result;
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
        result=Integer.MAX_VALUE;
        //starting all the threads
        for (int i = 0; i < ourThreads.length; i++){
            ourThreads[i].start();
        }
        //waits for all the threads to finish
        for(int i=0; i<ourThreads.length;i++){
            ourThreads[i].join();
        }



        //return global number
        return result;
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
            updateGlobalResult(result);
        }
    }

    public class BottomRight implements Runnable{

        public void run() {
            for (int i = size/2; i < size; ++i) {
                for (int j = 0; j + size/2 < i; ++j) {
                    // Gives us all the pairs (i, j) where 0 <= j < i < values.length
                    long diff = Math.abs(values[i] - values[j]);
                    if (diff < result) {
                        result = diff;
                    }
                }
            }
            updateGlobalResult(result);
        }
    }

    public class TopRight implements Runnable{

        public void run() {
            for (int i = size/2; i < size; ++i) {
                for (int j = 0; j < i; ++j) {
                    // Gives us all the pairs (i, j) where 0 <= j < i < values.length
                    long diff = Math.abs(values[i] - values[j]);
                    if (diff < result) {
                        result = diff;
                    }
                }
            }
            updateGlobalResult(result);
        }
    }

    public class Center implements Runnable{

        public void run() {
            for (int i = size/2; i < size; ++i) {
                for (int j = 0; j + size/2 > i; ++j) {
                    // Gives us all the pairs (i, j) where 0 <= j < i < values.length
                    long diff = Math.abs(values[j] - values[i]);
                    if (diff < result) {
                        result = diff;
                    }
                }
            }
            updateGlobalResult(result);
        }
    }

    public synchronized void updateGlobalResult(long localResult){
        if(localResult < result){
            result = localResult;
        }
    }

}
