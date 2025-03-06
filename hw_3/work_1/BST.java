package work_1;

import java.io.*;

interface BST<K extends Comparable<K>, V> {
    // 向集合中插入一个键值对,如果映射中已经存在该键的映射关系，则更新该键对应的值
    void insert(K key, V value);
    // 移除指定键的映射关系，如果映射中存在该键的映射关系，则返回该键对应的值，否则返回null
    V remove(K key);
    // 根据指定的键值，返回对应的值
    V search(K key);
    // 更新指定键的值
    boolean update(K key, V value);
    // 判断集合是否为空
    boolean isEmpty();
    // 清除当前对象中的所有数据
    void clear();
    // 打印出该对象的字符串表示形式
    void showStructure(PrintWriter pw) throws IOException;
    // 打印中序遍历
    void printInorder(PrintWriter pw) throws IOException;
}