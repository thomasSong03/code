package work_2;
import java.io.*;

class BSTImpl<K extends Comparable<K>, V> implements BST<K, V> {
    // 定义一个泛型类TreeNode，用于表示二叉树的节点
    private static class TreeNode<K, V> {
        // 定义节点的key属性，类型为K
        K key;
        // 定义节点的value属性，类型为V
        V value;
        // 定义节点的左子节点，类型为TreeNode
        TreeNode<K, V> left;
        // 定义节点的右子节点，类型为TreeNode
        TreeNode<K, V> right;

        // 构造函数，用于创建一个TreeNode对象
        TreeNode(K key, V value) {
            // 将传入的key和value赋值给当前对象的key和value属性
            this.key = key;
            this.value = value;
        }
    }

    // 根节点
    private TreeNode<K, V> root;
    private int size;

    // 向二叉搜索树中插入一个键值对
    @Override
    public void insert(K key, V value) {
        root = insert(root, key, value);
    }

    private TreeNode<K, V> insert(TreeNode<K, V> node, K key, V value) {
        if (node == null) {
            size++;
            return new TreeNode<>(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = insert(node.left, key, value);
        } else if (cmp > 0) {
            node.right = insert(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    @Override
    public V remove(K key) {
        TreeNode<K, V>[] result = new TreeNode[1];
        root = remove(root, key, result);
        if (result[0] == null) return null;
        size--;
        return result[0].value;
    }

    private TreeNode<K, V> remove(TreeNode<K, V> node, K key, TreeNode<K, V>[] result) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key, result);
        } else if (cmp > 0) {
            node.right = remove(node.right, key, result);
        } else {
            result[0] = node;
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            TreeNode<K, V> successor = findMin(node.right);
            node.key = successor.key;
            node.value = successor.value;
            node.right = remove(node.right, successor.key, new TreeNode[1]);
        }
        return node;
    }

    private TreeNode<K, V> findMin(TreeNode<K, V> node) {
        while (node.left != null) node = node.left;
        return node;
    }

    @Override
    public V search(K key) {
        TreeNode<K, V> node = search(root, key);
        return node == null ? null : node.value;
    }


    private TreeNode<K, V> search(TreeNode<K, V> node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return search(node.left, key);
        else if (cmp > 0) return search(node.right, key);
        else return node;
    }

    @Override
    public boolean update(K key, V value) {
        TreeNode<K, V> node = search(root, key);
        if (node == null) return false;
        node.value = value;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public void showStructure(PrintWriter pw) throws IOException {
        pw.println("-----------------------------");
        pw.println("There are " + size + " nodes in this BST.");
        pw.println("The height of this BST is " + height(root) + ".");
        pw.println("-----------------------------");
    }

    private int height(TreeNode<K, V> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    @Override
    public void printInorder(PrintWriter pw) throws IOException {
        inorder(root, pw);
    }

    private void inorder(TreeNode<K, V> node, PrintWriter pw) {
        if (node == null) return;
        inorder(node.left, pw);
        pw.println("[ " + node.key + " --- <" + node.value + "> ]");
        inorder(node.right, pw);
    }
}