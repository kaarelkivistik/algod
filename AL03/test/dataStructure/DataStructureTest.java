package dataStructure;

import static org.junit.Assert.*;

/**
 * Created by kaarel on 05/10/15.
 */
public class DataStructureTest {

    @org.junit.Test
    public void testStack() throws Exception {
        Object object1 = new Object();
        Object object2 = new Object();
        Object object3 = new Object();

        Stack stack = new Stack();

        assertTrue(stack.isEmpty());

        stack.push(object1);

        assertFalse(stack.isEmpty());

        stack.push(object2);
        stack.push(object3);

        stack.push(null);
        stack.pop();

        assertEquals(object3, stack.pop());
        assertEquals(object2, stack.pop());
        assertEquals(object1, stack.pop());
        assertNull(stack.pop());

        assertTrue(stack.isEmpty());
    }

    @org.junit.Test
    public void testQueue() throws Exception {
        Object object1 = new Object();
        Object object2 = new Object();
        Object object3 = new Object();

        Queue queue = new Queue();

        assertTrue(queue.isEmpty());

        queue.enqueue(object1);
        queue.enqueue(object2);
        queue.enqueue(object3);

        assertFalse(queue.isEmpty());

        assertEquals(object1, queue.dequeue());
        assertEquals(object2, queue.dequeue());
        assertEquals(object3, queue.dequeue());

        assertTrue(queue.isEmpty());
    }
}