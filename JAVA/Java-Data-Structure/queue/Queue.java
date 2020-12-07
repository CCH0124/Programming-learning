package queue;

public class Queue {
    public static void main(String[] args) {
        CircleArrayQueue c = new CircleArrayQueue(5);
        c.add(10);
        c.displayQueue();
        c.add(20);
        c.displayQueue();
        System.out.println(c.peek());
        System.out.println(c.peek());
        System.out.println(c.element());
        System.out.println(c.peek());
        c.displayQueue();
    }
}

class ArrayQueue {
    private int maxSize;
    private int front; // Queue header
    private int rear; // Queue tail
    private int[] arr;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }

    public boolean isFull() {
        return rear == (maxSize - 1);
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void add(int n) {
        if(isFull()) {
            throw new RuntimeException("is full");
        }
        rear++;
        this.arr[rear] = n;
    }

    public int element() {
        if(isEmpty()) {
            throw new RuntimeException("is empty");
        }
        front++;
        return arr[front];
    }

    public int peek(){
        if(isEmpty()) {
            throw new RuntimeException("is empty");
        }
        return arr[front+1];

    }

    public void display() {
        if(isEmpty()) {
            throw new RuntimeException("is empty");
        }
        for (int i=0; i<arr.length; i++){
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    public int size() {
        if(isEmpty()) return 0;
        return rear+1;
    }
}

class CircleArrayQueue {
    private int maxSize;
    private int front; // Queue header
    private int rear; // Queue tail
    private int[] arr;

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];

    }
    
    public boolean isFull() {
        return (rear+1)%maxSize == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void add(int n) {
        if(isFull()) {
            throw new RuntimeException("is full");
        }
        arr[rear] = n;
        rear = (rear+1)%maxSize;
    }

    public int element(){
        if(isEmpty()) {
            throw new RuntimeException("is empty");
        }
        int val = arr[front];
        front = (front+1)%maxSize;
        return val;
    }


    public void displayQueue() {
        if(isEmpty()) {
            throw new RuntimeException("is empty");
        }
        for(int i=front; i<(front+ size()); i++) {
            System.out.printf("arr[%d]=%d\n", i%maxSize, arr[i%maxSize]);
        }
    }

    public int size() {
        return (rear+maxSize-front)%maxSize;
    }

    public int peek() {
        if(isEmpty()) {
            throw new RuntimeException("is empty");
        }
        return arr[front];
    }
}