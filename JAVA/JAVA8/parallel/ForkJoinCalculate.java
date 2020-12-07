package parallel;

import java.util.concurrent.RecursiveTask;

public class ForkJoinCalculate extends RecursiveTask<Long> {
    private static final long serialVersionUID = 123213232155L;
    private long start;
    private long end;
    
    private static final long THRESHOLD = 10000;
    
    public ForkJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }
    
    @Override
    protected Long compute() {
        long length = end - start;
        
        if (length <= THRESHOLD) {
            long sum = 0;
            for (long i=start; i<=end; i++) {
                sum += i;
            }
            return sum;
        } else {
           long middle = (start + end)/2;
           ForkJoinCalculate left = new ForkJoinCalculate(start, middle);
           left.fork();
           
           ForkJoinCalculate right = new ForkJoinCalculate(middle+1, end);
           right.fork();
           
           return left.join() + right.join();
        }
    }
}
