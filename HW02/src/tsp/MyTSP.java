package tsp;

import java.util.*;

/**
 * Created by kaarel on 16/11/15.
 */
public class MyTSP {
    private int[][] matrix;

    public MyTSP(int[][] matrix) {
        this.matrix = matrix;
    }

    public List<Integer> findOptimalTourUsingBfs() {
        PriorityQueue<Node> queue = new PriorityQueue<>(Node.BOUND_COMPARATOR);

        List<Integer> optimalTour = null;
        Node v = new Node(Collections.singletonList(0));
        int minLength = Integer.MAX_VALUE;

        v.setBound(bound(v.getTour()));

        queue.add(v);

        while(!queue.isEmpty()) {
            v = queue.remove();

            if(v.getBound() < minLength) {
                for(List<Integer> tour : getNextPossibleTours(v.getTour())) {
                    if(tour.size() == matrix.length + 1) {
                        int length = length(tour);

                        if (length < minLength) {
                            minLength = length;
                            optimalTour = tour;
                        }
                    } else {
                        Node u = new Node(tour);
                        u.setBound(bound(tour));

                        if(u.getBound() < minLength)
                            queue.add(u);
                    }
                }
            }
        }

        return optimalTour;
    }

    public List<Integer> findOptimalTourUsingDfs(boolean ignoreBounds) {
        Stack<Node> stack = new Stack<>();

        List<Integer> optimalTour = null;
        Node v = new Node(Collections.singletonList(0));
        int minLength = Integer.MAX_VALUE;

        v.setBound(bound(v.getTour()));

        stack.push(v);

        while(!stack.isEmpty()) {
            v = stack.pop();

            for(List<Integer> tour : getNextPossibleTours(v.getTour())) {
                if(tour.size() == matrix.length + 1) {
                    int length = length(tour);

                    if(length < minLength) {
                        minLength = length;
                        optimalTour = tour;
                    }
                } else if(ignoreBounds) {
                    stack.push(new Node(tour));
                } else {
                    Node u = new Node(tour);
                    u.setBound(bound(tour));

                    if(u.getBound() < minLength)
                        stack.push(u);
                }
            }
        }

        return optimalTour;
    }

    public int bound(List<Integer> tour) {
        int bound = length(tour);

        for(int i = 0; i < matrix.length; i++) {
            if(!tour.contains(i))
                bound += minimumDistance(matrix[i]);
        }

        if(tour.size() > 0)
            bound += minimumDistance(matrix[tour.get(tour.size() - 1)]);

        return bound;
    }

    public int length(List<Integer> tour) {
        int length = 0;

        if(tour.size() > 1)
            for(int i = 0; i < tour.size() - 1; i++)
                length += matrix[tour.get(i)][tour.get(i + 1)];

        return length;
    }

    public static int minimumDistance(int[] array) {
        return Arrays.stream(array)
                .filter(i -> i > 0)
                .min().getAsInt();
    }

    public Set<List<Integer>> getNextPossibleTours(List<Integer> tour) {
        Set<List<Integer>> tours = new HashSet<>();

        for(int i = 0; i < matrix.length; i++) {
            if(!tour.contains(i)) {
                List<Integer> nextTour = new ArrayList<>(tour);
                nextTour.add(i);

                if(tour.size() == matrix.length - 1)
                    nextTour.add(tour.get(0));

                    tours.add(nextTour);
            }
        }

        return tours;
    }
}
