package labSrc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import labSrc.Graph.Arc;

import static java.lang.Math.min;


/**
 * Решение задачи нахождения максимального потока в сети
 * методом Фалкерсона - Форда (реализация алгоритма Эдмондса - Карпа).
 *
 * Класс представляет собой описание сети, нагруженной целыми значениями.
 * Представление сети - в виде списков смежности.
 *
 */
public class FordFulkerson {
    /**
     * Фиктивная дуга, несущая обратный поток.
     */
    ArrayList<Integer> usedCaps;

    private static class ExtArc extends Arc {
        public ExtArc(int to) {
            super(to, 0);
        }

    }

    private Net net;
    private int nVert;
    boolean[] visited;

    public FordFulkerson(Net g) {
        net = g;
        nVert = g.getCount();
        visited = new boolean[nVert];
        for(boolean tmp : visited) tmp = false;
        usedCaps = new ArrayList<>();
    }

    /**
     * Реализация алгоритма Эдмондса - Карпа (по методу Форда - Фалкерсона).
     * Последовательно увеливаем поток на величину остаточного пути.
     * Предполагается, что начальный поток равен нулю. Тогда после
     * окончания работы алгоритма дуги графа будут нести максимальный поток.
     * @return		Величина максимального потока в сети.
     */
    public int fordFulkerson() {
        int source = net.getSource();
        int sink = net.getSink();

        // Добавляем обратные дуги
        for (int i = 0; i < nVert; ++i) {
            for (Iterator<Arc> iArc = net.arcs(i); iArc.hasNext(); ) {
                Arc arc = iArc.next();
                arc.setFlow(0);
                if (net.getArc(arc.to(), i) == null) {
                    net.addArc(arc.to(), new ExtArc(i));
                }
            }
        }

        // Цикл последовательного увеличения потока.
        int[] path;
        System.out.println(Arrays.toString(getPath(source,sink)));

        while((path = getPath(source, sink)) != null) {
            // Найден путь в остаточной сети.
            // Вычисляем минимальный остаточный поток на этом пути
            int min = Integer.MAX_VALUE;
            for (int curr = sink;  curr != source; ) {
                Arc arcFwd = net.getArc(path[curr], curr);
                if (arcFwd.capacity() - arcFwd.flow() < min) {
                    min = arcFwd.capacity() - arcFwd.flow();
                }
                curr = path[curr];
            }
            // Корректируем поток вдоль прямых и обратных дуг на этом пути.
            for (int curr = sink;  curr != source; ) {
                Arc arcFwd = net.getArc(path[curr], curr);
                Arc arcBack = net.getArc(curr, path[curr]);
                arcFwd.changeFlow(min);
                arcBack.changeFlow(-min);
                curr = path[curr];
            }
            //System.out.println(Arrays.toString(path));
            net.printNet();
            System.out.println("-----------------------");
        }
        // Величина потока равна сумме потоков на исходящих из истока дугах.
        int s = 0;
        for (Iterator<Arc> iArc = net.arcs(source); iArc.hasNext(); ) {
            s += iArc.next().flow();
        }

        // Убираем обратные дуги
        for (int i = 0; i < nVert; ++i) {
            for (Iterator<Arc> iArc = net.arcs(i); iArc.hasNext(); ) {
                Arc arc = iArc.next();
                if (arc instanceof ExtArc) {
                    iArc.remove();
                }
            }
        }

        return s;
    }

    int findBackArc(Arc arc) {
        for (int i = 0; i < net.arcSet.size(); i++) {
            if (net.arcSet.get(i).from == arc.to() && net.arcSet.get(i).to() == arc.from) {
                return i;
            }
        }
        return -1;
    }

    int findMinCap(int source) {
        int minCap = Integer.MAX_VALUE;
        ArrayList<Integer> usedCaps = new ArrayList<>();
        for (Iterator<Arc> iArc = net.arcs(source); iArc.hasNext();) {
            Arc tempArc = iArc.next();
            if (tempArc.capacity < minCap) {
                if (usedCaps.contains(tempArc.capacity)) continue;
                usedCaps.add(tempArc.capacity);
                minCap = tempArc.capacity;
            }
        }
        return minCap;
    }

    Arc findMaxCapNearArc(int source) {
        int maxNearCap = findMinCap(source);

        System.out.println("Min Cap " + maxNearCap);
        Arc maxArc = new Arc(source, source, 0);
        for (Iterator<Arc> iArc = net.arcs(source); iArc.hasNext(); ) {
            Arc tempArc = iArc.next();
            System.out.println(visited[tempArc.to()] + " - " + tempArc.to());
            if (tempArc.capacity > maxNearCap && !visited[tempArc.to()]) {
                if (usedCaps.contains(tempArc.capacity)) continue;
                usedCaps.add(tempArc.capacity);
                maxArc = tempArc;
                continue;
            }
        }

        return maxArc;
    }

    int fulkerson(int source, int startFlow) {
        visited[source] = true;
        int result = 0;
        if (source == net.getSink()) return startFlow;
        System.out.println(source + " " + startFlow);
        for (Iterator<Arc> iArc = net.arcs(source); iArc.hasNext(); ) {
            Arc tempArc = findMaxCapNearArc(source);

            tempArc.visited = true;
            visited[source] = true;

            System.out.println("Capacity " + tempArc.capacity);
            if (!visited[tempArc.to()]) {
                if (tempArc.capacity < startFlow || startFlow == 0) {
                    tempArc.flow = tempArc.capacity();
                } else tempArc.flow = startFlow;
                result = tempArc.flow();
                return fulkerson(tempArc.to(), tempArc.flow);
            }
        }
        return result;
    }

    int fuckerson(int source, int startFlow) {
        if (source == net.getSink()) return startFlow;
        visited[source] = true;
        for (Iterator<Arc> iArc = net.arcs(source); iArc.hasNext(); ) {
            Arc tempArc = iArc.next();
            if (!visited[tempArc.to()] && tempArc.flow() < tempArc.capacity()) {
                int delta = fuckerson(tempArc.to(), min(startFlow, tempArc.capacity() - tempArc.flow()));
                if (delta > 0) {
                    tempArc.flow += delta;
                    net.arcSet.get(findBackArc(tempArc)).flow -= delta;
                    return delta;
                }
            }
        }
        return 0;
    }

    /**
     * Поиск пути с ненулевым дополнительным потоком в сети.
     * Поиск ведется "в ширину", при этом вершины, уже попавшие
     * в дерево обхода, пропускаются.
     * @param from - исток
     * @param to - сток
     * @return путь, если он существует, null в противном случае
     */
    private int[] getPath(int from, int to) {
        // Дерево поиска в ширину
        int[] tree = new int[nVert];
        Arrays.fill(tree, -1);
        // Очередь вершин
        int[] queue = new int[nVert];
        int qHead = 0;	// Указатель на первый элемент в очереди
        int qCount = 1;	// Количество элементов очереди

        queue[0] = from;
        while(qCount > 0) {
            // Выбираем вершину из очереди
            int curr = queue[qHead++];
            qCount--;
            // Анализируем дуги, ведущие из этой вершины
            for (Iterator<Arc> iArc = net.arcs(curr); iArc.hasNext(); ) {
                Arc arc = iArc.next();
                int end = arc.to;
                if (tree[end] == -1 && arc.capacity() - arc.flow() > 0) {
                    // Дуга с ненулевой остаточной пропускной способностью,
                    // ведущая в еще не пройденную вершину.
                    tree[end] = curr;
                    if (end == to) {
                        // Путь найден!
                        return tree;
                    }
                    queue[qHead + (qCount++)] = end;
                }
            }
        }
        // Все пути исследованы, ничего не найдено.
        return null;
    }
}