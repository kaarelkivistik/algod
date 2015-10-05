package dataStructure;

public class Dequeue {
    private Stack back = new Stack();
    private Stack front = new Stack();

    public void pushFirst(Object object) {
        front.push(object);
    }

    public void pushLast(Object object) {
        back.push(object);
    }

    public Object popFirst() {
        if(front.isEmpty())
            while(!back.isEmpty())
                front.push(back.pop());

        return front.pop();
    }

    public Object popLast() {
        if(back.isEmpty())
            while(!front.isEmpty())
                back.push(front.pop());

        return back.pop();
    }

    public boolean isEmpty() {
        return front.isEmpty() && back.isEmpty();
    }
}
