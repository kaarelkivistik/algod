package dataStructures;

public class Node {

        public String name;
        public float k;

        @Override
        public String toString() {
                return "Node{" +
                        "name='" + name + '\'' +
                        ", k=" + k +
                        '}';
        }

        public Node(String name, float k) {
                this.name = name;
                this.k = k;
        }
}