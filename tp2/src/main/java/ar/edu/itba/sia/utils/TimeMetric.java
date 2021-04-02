package ar.edu.itba.sia.utils;


public class TimeMetric {

    private Long start;
    private Long stop;

    public TimeMetric() {
        this.start = 0L;
        this.stop = 0L;
    }


    public void startTime() {
        if(this.start != null) {
            return;
        }
        this.start = System.currentTimeMillis();
    }

    public void stopTime() {
        if(this.start == null || this.stop != null) {
            return;
        }
        this.stop = System.currentTimeMillis();
    }

    public long getTime() {
        if(this.start == null || this.stop == null) {
            return 0;
        }
        return this.stop - this.start;
    }
}

