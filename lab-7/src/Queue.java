public class Queue {

    private int[] array;
    private int size;
    private int count;
    private int front;
    private int rear;

    public Queue(int queueSize) {
        size = queueSize;
        array = new int[size];
        front = 0;
        rear = -1;
        count = 0;
    }

    public void insert(int value) {
        if (rear == size - 1) rear = -1;
        array[++rear] = value;
        count++;
    }

    public int pop() {
        int temp = array[front++];
        if(front == size) front = 0;
        count--;
        return temp;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return (count == 0);
    }

}
