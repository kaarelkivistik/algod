package tsp;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Arrays;

/**
 * Created by kaarel on 14/11/15.
 */
public class TreeGUI {

    static int[][] smallMatrix = {
            {0,   128,  71,  85, 185},
            {128,   0,  91,  62,  58},
            { 71,  91,   0,  29, 149},
            { 85,  62,  29,   0, 120},
            {185,  58, 149, 120,   0}};

    public static void main(String[] args) {
        JFrame frame = new JFrame("TSP search tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultMutableTreeNode top = generateTree(new int[]{});

        JTree tree = new JTree(top);

        for (int i = 0; i < tree.getRowCount(); i++) {
            //tree.expandRow(i);
        }

        frame.setContentPane(new JScrollPane(tree));
        frame.setSize(640, 480);
        frame.setVisible(true);
    }

    public static DefaultMutableTreeNode generateTree(int[] node) {
        DefaultMutableTreeNode swingNode = new DefaultMutableTreeNode(Arrays.toString(node) + " b:" + TSP.bound(smallMatrix, node));

        int[][] children = TSP.childrenOf(smallMatrix.length, node);

        if(children != null)
            for(int[] child : children)
                swingNode.add(generateTree(child));
        else {
            int[] extendedNode = TSP.extendLeafNode(node);

            swingNode.setUserObject(Arrays.toString(extendedNode) + " " + TSP.distanceOf(smallMatrix, extendedNode));
        }

        return swingNode;
    }

}
