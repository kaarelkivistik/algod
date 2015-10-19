package dataStructures;

import javafx.collections.transformation.SortedList;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Comparison {

    public static PriorityQueue<Integer> ascPriorityQueue(int n) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(n);

        for(int i = 0; i < n; i++)
            queue.add(i);

        return queue;
    }

    public static PriorityQueue<Integer> randomPriorityQueue(int n) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(n);
        Random random = new Random();

        for(int i = 0; i < n; i++)
            queue.add(random.nextInt(n));

        return queue;
    }
    
    public static TreeSet<Integer> ascTree(int n) {
        TreeSet<Integer> tree = new TreeSet<>();

        for(int i = 0; i < n; i++)
            tree.add(i);

        return tree;
    }

    public static TreeSet<Integer> randomTree(int n) {
        TreeSet<Integer> tree = new TreeSet<>();
        Random random = new Random();

        for(int i = 0; i < n; i++)
            tree.add(random.nextInt(n));

        return tree;
    }
    
    public static Hashtable<Integer,Integer> ascHashTable(int n) {
        Hashtable<Integer,Integer> hashtable = new Hashtable<>(n);

        for(int i = 0; i < n; i++)
            hashtable.put(i, i);

        return hashtable;
    }

    public static Hashtable<Integer,Integer> randomHashTable(int n) {
        Hashtable<Integer,Integer> hashtable = new Hashtable<>(n);
        Random random = new Random();

        for(int i = 0; i < n; i++) {
            int value = random.nextInt();

            hashtable.put(value, value);
        }

        return hashtable;
    }

    public static ArrayList<SimpleEntry<String, Consumer<Integer>>> getListOfMethods() {
        ArrayList<SimpleEntry<String, Consumer<Integer>>> methods = new ArrayList<>();

        methods.add(new SimpleEntry<>("ascPriorityQueue", Comparison::ascPriorityQueue));
        methods.add(new SimpleEntry<>("randomPriorityQueue", Comparison::randomPriorityQueue));
        methods.add(new SimpleEntry<>("ascTree", Comparison::ascTree));
        methods.add(new SimpleEntry<>("randomTree", Comparison::randomTree));
        methods.add(new SimpleEntry<>("ascHashTable", Comparison::ascHashTable));
        methods.add(new SimpleEntry<>("randomHashTable", Comparison::randomHashTable));

        return methods;
    }

    public static List<String> dataStructureComparison(int n) {
        ArrayList<SimpleEntry<String, Consumer<Integer>>> methods = getListOfMethods();
        ArrayList<SimpleEntry<Long, String>> results = new ArrayList<>();

        long startTime;

        for(SimpleEntry<String, Consumer<Integer>> entry : methods) {
            startTime = System.nanoTime();

            entry.getValue().accept(n);

            long duration = System.nanoTime() - startTime;

            results.add(new SimpleEntry<>(duration, entry.getKey()));

            System.out.println("duration: " + duration + " " + entry.getKey());
        }

        Collections.sort(results, (o1, o2) -> o1.getKey().compareTo(o2.getKey()));

        return results.stream().map(SimpleEntry::getValue).collect(Collectors.toList());
    }
    
    public static List<Node> dataStructureComparisonTimesK(int n, int k) {
        ArrayList<SimpleEntry<String, Consumer<Integer>>> methods = getListOfMethods();
        ArrayList<SimpleEntry<Long, String>> firstResults = new ArrayList<>();
        ArrayList<SimpleEntry<Long, String>> secondResults = new ArrayList<>();
        ArrayList<Node> finalResults = new ArrayList<>();

        long startTime;

        for(SimpleEntry<String, Consumer<Integer>> entry : methods) {
            startTime = System.nanoTime();

            entry.getValue().accept(n);

            long duration = System.nanoTime() - startTime;

            firstResults.add(new SimpleEntry<>(duration, entry.getKey()));

            System.out.println("duration: " + duration + " " + entry.getKey());
        }

        for(SimpleEntry<String, Consumer<Integer>> entry : methods) {
            startTime = System.nanoTime();

            entry.getValue().accept(n * k);

            long duration = System.nanoTime() - startTime;

            secondResults.add(new SimpleEntry<>(duration, entry.getKey()));

            System.out.println("duration: " + duration + " " + entry.getKey());
        }

        for(int i = 0; i < methods.size(); i++)
            finalResults.add(new Node(methods.get(i).getKey(),
                    + ((float) secondResults.get(i).getKey() / (float) firstResults.get(i).getKey())));

        return finalResults;
    }

    public static void main(String[] args) {
        //dataStructureComparison(1000000);

        System.out.println(dataStructureComparisonTimesK(10000, 10));
    }
    
}
