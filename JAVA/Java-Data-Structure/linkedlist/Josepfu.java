package linkedlist;

class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    /**
     * @return the no
     */
    public int getNo() {
        return no;
    }

    /**
     * @param no the no to set
     */
    public void setNo(int no) {
        this.no = no;
    }

    /**
     * @return the next
     */
    public Boy getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(Boy next) {
        this.next = next;
    }
}

class CircleSingleLinkedList {
    /**
     * Create first node
     */
    private Boy first = null;

    public void addBoy(int num) {
        if (num < 2) {
            throw new RuntimeException("number error");
        }
        Boy cur = null;
        for (int i = 1; i <= num; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first); // create circle
                cur = first; // cur point to first
            } else {
                cur.setNext(boy);
                boy.setNext(first);
                cur = boy;
            }
        }
    }

    public void display() {
        if (first == null) {
            throw new RuntimeException("is empty");
        }
        Boy cur = first;
        while (true) {
            System.out.println(cur.getNo());
            if (cur.getNext() == first) {
                break;
            }
            cur = cur.getNext();
        }
    }

    /**
     * 
     * @param startNo  第幾個開始數
     * @param countNum 間隔
     * @param nums     表示最初有多少小孩在圈中
     */
    public void count(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            throw new RuntimeException("args error");
        }

        Boy helper = first;
        /**
         * 指向最後節點
         */
        while (helper.getNext() != first) {
            helper = helper.getNext();
        }
        /**
         * 報數前，讓 first 和 helper 移動 k-1 次
         */
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        /**
         * 報數時，first 和 helper 同時移動 m-1 次，接著出圈
         */
        while (helper != first) {
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.println("out : " + first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("Exists : " + first.getNo());
    }
}

public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.display();

        circleSingleLinkedList.count(1, 2, 5);// 2 4 1 5 3
    }
}