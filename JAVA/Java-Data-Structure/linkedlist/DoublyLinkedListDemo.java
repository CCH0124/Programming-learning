package linkedlist;
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "{" + " no='" + no + "'" + ", name='" + name + "'" + ", nickname='" + nickname + "}";
    }

}

class DoublyLinkedList {
    private HeroNode2 head;

    public DoublyLinkedList() {
        head = null;
    }

    public void display() {
        if (head == null) {
            throw new RuntimeException("is Empty");
        }
        HeroNode2 cur = head;
        while (cur != null) {
            System.out.println(cur);
            cur = cur.next;
        }
    }
    public void add(HeroNode2 HeroNode) {
        if(head == null) {
            head = HeroNode;
            return;
        }
        HeroNode2 temp = head;
        while(temp.next != null) {
            temp = temp.next;
        }
        temp.next = HeroNode;
        HeroNode.pre = temp;
    }

    public void update(HeroNode2 HeroNode) {
        if (head == null) {
            throw new RuntimeException("is Empty");
        }
        HeroNode2 cur = head;
        while(cur.no != HeroNode.no) {
            cur = cur.next;
        }
        cur.name = HeroNode.name;
        cur.nickname = HeroNode.nickname;
    }

     /**
     * remove node
     */
    public void remove(int no) {
        if (head == null) {
            throw new RuntimeException("is Empty");
        }
        HeroNode2 cur = head;
        if(cur.no == no) {
            deleteFirstNode();
            return;
        }
        while(cur.no != no) {
            cur = cur.next;
        }
        cur.pre.next = cur.next;
        if(cur.next != null) {
            cur.next.pre = cur.next;
        }
    }

    /**
     * delete first node
     */
    private void deleteFirstNode() {
        head = head.next;
        head.pre = null;
    }

}

public class DoublyLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 hero1 = new HeroNode2(1, "itachi", "i");
        HeroNode2 hero2 = new HeroNode2(2, "asdasd", "asd");
        HeroNode2 hero3 = new HeroNode2(3, "vvvvvv", "v");
        HeroNode2 hero4 = new HeroNode2(4, "qqqqq", "q");

        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        doublyLinkedList.add(hero1);
        doublyLinkedList.add(hero2);
        doublyLinkedList.add(hero3);
        doublyLinkedList.add(hero4);
        doublyLinkedList.display();
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        HeroNode2 hero5 = new HeroNode2(4, "qq", "qqqqqq");
        HeroNode2 hero6 = new HeroNode2(2, "aaaaa", "aaaaa");
        HeroNode2 hero7 = new HeroNode2(3, "vvvvvvv", "vvvvvvv");
        doublyLinkedList.update(hero5);
        doublyLinkedList.update(hero6);
        doublyLinkedList.update(hero7);
        doublyLinkedList.display();
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        doublyLinkedList.remove(2);
        doublyLinkedList.display();
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        doublyLinkedList.remove(4);
        doublyLinkedList.display();
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        doublyLinkedList.remove(1);
        doublyLinkedList.display();

    }
}
