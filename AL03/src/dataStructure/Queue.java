package dataStructure;

public class Queue {

    private Stack inbox = new Stack();
    private Stack outbox = new Stack();

    public void enqueue(Object object) {
        inbox.push(object);
    }

    public Object dequeue() {
        if(outbox.isEmpty())
            while(!inbox.isEmpty())
                outbox.push(inbox.pop());

        return outbox.pop();
    }

    public boolean isEmpty() {
        return inbox.isEmpty() && outbox.isEmpty();
    }
}
