import java.util.Scanner;

public class PriorityQueueLab {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of queues: ");
        int queueCountity = input.nextInt();
        System.out.println("Enter the size of queues: ");
        int queueSize = input.nextInt();
        PriorityQueue[] queues = new PriorityQueue[queueCountity];
        int[] prioritySums = new int[queueSize];
        int[] elementSums = new int[queueSize];
        PriorityQueue resultQueue = new PriorityQueue();

        for (int i = 0; i < queueCountity; i++) {
            PriorityQueue tempQueue = new PriorityQueue();
            tempQueue.randomFill(queueSize);
            System.out.println(tempQueue.toString());
            queues[i] = tempQueue;
        }

        for (PriorityQueue queue : queues) {
            int i = 0;
            while (queue.size() > 0) {
                QueuePriorityItem tempItem = queue.pop();
                elementSums[i] += tempItem.getData();
                prioritySums[i] += tempItem.getPriority();
                i++;
            }
        }

        for (int i = 0; i < queueSize; i++) {
            resultQueue.push(elementSums[i], prioritySums[i]);
        }

        System.out.println();
        System.out.println(resultQueue.toString());

    }

}
