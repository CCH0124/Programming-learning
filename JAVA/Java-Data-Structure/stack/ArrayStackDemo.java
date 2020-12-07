package stack;
class ArrayStack {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }
    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value) {
        if (isFull()) {
            throw new RuntimeException("stack full");
        }
        top++;
        stack[top] = value;
    }

    public int pop(){
        if(isEmpty()) {
            throw new RuntimeException("stack Empty");
        }
        int value = stack[top];
        top--;
        return value;
    }
    public void display() {
        if(isEmpty()) {
            throw new RuntimeException("stack Empty");
        }
        for(int i=top; i>=0; i--){
            System.out.println(stack[i]);
        }
    }
}
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        stack.push(10);
        stack.display();
        System.out.println("===========================================================================");
        stack.push(20);
        stack.push(30);
        // stack.push(240);
        stack.display();
        System.out.println("===========================================================================");
        System.out.println(stack.pop());
        System.out.println("===========================================================================");
        stack.display();
        System.out.println("===========================================================================");
        System.out.println(stack.pop());
        System.out.println("===========================================================================");
        stack.push(30);
        stack.display();
        System.out.println("===========================================================================");

    }
}