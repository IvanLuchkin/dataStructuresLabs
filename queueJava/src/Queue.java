public class Queue {
    private int[] queue;
    private int maxSize;
    private int size;
    private int front;
    private int rear;

    public Queue (int maxSize) {
        this.maxSize = maxSize;
        queue = new int[maxSize];
        rear = -1;
        front = 0;
        size = 0;
    }

    public void insert(int element) {
        if (rear == maxSize - 1) {
            rear = -1;
        }
        queue[++rear] = element;
        size++;
    }

    public int remove() {
        int temp = queue[front++];
        if(front == maxSize) {
            front = 0;
        }
        size--;
        return temp;
    }

    public int getFront() {
        return queue[front];
    }

    public int getRear() {
        return queue[rear];
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public boolean isFull() {
        return (size == maxSize);
    }

    public void printQueue() {
        for(int counter = 0; counter < size; counter++) {
            System.out.printf("%d, ", queue[counter]);
        }
    }

    public void insertRand(int a, int b) {
        while(!this.isFull()) {
            this.insert((int)(Math.random() * (b - a) + a));
        }
    }
}
