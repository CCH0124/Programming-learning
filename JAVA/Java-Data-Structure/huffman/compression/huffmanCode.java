package huffman.compression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Node implements Comparable<Node> {
    Byte data;// 存放樹鋸
    int weight; // 權值，出現次數
    Node left;
    Node right;

    /**
     * @param data
     * @param weight
     */
    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        // TODO Auto-generated method stub
        return this.weight - o.weight;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return "Node [data=" + data + ", right=" + right + "]";
    }
}

public class HuffmanCode {
    private static final String ZERO = "0";
    private static final String ONE = "1";
    public static Map<Byte, String> huffmanCodes = new HashMap<>(); // 存放霍夫曼編碼
    static StringBuilder stringBuilder = new StringBuilder(); // 拼接每個葉子結點路徑

    /**
     * 
     * @param node          傳入節點
     * @param code          左 0 右 1
     * @param stringBuilder
     */
    private static void getHuffmanCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder concatNodePath = new StringBuilder(stringBuilder);
        concatNodePath.append(code);

        if (node != null) { // 非葉子節點
            if (node.data == null) {
                getHuffmanCodes(node.left, ZERO, concatNodePath);
                getHuffmanCodes(node.right, ONE, concatNodePath);
            } else {
                huffmanCodes.put(node.data, concatNodePath.toString());
            }
        }
    }

    private static Map<Byte, String> getHuffmanCodes(Node root) {
        if (root == null) {
            return null;
        }
        getHuffmanCodes(root.left, ZERO, stringBuilder);
        getHuffmanCodes(root.right, ONE, stringBuilder);
        return huffmanCodes;
    }

    /**
     * 字符出現次數
     * 
     * @param bytes
     * @return
     */
    private static List<Node> getNode(byte[] bytes) {
        List<Node> nodes = new ArrayList<>();

        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }

        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }

        return nodes;
    }

    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(null, leftNode.weight + rightNode.weight); // 只有 weight
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    /**
     * 
     * @param bytes        初始字符串
     * @param huffmanCodes 霍夫曼編碼
     * @return
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(huffmanCodes.get(b));
        }

        int len;
        if (sb.length() % 8 == 0) {
            len = sb.length() / 8;
        } else {
            len = sb.length() / 8 + 1;
        }

        byte[] huffmanCodesToByte = new byte[len];
        int index = 0;
        for (int i = 0; i < sb.length(); i += 8) { // 8 為 1byte
            String str;
            if (i + 8 > sb.length()) {
                str = sb.substring(i);
            } else {
                str = sb.substring(i, i + 8);
            }
            huffmanCodesToByte[index] = (byte) Integer.parseInt(str, 2);
            index++;
        }

        return huffmanCodesToByte;
    }

    /**
     * 壓縮結果
     * 
     * @param bytes
     * @return
     */
    public static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNode(bytes);
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        Map<Byte, String> huffmanCodes = getHuffmanCodes(huffmanTreeRoot);
        return zip(bytes, huffmanCodes);
    }

    /**
     * 將編碼轉回霍夫曼路徑
     * 
     * @param flag
     * @param b
     * @return
     */
    private static String byteToBitString(boolean flag, byte b) {
        int temp = b;
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        }

        return str;
    }

    public static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }

        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        byte b[] = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;

    }

    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        byte [] huffmanCodesBytes = huffmanZip(str.getBytes());
        System.out.println(Arrays.toString(huffmanCodesBytes));
        byte[] sourceBytes = decode(huffmanCodes, huffmanCodesBytes);
        System.out.println(new String(sourceBytes));
        
    }
}