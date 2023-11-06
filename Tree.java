import java.util.*;

public class Tree <V extends Comparable<? super V>> {

    private static class Node<V extends Comparable<? super V>> {
        V value;
        Node<V> left;
        Node<V> right;

        public Node(V value) {
            this.value = value;
        }
    }

    private Node<V> root;

    public void add(V value) {
        if (root == null) {
            root = new Node<>(value);
            return;
        }
        add(root, value);
    }

    private void add(Node<V> current, V value) {
        if (value.compareTo(current.value) < 0) {
            if (current.left == null) {
                current.left = new Node<>(value);
            } else {
                add(current.left, value);
            }
        } else if (value.compareTo(current.value) > 0) {
            if (current.right == null) {
                current.right = new Node<>(value);
            } else {
                add(current.right, value);
            }
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public boolean contains(V value) {
        return findNode(root, value) != null;
    }

    private Node<V> findNode(Node<V> current, V value) {
        if (current == null) {
            return null;
        }

        if (current.value == value) {
            return current;
        } else if (current.value.compareTo(value) > 0) {
            return findNode(current.left, value);
        } else { // current.value < value
            return findNode(current.right, value);
        }
    }

    public void remove(V value) {
        root = removeNode(root, value);
    }

    // Метод, который удаляет ноду и возвращает ту ноду, которая будет вместо удаленной
    private Node<V> removeNode(Node<V> current, V value) {
        if (current == null) {
            return null;
        }

        if (value.compareTo(current.value) < 0) {
            current.left = removeNode(current.left, value);
            return current;
        } else if (value.compareTo(current.value) > 0) {
            current.right = removeNode(current.right, value);
            return current;
        }

        // Нужно удалить current. Возможны 3 случая.
        // 1. У удаляемого узла нет дочерних узлов
        if (current.left == null && current.right == null) {
            return null;
        }

        // 2. У удаляемого узла ровно 1 дочерний узел
        if (current.left != null && current.right == null) {
            return current.left;
        } else if (current.left == null) {
            return current.right;
        }

        // 3. У удаляемого узла 2 дочерних узла
        // Ищем минимальный элемент в правом поддереве

        //          12
        //      8
        //   2     9
        //           10
        //
        // current = 6
        Node<V> smallestNodeOnTheRight = findFirst(current.right); // 8
        V smallestValueOnTheRight = smallestNodeOnTheRight.value; // 8
        current.value = smallestValueOnTheRight;
        current.right = removeNode(current.right, smallestValueOnTheRight);
        return current;
    }

    public V findFirst() {
        if (root == null) {
            throw new NoSuchElementException();
        }
        return findFirst(root).value;
    }

    private Node<V> findFirst(Node<V> current) {
        if (current.left != null) {
            return findFirst(current.left);
        }
        return current;
    }

    // Поиск в глубину DFS Depth-first search
    // Поиск в ширину  BFS Breath-first search

    public List<V> dfs() {
        if (root == null) {
            return List.of();
        }

        List<V> result = new ArrayList<>();
        dfs(root, result);
        return List.copyOf(result);
    }

    private void dfs(Node<V> current, List<V> result) {
        // in-order
        if (current.left != null) {
            dfs(current.left, result);
        }
        result.add(current.value);
        if (current.right != null) {
            dfs(current.right, result);
        }
    }

    public List<V> bfs() {
        if (root == null) {
            return List.of();
        }

        List<V> result = new ArrayList<>();
        Queue<Node<V>> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<V> next = queue.poll();
            result.add(next.value);
            if (next.left != null) {
                queue.add(next.left);
            }
            if (next.right != null) {
                queue.add(next.right);
            }
        }
        return result;
    }
}
