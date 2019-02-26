import java.util.Scanner;

public class LabLinkedList {

    public static QueueLinkedList duplicateElements(QueueLinkedList queue) {
        QueueLinkedList temp = new QueueLinkedList();
        while(!queue.isEmpty()) {
            int tempValue = queue.popBottom().value;
            temp.pushTop(tempValue);
            temp.pushTop(tempValue);
        }
        return temp;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the lower edge of the values' range:");
        int lowerEdge = input.nextInt();
        System.out.println("Enter the higher edge of the values' range:");
        int higherEdge = input.nextInt();
        System.out.println("Enter the size of the queue:");
        int userSize = input.nextInt();

        QueueLinkedList queue = new QueueLinkedList();
        queue.fillQueue(userSize, lowerEdge, higherEdge);
        QueueLinkedList newQueue1 = queue.printQueue();
        QueueLinkedList newQueue2 = duplicateElements(newQueue1);
        newQueue2.printQueue();


    }

}
