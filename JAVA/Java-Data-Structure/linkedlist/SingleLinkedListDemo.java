package linkedlist;

import java.util.Stack;

class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "{" + " no='" + no + "'" + ", name='" + name + "'" + ", nickname='" + nickname + "'" + "}";
    }

}

class SingleLinkedList {
    private HeroNode head;

    public SingleLinkedList() {
        head = null;
    }

    public void add(HeroNode heroNode) {
        heroNode.next = head;
        head = heroNode;

    }

    public void display() {
        if (head == null) {
            throw new RuntimeException("is Empty");
        }
        HeroNode cur = head;
        while (cur != null) {
            System.out.println(cur);
            cur = cur.next;
        }
    }
    /**
     * sort list
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode) {
        if (head == null) {
            add(heroNode);
            return;
        }
        HeroNode temp = head;
        while (temp != null) {
            if (temp.no > heroNode.no) {
                heroNode.next = temp;
                temp = heroNode;
                head = temp;
                break;
            } else if (temp.no < heroNode.no) {
                if (temp.next == null) {
                    temp.next = heroNode;
                    break;
                } 
                if (temp.next.no > heroNode.no) {
                    heroNode.next = temp.next;
                    temp.next = heroNode;
                    break;
                }
            } else {
                throw new RuntimeException("is exist");
            }
            temp = temp.next;

        }
    }
    /**
     * delete first node
     */
    public void deleteFirstNode() {
		head = head.next;
    }

    /**
     * remove node
     */
    public void remove(int no) {
        HeroNode temp = head;
        if (temp.no == no) {
            deleteFirstNode();
        }
        while(temp.next != null && temp.next.no != no) {
            temp = temp.next;
        }
        if(temp.next != null)
			temp.next = temp.next.next;
    }
    /**
     * get linklist length
     * @return
     */
    public int size(){
        int length = 0;
        HeroNode cur = head;
        if (head == null) return length;
        while(cur != null){
            length++;
            cur = cur.next;
        }
        return length;
    }
    /**
     * Find the K-th node from the bottom of the linked list.
     */
    public HeroNode findLastIndexNode(int index){
        int size = size();
        if(head == null || index <= 0 || index > size) return null;
        HeroNode cur = head;
        int count = 0;
        while(count < size-index) {
            cur = cur.next;
            count++;
        }

        return cur;
    }
    /**
     * reverse
     */
    public void reverseLinkList() {
        if (head == null || head.next == null) return;
        HeroNode cur = head;
        HeroNode next = null;
        HeroNode reverse = null;
        while(cur != null) {
            next = cur.next; // 保存當前節點的下個節點
            cur.next = reverse; // 將 cur 的下個節點指向新的鏈表最前端
            reverse = cur; // 將 cur 連接到新的鏈表上
            cur = next; // cur 後移
        }
        head = reverse;
    }
    /**
     * reverse print
     */
    public void reversePrint(){
        if (head == null || head.next == null) return;
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head;
        while(cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
        
    }

}

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "itachi", "i");
        HeroNode hero2 = new HeroNode(2, "asdasd", "asd");
        HeroNode hero3 = new HeroNode(3, "vvvvvv", "v");
        HeroNode hero4 = new HeroNode(4, "qqqqq", "q");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        // singleLinkedList.add(hero1);
        // singleLinkedList.add(hero2);
        // singleLinkedList.add(hero3);
        // singleLinkedList.add(hero4);
        // singleLinkedList.display();

        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.display();
        System.out.println("-------------------------------------------------------------------------------------");
        singleLinkedList.remove(2);
        System.out.println("-------------------------------------------------------------------------------------");
        singleLinkedList.display();
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println(singleLinkedList.size());
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println(singleLinkedList.findLastIndexNode(2));
        System.out.println("-------------------------------------------------------------------------------------");
        singleLinkedList.reverseLinkList();
        singleLinkedList.display();
        System.out.println("-------------------------------------------------------------------------------------");
        singleLinkedList.reversePrint();
    }
}
