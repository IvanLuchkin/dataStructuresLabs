/******************Huffman Tree And Huffman Algorithm Realisation *********************/

import java.util.PriorityQueue;
/**
 * Дерево Хаффмана
 */
public class HuffmanTree implements Comparable<HuffmanTree>{
    int counter;
    int treeDepth = 0;
    double sumDepths;
    double uniqueSymbolsCounter;
    private Node root;

    public HuffmanTree(Node root) {
        this.root = root;
    }

    /**
     * Класс, описывающий объекты узлов.
     */
    private static class Node {

        private Integer frequency;
        private Character character;
        private Node leftChild;
        private Node rightChild;

        public Node(Integer frequency, Character character) {
            this.frequency = frequency;
            this.character = character;
        }

        public Node(HuffmanTree left, HuffmanTree right) {
            frequency = left.root.frequency + right.root.frequency;
            leftChild = left.root;
            rightChild = right.root;
        }

        @Override
        public String toString() {
            return "[id=" + frequency + ", data =" + character + "]";
        }
    }
    /**
     * Построение дерева Хаффмана
     * Алгоритм:
     * 1. Создать объект Node для каждого символа, используемого в сообщении.
     * 2. Создать объект дерева для каждого из этих узлов. Узел становится корнем дерева.
     * 3. Вставить эти деревья в приоритетную очередь. Деревья упорядочиваются по частоте,
     * при этом наименьшая частота обладает наибольшим приоритетом. Таким образом,
     * при извлечении всегда выбирается дерево с наименее часто используемым символом.
     * 4. Извлечь два дерева из приоритетной очереди и сделать их потомками нового узла.
     * Частота нового узла является суммой частот потомков. Поле символа может остаться пустым.
     * 5. Вставить новое дерево из двух узлов обратно в приоритетную очередь.
     * 6. Продолжить выполнение шагов 4 и 5. Деревья постепенно увеличиваются, а их
     * количество постепенно сокращается. Когда в очереди останется всего одно дерево, оно
     * представляет собой дерево Хаффмана. Работа алгоритма при этом завершается.
     */
    public static HuffmanTree buildHuffmanTree(int[] charFrequencies) {
        PriorityQueue<HuffmanTree> trees = new PriorityQueue<>();
        for (int i = 0; i < charFrequencies.length; i++) {
            if (charFrequencies[i] > 0) {
                trees.offer(new HuffmanTree(new Node(charFrequencies[i], (char)i)));
            }
        }
        while (trees.size() > 1) {
            HuffmanTree a = trees.poll();
            HuffmanTree b = trees.poll();
            trees.offer(new HuffmanTree(new Node(a, b)));
        }
        return trees.poll();
    }

    /**
     * Преобразование последовательности битов в текстовую строку.
     * Декодирование каждого символа начинается с корневого узла.
     * Если в потоке обнаружен бит 0, вы переходите налево к следующему узлу,
     * а если 1 - то направо.
     * Например, для кода 010 нужно двигаться налево, направо, потом снова налево.
     * Если существует символ с таким кодом, то вы окажитесь в листовом узле.
     * При достижении листового узла начинается поиск нового символа.
     */
    public String decode(String bytes, HuffmanTree tree) {
        StringBuilder result = new StringBuilder();
        Node temp = tree.root;
        char[] textToDecode = bytes.toCharArray();
        for (int i = 0; textToDecode[i] != '\0' && i < bytes.length() - 1; i++) {
            if (temp.leftChild == null && temp.rightChild == null) {
                result.append(temp.character);
                temp = tree.root;
            }
            if (textToDecode[i] == '0') {
                temp = temp.leftChild;
            }
            if (textToDecode[i] == '1') {
                temp = temp.rightChild;
            }
            if (textToDecode[i] != '0' && textToDecode[i] != '1') {
                return "ERROR: The text is not encoded correctly!\n";
            }
        }
        if (temp.leftChild == null && temp.rightChild == null) {
            result.append(temp.character);
            temp = tree.root;
        }
        return result.toString();
    }

    /**
     * Кодирование сообщения
     */
    public String encoded(String text) {
        String[] codes = codeTable();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            result.append(codes[text.charAt(i)]);
        }
        return result.toString();
    }

    /**
     * Создание кодовой таблицы по данному дереву Хаффмана
     * tree дерево Хаффмана
     * возвращает кодовую таблицу, в которой для каждого символа
     * приводится соответствующий код Хаффмана.
     */
    private String[] codeTable() {
        String[] codeTable = new String[256];
        codeTable(root, new StringBuilder(), codeTable);
        return codeTable;
    }

    /**
     * Постороение кодовой таблицы реализовано посредством вызова метода,
     * который начинается от корня таблицы, а затем рекурсивно вызывает себя
     * для каждого потомка. Через некоторое время алгоритм обойдет все пути
     * ко всем листовым узлам, и кодовая таблица будет построена.
     */
    private void codeTable(Node node, StringBuilder code, String[] codeTable) {
        if (node.character != null) {
            codeTable[(char)node.character] = code.toString();
            return;
        }
        codeTable(node.leftChild, code.append('0'), codeTable);
        code.deleteCharAt(code.length() - 1);
        codeTable(node.rightChild, code.append('1'), codeTable);
        code.deleteCharAt(code.length() - 1);
    }

    public void printCodes() {
        System.out.println("char\t frequency\t binary code");
        printCodes(root, new StringBuilder());
    }

    /**
     * Рекурсивный метод печати кодовой таблицы
     */
    private void printCodes(Node current, StringBuilder code) {
        if (current.character != null) {
            System.out.println(current.character + "\t\t " + current.frequency + "\t\t\t   " + code + " " + code.length());
            if (code.length() > treeDepth) {
                treeDepth = code.length();
            }
            counter += current.frequency;
            sumDepths += code.length();
            uniqueSymbolsCounter += 1;
        } else {
            printCodes(current.leftChild, code.append('0'));
            code.deleteCharAt(code.length() - 1);
            printCodes(current.rightChild, code.append('1'));
            code.deleteCharAt(code.length() - 1);
        }
    }

    @Override
    public int compareTo(HuffmanTree tree) {
        return root.frequency - tree.root.frequency;
    }

}