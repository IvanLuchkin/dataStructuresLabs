import java.util.Scanner;

public class PriorityQueueLab {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int queueCountity = input.nextInt();
        int queueSize = input.nextInt();
        PriorityQueue[] queues = new PriorityQueue[queueCountity];
        int[] prioritySums = new int[queueSize];
        int[] elementSums = new int[queueSize];
        PriorityQueue resultQueue = new PriorityQueue();

       /* for (int i = 0; i < queueSize; i++) {
            prioritySums[i] = 0;
            elementSums[i] = 0;
        } */

        for (int i = 0; i < queueCountity; i++) {
            PriorityQueue tempQueue = new PriorityQueue();
            tempQueue.randomFill(queueSize);
            System.out.println(tempQueue.toString());
            queues[i] = tempQueue;
        }

        for (PriorityQueue queue : queues) {
            for (int i = 0; i < queueSize; i++) {
                QueuePriorityItem tempItem = queue.pop();
                elementSums[i] += tempItem.getData();
                prioritySums[i] += tempItem.getPriority();
            }
        }

        for (int i = 0; i < queueSize; i++) {
            resultQueue.push(elementSums[i], prioritySums[i]);
        }

        System.out.println();
        System.out.println(resultQueue.toString());

    }

}
