package ar.edu.itba.sia.utils;


public class TimeMetric {

    private Long start;
    private Long stop;

    public TimeMetric() {
        this.start = null;
        this.stop = null;
    }

    public void restartTime(){
        this.start = null;
        this.stop = null;
    }
    public void startTime() {
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
            return 0L;
        }
        return this.stop - this.start;
    }
}

