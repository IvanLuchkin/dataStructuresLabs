public class Stack {
    public int mSize;
    public int stackArray[];
    public int top;

    public Stack (int m) {
        this.mSize = m;
        stackArray = new int[mSize];
        top = -1;

    }

    public void addElement (int element) {
        stackArray[++top] = element;
    }

    public int deleteElement () {
        return stackArray[top--];
    }

    public int readTop() {
        return stackArray[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == mSize -1);
    }

    public void printStack(int array[]) {
        for (int counter = 0; counter < array.length; counter++) {
            System.out.println(array[counter]);
        }
    }



}
