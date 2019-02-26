import java.util.Scanner;

public class LabArray {

    private static QueueArray duplicateElements(QueueArray queue) {
        int size = queue.getSize();
        QueueArray temp = new QueueArray((2 * size));
        for (int i = 0; i < size; i++) {
            int tempValue = queue.popRear();
            temp.pushFront(tempValue);
            temp.pushFront(tempValue);
        }
        return temp;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input the max size of the queue:");
        int size = input.nextInt();
        System.out.println("Input the lower edge of the range:");
        int lowerEdge = input.nextInt();
        System.out.println("Input the top edge of the range:");
        int topEdge = input.nextInt();
        QueueArray queue = new QueueArray(size);
        queue.insertRand(lowerEdge, topEdge);

        queue.printQueue();
        QueueArray newQueue = duplicateElements(queue);
        newQueue.printQueue();

    }
}
