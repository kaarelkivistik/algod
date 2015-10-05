package dataStructure;

public class Stack {

    private int index = 0;

    private Object[] stack = new Object[10];

    public void push(Object object) {
        if(index == stack.length)
            increaseStack();

        stack[index] = object;
    }

    public Object pop() {
        return null;
    }

    public boolean isEmpty() {
        return true;
    }

    private void increaseStack() {
        Object[] newStack = new int[stack.length * 2];

        for(int i = 0; i < stack.length; i++)
            newStack[i] = stack[i];
    }
}

