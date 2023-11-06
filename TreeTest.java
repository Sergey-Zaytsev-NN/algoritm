public class TreeTest {
    public static void main(String[] args) {
        
	// Создаю тестовое дерево из целых чисел 
        Tree<Integer> treeInt = new Tree<>();
        treeInt.add(15);
        treeInt.add(12);
        treeInt.add(14);
        treeInt.add(13);
        treeInt.add(17);
        treeInt.add(16);
        treeInt.add(20);
        treeInt.add(22);

        // создаю тестовое дерево состоящее из строк
        Tree<String> treeStr = new Tree<>();
        treeStr.add("папа");
        treeStr.add("мама");
        treeStr.add("сын");
        treeStr.add("дочь");
        treeStr.add("бабушка");
        treeStr.add("дедушка");

	// проверяем показ первого элемента
        System.out.println("Первый элемент в цифровом дереве: " + treeInt.findFirst());
        System.out.println("Первый элемент в строковом дереве: " + treeStr.findFirst());

	//  проверяем обход дерева в ширину
        System.out.println("Обходим цифровое дерево в ширину: " + treeInt.bfs());
        System.out.println("Обходим строковое дерево в ширину: " + treeStr.bfs());

        // проверяем обход дерева в глубину
        System.out.println("Обходим цифровое дерево в глубину: " + treeInt.dfs());
        System.out.println("Обходим строковое дерево в глубину: " + treeStr.dfs());

        // проверяем удаление + по итогу обходим ещё раз чтобы увидить результат
	System.out.println("Удаляем в цифровом дереве - 13");
        treeInt.remove(13);
        System.out.println("Проверяем результат удаления: " + treeInt.dfs());
	System.out.println("Удаляем в строковом дереве - дочь");
        treeStr.remove("дочь");
        System.out.println("Проверяем результат удаления: " + treeStr.dfs());
    }
}