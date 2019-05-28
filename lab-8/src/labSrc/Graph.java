package labSrc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Представление сети списками смежности.
 * Нагрузка на дуги - целые числа ("пропускная способность дуги").
 */
public class Graph {
    /**
     * Представление дуги графа
     */
    ArrayList<Arc> arcSet;

    public static class Arc {
        int to;			// Номер вершины, в которую ведет дуга
        int capacity;	// Пропускная способность дуги
        int flow;		// Поток по дуге
        int from;
        boolean visited;

        public Arc(int to, int capacity, int flow) {
            this.to = to;
            this.capacity = capacity;
            this.flow = flow;
            visited = false;
        }

        public Arc(int to, int capacity) {
            this(to, capacity, 0);
            visited = false;
        }

        public Arc(Arc arc) {
            this(arc.to, arc.capacity, arc.flow);
            visited = false;
        }

        public int to() { return to; }

        public int capacity() { return capacity; }

        public int flow() { return flow; }

        public void setFlow(int flow) { this.flow = flow; }

        public void changeFlow(int delta) { flow += delta; }

        public String toString() {
            return "-> " + flow + "/" + capacity + " -> " + (to+1);
        }
    };

    private final List<Arc>[] lGraph;	// Списки смежности
    private final int nVertex;			// Число вершин

    /**
     * Конструктор пустого графа с заданным числом вершин
     * @param nVert Число вершин
     */
    @SuppressWarnings("unchecked")
    public Graph(int nVert) {
        lGraph = new List[nVert];
        for (int i = 0; i < nVert; ++i) {
            lGraph[i] = new ArrayList<Arc>();
        }
        nVertex = nVert;
        arcSet = new ArrayList<>();
    }

    /**
     * Число вершин графа
     * @return
     */
    public int getCount() { return nVertex; }

    /**
     * Добавление дуги в граф. Предполагается, что ранее такой дуги в графе не было.
     * @param from	Начало дуги (номер вершины)
     * @param to	Конец дуги (номер вершины)
     * @param capacity	Нагрузка на дугу
     */
    public void addArc(int from, int to, int capacity) {
        assert to < nVertex && to >= 0;
        assert capacity >= 0;
        Arc newArc = new Arc(to, capacity);
        newArc.from = from;
        arcSet.add(newArc);
        addArc(from, newArc);
    }

    /**
     * Добавление дуги в граф. Предполагается, что ранее такой дуги в графе не было.
     * @param from	Начало дуги (номер вершины)
     * @param arc	Добавляемая дуга
     */
    public void addArc(int from, Arc arc) {
        assert from < nVertex && from >= 0;

        lGraph[from].add(arc);
    }

    /**
     * Удаление дуги из графа.
     * @param from	Начало дуги
     * @param to	Конец дуги
     */
    public void removeArc(int from, int to) {
        assert from < nVertex && from >= 0;

        for (Iterator<Arc> iArc = arcs(from); iArc.hasNext(); ) {
            Arc arc = iArc.next();
            if (arc.to() == to) {
                iArc.remove();
                break;
            }
        }
    }

    /**
     * Итератор дуг, ведущих из заданной вершины
     * @param u	Исходная вершина
     * @return
     */
    public Iterator<Arc> arcs(int u) {
        return lGraph[u].iterator();
    }

    /**
     * Ищет в графе дугу с заданными началом и концом.
     * @param from	Начало дуги (номер вершины)
     * @param to	Конец дуги (номер вершины)
     * @return		Дуга, если она есть, или null, если дуга отсутствует.
     */
    public Arc getArc(int from, int to) {
        for (Iterator<Arc> iArc = arcs(from); iArc.hasNext(); ) {
            Arc arc = iArc.next();
            if (arc.to == to) return arc;
        }
        return null;
    }

    /**
     * Отладочная функция печати всей сети
     */
    public void printNet() {
        for (int i = 0; i < nVertex; ++i) {
            for (Iterator<Arc> iArc = arcs(i); iArc.hasNext(); ) {
                System.out.println((i+1) + " " + iArc.next());
            }
        }
    }
}