public class QueueTest {
    public static void main(String[] args) {
        Queue queue = new Queue(15);
        queue.insertRand(0, 10);
        queue.printQueue();
        System.out.println(queue.remove());

    }
}
