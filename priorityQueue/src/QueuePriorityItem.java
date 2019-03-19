class QueuePriorityItem {
    int data;
    int priority;
    QueuePriorityItem next;
    QueuePriorityItem prev;

    QueuePriorityItem(int data, int priority) {
        this.data = data;
        this.priority = priority;
        prev = null;
        next = null;
    }

    QueuePriorityItem(int data, int priority, QueuePriorityItem prev, QueuePriorityItem next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
        this.priority = priority;
    }

    public QueuePriorityItem getNext() {
        return next;
    }

    public QueuePriorityItem getPrev() {
        return prev;
    }

    public void setPrev(QueuePriorityItem prev) {
        this.prev = prev;
    }

    public void setNext(QueuePriorityItem next) {
        this.next = next;
    }

    public int getPriority() {
        return priority;
    }

    public int getData() {
        return data;
    }
}