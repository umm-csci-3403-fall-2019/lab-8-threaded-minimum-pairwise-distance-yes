package mpd;

public class ThreadedMinimumPairwiseDistance implements MinimumPairwiseDistance {
    private int size;
    private long result;
    private int[] values;



    @Override
    public long minimumPairwiseDistance(int[] values) throws InterruptedException {
        this.size = values.length;
        this.values = values;
        Thread[] ourThreads = new Thread[4];
        ourThreads[0] = new Thread(new LowerLeft());
        ourThreads[1] = new Thread(new BottomRight());
        ourThreads[2] = new Thread(new TopRight());
        ourThreads[3] = new Thread(new Center());
        this.result=Integer.MAX_VALUE;
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
            for (int i = size-size; i < size/2; ++i) {
                for (int j = 0; j < i; ++j) {
                    long diff = Math.abs(values[i] - values[j]);
                    if (diff < result) {
                        updateGlobalResult(diff);
                    }
                    if(result == 0){break;}
                }
            }
        }
    }

    public class BottomRight implements Runnable{

        public void run() {
            for (int i = size/2; i < size; ++i) {
                for (int j = 0; j + size/2 < i; ++j) {
                    long diff = Math.abs(values[i] - values[j]);
                    if (diff < result) {
                        updateGlobalResult(diff);
                    }
                    if(result == 0){break;}
                }
            }
        }
    }

    public class TopRight implements Runnable{

        public void run() {
            for (int i = size/2; i < size; ++i) {
                for (int j = 0; j < i; ++j) {
                    long diff = Math.abs(values[i] - values[j]);
                    if (diff < result) {
                        updateGlobalResult(diff);
                    }
                    if(result == 0){break;}
                }
            }
        }
    }

    public class Center implements Runnable{
        public void run() {
            for (int i = size/2; i < size; ++i) {
                for (int j = 0; j + size/2 > i; ++j) {
                    long diff = Math.abs(values[j] - values[i]);
                    if (diff < result) {
                        updateGlobalResult(diff);
                    }
                    if(result == 0){break;}
                }
            }
        }
    }

    public synchronized void updateGlobalResult(long localResult){
        if(localResult < result){
            result = localResult;
        }
    }

}
