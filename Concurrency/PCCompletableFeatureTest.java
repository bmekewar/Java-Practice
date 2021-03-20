package Concurrency;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.PriorityBlockingQueue;

public class PCCompletableFeatureTest {

    final static int noOfEvents = 5;
    static BlockingQueue<EventPriority> queue = new PriorityBlockingQueue<EventPriority>();

    public static void main(String[] args) {

        CompletableFuture future = CompletableFuture.runAsync(new EventProducer(queue, noOfEvents))
                .thenRunAsync(() -> System.out.println(queue)).thenRunAsync(new EventConsumer(queue));
        // .completeExceptionally(new Exception("Error"));

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

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
                EventPriority event = null;
                try {
                    event = queue.take();
                    System.out.println("Consumed:" + event);
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    System.out.println(e1.getMessage());
                }
            }
        }
    }

}
