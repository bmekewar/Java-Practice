package Concurrency;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class PCDelayedQueueTest {
    public static void main(String[] args) {
        final int noOfEvents = 5;
        final long initialDelay =0;
        BlockingQueue<EventDelayed> queue = new DelayQueue<EventDelayed>();

        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(new EventProducer(queue, noOfEvents, initialDelay));
        try {
            service.awaitTermination(5, TimeUnit.SECONDS);
            System.out.println("\n"+queue);
        } catch (InterruptedException e) {
            System.out.println("Exception:" + e.getMessage());
        }

        service.submit(new EventConsumer(queue, noOfEvents));
        service.shutdown();

    }

    static class EventDelayed implements Delayed {

        private String data;
        private long startTime;

        public EventDelayed(String data, Long delayInNanoSeconds) {
            this.data = data;
            this.startTime = System.nanoTime()+ delayInNanoSeconds;
        }

        @Override
        public int compareTo(Delayed o) {
            long l = this.startTime - ((EventDelayed) o).startTime;
            return (int) Math.max(Math.min(Integer.MAX_VALUE, l), Integer.MIN_VALUE);
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long diff = startTime - System.nanoTime();
            return unit.convert(diff, TimeUnit.NANOSECONDS);
        }

        @Override
        public String toString() {
            return "EventDelayed [data=" + data + ", startTime=" + startTime + "]";
        }

    }

    static class EventProducer implements Runnable {

        BlockingQueue<EventDelayed> queue;
        Integer noOfEvents;
        Long initialDelay;
        AtomicLong delay = new AtomicLong();

        EventProducer(BlockingQueue<EventDelayed> queue, Integer noOfEvents, long initialDelay) {
            this.queue = queue;
            this.noOfEvents = noOfEvents;
            this.initialDelay = initialDelay;
            delay.addAndGet(initialDelay);
        }

        @Override
        public void run() {
            while (queue.size() < noOfEvents) {
                String eventId = UUID.randomUUID().toString();
                queue.offer(new EventDelayed(eventId,delay.incrementAndGet()));
                System.out.println("Produced:" + eventId);
            }
        }
    }

    static class EventConsumer implements Runnable {

        BlockingQueue<EventDelayed> queue;
        Integer noOfEvents;

        EventConsumer(BlockingQueue<EventDelayed> queue, Integer noOfEvents) {
            this.queue = queue;
            this.noOfEvents = noOfEvents;
        }

        @Override
        public void run() {
            while (!queue.isEmpty()) {
                EventDelayed event =null;
                try {
                    event = queue.take();
                    System.out.println("Consumed:" +event);
                } catch (InterruptedException e1) {
                    System.out.println(e1.getMessage());
                }
            }
        }
    }
}
