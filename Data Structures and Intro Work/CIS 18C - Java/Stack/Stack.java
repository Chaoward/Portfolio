import java.util.Iterator;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Stack<TYPE> implements Iterator {
    private ArrayList<TYPE> list;

    Stack() {
        list = new ArrayList<>();
    }

    //===== Interface Functions ====================
    public boolean hasNext() {
        return !list.isEmpty();
    }

    public TYPE next() throws NoSuchElementException {
        return list.get(list.size() - 1);
    }
    //===== *END*  Interface Functions *END* ========

    public void push(TYPE item) {
        list.add(item);
    }

    public void pop() {
        list.remove(list.size() - 1);
    }

    public int size() {
        return list.size();
    }
}
