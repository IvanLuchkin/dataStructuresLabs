public class Stack {

    private final int maxSize = 17;
    private final int[] stackArray;
    private int top;

    public Stack() {
        stackArray = new int[maxSize];
        top = -1;
    }

    public void push(int node) {
        stackArray[++top] = node;
    }

    public int pop() {
        return stackArray[top--];
    }

    public int peek() {
        return stackArray[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == maxSize-1);
    }
}