package Concurrency;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class PCPriorityQueueTest {

    public static void main(String[] args) {
        final int noOfEvents = 5;
        BlockingQueue<EventPriority> queue = new PriorityBlockingQueue<EventPriority>();

        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(new EventProducer(queue, noOfEvents));
        try {
            service.awaitTermination(5, TimeUnit.SECONDS);
            System.out.println("\n"+queue);
        } catch (InterruptedException e) {
            System.out.println("Exception:" + e.getMessage());
        }

        service.submit(new EventConsumer(queue));
        service.shutdown();

    }

    static class EventPriority implements Comparable {

        private String data;
        private long startTime;

        public EventPriority(String data) {
            this.data = data;
            this.startTime = System.nanoTime();
        }

        @Override
        public int compareTo(Object o) {
            long time = this.startTime - ((EventPriority) o).startTime;
            return (int) Math.max(Math.min(Integer.MAX_VALUE, time), Integer.MIN_VALUE);
        }

        @Override
        public String toString() {
            return "EventPriority [data=" + data + ", startTime=" + startTime + "]";
        }

    }

    static class EventProducer implements Runnable {

        BlockingQueue<EventPriority> queue;
        Integer noOfEvents;

        EventProducer(BlockingQueue<EventPriority> queue, Integer noOfEvents) {
            this.queue = queue;
            this.noOfEvents = noOfEvents;
        }

        @Override
        public void run() {
            while (queue.size() < noOfEvents) {
                String eventId = UUID.randomUUID().toString();
                queue.offer(new EventPriority(eventId));
                System.out.println("Produced:" + eventId);
            }
        }
    }

    static class EventConsumer implements Runnable {

        BlockingQueue<EventPriority> queue;

        EventConsumer(BlockingQueue<EventPriority> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (!queue.isEmpty()) {
                EventPriority event =null;
                try {
                    event = queue.take();
                    System.out.println("Consumed:" +event);
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    System.out.println(e1.getMessage());
                }
            }
        }
    }
}
