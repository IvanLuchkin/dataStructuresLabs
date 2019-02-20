public class Stack {
    public int mSize;
    public int stackArray[];
    public int top;

    public Stack (int m) {
        this.mSize = m;
        stackArray = new int[mSize];
        top = -1;

    }

    public void push(int element) {
        if(this.isFull()){
            System.out.println("The stack is full, unable to push");
            return;
        } else {
            stackArray[++top] = element;
        }
    }

    public int pop() {
        if (this.isEmpty()) {
            System.out.println("The stack is empty");
            return 0;
        }else {
            return stackArray[top--];
        }
    }

    public int top() {
        return stackArray[top];
    }

    public int[] clear() {
        if(this.isEmpty()) {
            System.out.println("The stack is empty");
            return stackArray;
        }else {
            stackArray = new int[0];
            top = -1;
            return stackArray;
        }
    }

    public int size() {
        int temp = top + 1;
        return temp;
    }

    public boolean isEmpty() {
        return (top == - 1);
    }

    public boolean isFull() {
        return (top == mSize - 1);
    }

    public void printStack(int array[]) {
        for (int counter = 0; counter < array.length; counter++) {
            System.out.println(array[counter]);
        }
    }

    public void randPush(int a, int b) {
        while(!this.isFull()) {
            this.push((int)(Math.random() * (b - a) + a));
        }
    }
}
