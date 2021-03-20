package Concurrency;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class DelayedQueueTest {

    public static void main(String[] args) {

        final Integer noOfEvents = 5;
        final int delayEventBy = 100;
        BlockingQueue<EventDelayed> queue = new DelayQueue<EventDelayed>();
        AtomicInteger dl = new AtomicInteger(delayEventBy);

        final Runnable producer = () -> {
            while(queue.size()<noOfEvents){
                try {
                    String eventId = UUID.randomUUID().toString(); 
                    queue.offer(new EventDelayed(eventId, dl.getAndIncrement()));
                    System.out.println("Produced:" + eventId);
                } catch (Exception e) {
                    System.out.println("Producer interrupted");
                }
            }
        };

        final Runnable consumer = () -> {
            while(!queue.isEmpty()){
                try {
                    System.out.println("Consumed : " + queue.take());
                } catch (InterruptedException e) {
                    System.out.println("Consumer interrupted");
                }
            }
        };
        
        IntStream.range(1, noOfEvents).forEach(num -> {
			new Thread(producer).start();
		});

		try {
			Thread.sleep(10000);
            System.out.println("Queue:"+queue.toString());
		} catch (InterruptedException e) {
			System.out.println("Consumer interrupted");
		}
        
		IntStream.range(1, noOfEvents).forEach(num -> {
			new Thread(consumer).start();
		});

    }

    static class EventDelayed implements Delayed {

        private String data;
        private long startTime;
    
        public EventDelayed(String data, long delayInMilliseconds) {
            this.data = data;
            this.startTime = System.currentTimeMillis() + delayInMilliseconds;
        }
    
        @Override
        public int compareTo(Delayed o) {
            long l = this.startTime - ((EventDelayed) o).startTime;
            return (int) Math.max(Math.min(Integer.MAX_VALUE, l), Integer.MIN_VALUE);
        }
    
        @Override
        public long getDelay(TimeUnit unit) {
            long diff = startTime - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }
    
        @Override
        public String toString() {
            return "EventDelayed [data=" + data + ", startTime=" + startTime + "]";
        }
        
    }
    
}

