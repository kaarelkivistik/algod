package dataStructure;

public class Stack {

    private int index = -1;

    private Object[] stack = new Object[10];

    public void push(Object object) {
        if(index == stack.length)
            increaseStack();

        stack[++index] = object;
    }

    public Object pop() {
        if(isEmpty())
            return null;

        return stack[index--];
    }

    public boolean isEmpty() {
        return index == -1;
    }

    private void increaseStack() {
        Object[] newStack = new Object[stack.length * 2];

        for(int i = 0; i < stack.length; i++)
            newStack[i] = stack[i];
    }
}

