package Graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Friends {


    private static ArrayList<Integer> connectionList;
    
    public static void bfs(int[][] adjacencyMatrix, int[] pair){
        LinkedList<ArrayList<Integer>> queue = new LinkedList<>();

        ArrayList<Integer> startingNode = new ArrayList<>();

        startingNode.add(pair[0]);

        queue.add(startingNode);

        while(!queue.isEmpty()) {
            ArrayList<Integer> node = queue.remove();

            for(ArrayList<Integer> child : friendsOf(adjacencyMatrix, node)) {
                if(child.get(0) == pair[0] && child.get(child.size() - 1) == pair[1]) {
                    connectionList = child;
                    return;
                }

                queue.add(child);
            }
        }
    }
    
    public static int getDistance() {
        return getConnectionList().length - 1;
    }
    
    public static int[] getConnectionList(){
        if(connectionList == null)
            return null;

        return connectionList.stream().mapToInt(i -> i).toArray();
    }

    public static ArrayList<ArrayList<Integer>> friendsOf(int[][] matrix, List<Integer> node) {
        int lastFriend = node.get(node.size() - 1);
        int[] friendsOfLastFriend = matrix[lastFriend];

        ArrayList<ArrayList<Integer>> children = new ArrayList<>();

        for(int i = 0; i < friendsOfLastFriend.length; i++) {
            if(friendsOfLastFriend[i] == 1 && !node.contains(i)) {
                ArrayList<Integer> newNode = new ArrayList<>(node);
                newNode.add(i);

                children.add(newNode);
            }
        }

        return children;
    }
}