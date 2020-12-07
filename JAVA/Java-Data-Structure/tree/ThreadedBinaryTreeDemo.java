package tree;

class HeroNode3 {
    private int no;
    private String name;
    private HeroNode3 left;
    private HeroNode3 right;
    private int leftType; // 0 為左子樹，1 前驅節點
    private int rightType;// 0 為右子樹，1 後繼節點

    public HeroNode3(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode3 getLeft() {
        return left;
    }

    public void setLeft(HeroNode3 left) {
        this.left = left;
    }

    public HeroNode3 getRight() {
        return right;
    }

    public void setRight(HeroNode3 right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode3 [name=" + name + ", no=" + no + "]";
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }
}

class ThreadBinaryTree {
    private HeroNode3 root;
    private HeroNode3 pre = null; // 保留前一個節點

    public void threadNodes() {
        this.threadNodes(root);
    }

    public void threadNodes(HeroNode3 node) {
        if (node == null) {
            return;
        }
        threadNodes(node.getLeft());
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && node.getRight() == null) {
            node.setRight(node);
            node.setRightType(1);
        }
        pre = node;
        threadNodes(node.getRight());
    }

    public void setRoot(HeroNode3 root) {
        this.root = root;
    }
}

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode3 root = new HeroNode3(1, "tom");
        HeroNode3 heroNode2 = new HeroNode3(3, "jack");
        HeroNode3 heroNode3 = new HeroNode3(6, "itachi");
        HeroNode3 heroNode4 = new HeroNode3(8, "naruto");
        HeroNode3 heroNode5 = new HeroNode3(10, "madara");
        HeroNode3 heroNode6 = new HeroNode3(14, "kevin");

        root.setLeft(heroNode2);
        root.setRight(heroNode3);
        heroNode2.setLeft(heroNode4);
        heroNode2.setRight(heroNode5);
        heroNode3.setLeft(heroNode6);

        ThreadBinaryTree threadBinaryTree = new ThreadBinaryTree();
        threadBinaryTree.setRoot(root);
        threadBinaryTree.threadNodes();

        HeroNode3 leftNode = heroNode5.getLeft();
        System.out.println(leftNode);
        // System.out.println(leftNode.getRight());
    }
    // 影片 104 ~ 106
}