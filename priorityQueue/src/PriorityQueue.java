

public class PriorityQueue {
    protected QueuePriorityItem front;
    protected QueuePriorityItem back;
    protected int size;

    public PriorityQueue() {
        front = null;
        back = null;
        size = 0;
    }

    public void push(int data, int priority) {
        size++;
        if (front == null) {
            front = new QueuePriorityItem(data, priority);
            back = front;
            return;
        }

        QueuePriorityItem curr = front;
        while (curr != null && curr.getPriority() <= priority) {
            curr = curr.getNext();
        }

        if (curr == null) {
            QueuePriorityItem prev = back;
            back = new QueuePriorityItem(data, priority, prev, null);
            prev.setNext(back);
            return;
        }
        QueuePriorityItem item = new QueuePriorityItem(data, priority, curr.getPrev(), curr);
        if (curr.getPrev() != null) {
            curr.getPrev().setNext(item);
        } else {
            front = item;
        }
        curr.setPrev(item);
    }

    public QueuePriorityItem pop() {
        if (front == null) {
            System.out.println("The queue is empty.");
            return null;
        } else {
            size--;
            QueuePriorityItem tempItem = front;
            front = front.getNext();
            return tempItem;
        }
    }

    public int back() { // Возврат конца очереди без удаления
        return back.getData();
    }

    public int front() { // Возврат начала очереди без удаления
        return front.getData();
    }

    public int size() { // Возврат размера очереди
        return size;
    }

    public void clear() {
        QueuePriorityItem item = front;
        front = null;
        back = null;
        size = 0;
        while (item != null) {
            QueuePriorityItem buff = item;
            item = item.getNext();
            buff.setPrev(null);
            buff.setNext(null);
        }
    }

    public boolean isEmpty() { // Проверка пуста ли очередь
        return (size <= 0);
    }

    public int valueOf(int index) {
        QueuePriorityItem item = front;
        while (index > 0) {
            item = item.getNext();
            if (item == null) {
                System.out.println("Current item is empty.");
            }
            index--;
        }
        return item.getData();
    }

    public void randomFill(int n) {
        for (int i = 0; i < n; i++) {
            this.push((int)(Math.random() * 100), (int)(Math.random() * 50));
        }
    }

    public String toString() {
        String str = "[";
        QueuePriorityItem item = front;

        while (item != back) {
            str += item.getData() + ":" + item.getPriority() + ", ";
            item = item.getNext();
        }

        if (item != null) {
            str += item.getData() + ":" + item.getPriority();
        }

        str += "]";

        return str;
    }
}