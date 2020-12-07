package tree;

import java.util.Stack;

class HeroNode2 {
    private int no;
    private String name;
    private HeroNode2 left;
    private HeroNode2 right;

    public HeroNode2(int no, String name) {
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

    public HeroNode2 getLeft() {
        return left;
    }

    public void setLeft(HeroNode2 left) {
        this.left = left;
    }

    public HeroNode2 getRight() {
        return right;
    }

    public void setRight(HeroNode2 right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode2 [name=" + name + ", no=" + no + "]";
    }
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

   
}

class BinaryTreeStackDemo {
    private HeroNode2 root;

    public void setRoot(HeroNode2 root) {
        this.root = root;
    }

    public void preOrder() {
        Stack<HeroNode2> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            HeroNode2 node = stack.pop();
            System.out.printf("%d ", node.getNo());
            if (node.getRight() != null) {
                stack.push(node.getRight());
            }
            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
        }
        System.out.println();
    }

    public void infixOrder() {
        Stack<HeroNode2> stack = new Stack<>();

        HeroNode2 currentNode = root;

        while (!stack.empty() || currentNode != null) {

            if (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.getLeft();
            } else {
                HeroNode2 n = stack.pop();
                System.out.printf("%d ", n.getNo());
                currentNode = n.getRight();
            }
        }
        System.out.println();
    }

    public void postOrder() {
        Stack<HeroNode2> stack = new Stack<>();

        HeroNode2 currentNode = root;

        while (true) {
            if (currentNode != null) {
                if (currentNode.getRight() != null)
                    stack.push(currentNode.getRight());
                stack.push(currentNode);
                currentNode = currentNode.getLeft();
                continue;
            }

            if (stack.isEmpty())
                return;
            currentNode = stack.pop();

            if (currentNode.getRight() != null && !stack.isEmpty() && currentNode.getRight() == stack.peek()) {
                stack.pop();
                stack.push(currentNode);
                currentNode = currentNode.getRight();
            } else {
                System.out.print(currentNode.getNo() + " ");
                currentNode = null;
            }
        }
    }

}

public class BinaryTreeStack {
    public static void main(String[] args) {
        BinaryTreeStackDemo binaryTree = new BinaryTreeStackDemo();
        HeroNode2 root = new HeroNode2(1, "Itachi");
        HeroNode2 node2 = new HeroNode2(2, "Naruto");
        HeroNode2 node3 = new HeroNode2(3, "Kevin");
        HeroNode2 node4 = new HeroNode2(4, "Tom");
        HeroNode2 node5 = new HeroNode2(5, "Jack");
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);
        System.out.println("前序");
        binaryTree.preOrder();
        System.out.println("中序");
        binaryTree.infixOrder();
        System.out.println("後序");
        binaryTree.postOrder();

    }
}