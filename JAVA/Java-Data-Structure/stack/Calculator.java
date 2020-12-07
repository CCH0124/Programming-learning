package stack;

class ArrayStack2 {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    public int peek() {
        return stack[top];
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

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("stack Empty");
        }
        int value = stack[top];
        top--;
        return value;
    }

    public void display() {
        if (isEmpty()) {
            throw new RuntimeException("stack Empty");
        }
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }

    /**
     * return priority 數字越大，優先級越高
     */
    public int priority(int ope) {
        if(ope == '*' || ope == '/'){
            return 1;
        } else if(ope == '+' || ope == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    public boolean isOpe(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    public int cal(int num1, int num2, int ope) {
        int res = 0;
        switch (ope) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}

public class Calculator {
    public static void main(String[] args) {
        String expression = "20+20*6-100";
        ArrayStack2 numStack = new ArrayStack2(100);
        ArrayStack2 opeStack = new ArrayStack2(100);
        String keepNum = "";
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int ope = 0;
        int res = 0;
        char ch = ' ';
        while (index < expression.length()) {
            ch = expression.substring(index, index + 1).charAt(0);
            if (opeStack.isOpe(ch)) {
                if (!opeStack.isEmpty()) {
                    if (opeStack.priority(ch) <= opeStack.priority(opeStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        ope = opeStack.pop();
                        res = numStack.cal(num1, num2, ope);
                        numStack.push(res);
                        opeStack.push(ch);
                    } else {
                        opeStack.push(ch);
                    }
                } else {
                    opeStack.push(ch);
                }
            } else {
                keepNum += ch;
                if(index == expression.length()-1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    if(opeStack.isOpe(expression.substring(index+1, index+2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            index++;
        }

        while (!opeStack.isEmpty()) {
            num1 = numStack.pop();
            num2 = numStack.pop();
            ope = opeStack.pop();
            res = numStack.cal(num1, num2, ope);
            numStack.push(res);
        }
        System.out.println(numStack.pop());
    }
}