package mpd;

public class ThreadedMinimumPairwiseDistance implements MinimumPairwiseDistance {
    private long size;



    @Override
    public long minimumPairwiseDistance(int[] values) throws InterruptedException {
        size = values.length;
        Thread[] ourThreads = new Thread[4];
        ourThreads[0] = new Thread(new LowerLeft());
        ourThreads[1] = new Thread(new BottomRight());
        ourThreads[2] = new Thread(new TopRight());
        ourThreads[3] = new Thread(new Center());
        for (int i = 0; i < ourThreads.length; i++){
            ourThreads[i].start();
        }
        //throw new UnsupportedOperationException();
        for(int i=0; i<ourThreads.length;i++){
            ourThreads[i].join();
        }

        //return global number
        return 0;
    }

    public class LowerLeft implements Runnable{

        public void run() {
            for (int i = 0; i < values.length; ++i) {
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
            for (int i = 0; i < values.length; ++i) {
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

    public class TopRight implements Runnable{

        public void run() {
            for (int i = 0; i < values.length; ++i) {
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

    public class Center implements Runnable{

        public void run() {
            for (int i = 0; i < values.length; ++i) {
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


}
