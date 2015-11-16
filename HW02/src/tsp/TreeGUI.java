package tsp;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.MouseEvent;
import java.util.Arrays;

/**
 * Created by kaarel on 14/11/15.
 */
public class TreeGUI {

    static int[][] matrix = TestData.mediumMatrix;

    public static void main(String[] args) {
        JFrame frame = new JFrame("TSP search tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultMutableTreeNode top = new DefaultMutableTreeNode(new TSPNode(new int[]{0}));

        JTree tree = new JTree(top);

        tree.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if(e.getClickCount() == 2) {
                    TreePath treePath = tree.getPathForLocation(e.getX(), e.getY());

                    if(treePath != null) {
                        DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) treePath.getLastPathComponent();
                        TSPNode tspNode = (TSPNode) treeNode.getUserObject();

                        if(tspNode != null && tspNode.getNode() != null) {
                            int[][] children = TSP.childrenOf(matrix.length, tspNode.getNode());

                            if(children == null)
                                treeNode.add(new DefaultMutableTreeNode(new TSPNode(TSP.extendLeafNode(tspNode.getNode()))));
                            else
                                for (int[] child : children)
                                    treeNode.add(new DefaultMutableTreeNode(new TSPNode(child)));

                            tree.expandPath(treePath);
                        }

                        tree.treeDidChange();
                    }
                }
            }
        });

        for (int i = 0; i < tree.getRowCount(); i++) {
            //tree.expandRow(i);
        }

        frame.setContentPane(new JScrollPane(tree));
        frame.setSize(640, 480);
        frame.setVisible(true);
    }

/*    public static DefaultMutableTreeNode generateTree(int[] node) {
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
    }*/

    static class TSPNode {
        private int[] node;

        public TSPNode(int[] node) {
            this.node = node;
        }

        public int[] getNode() {
            return node;
        }

        public void setNode(int[] node) {
            this.node = node;
        }

        @Override
        public String toString() {
            return Arrays.toString(node) + " " + (node.length == matrix.length + 1 ?
                    TSP.distanceOf(matrix, node) : "b:" + TSP.bound(matrix, node));
        }
    }
}
