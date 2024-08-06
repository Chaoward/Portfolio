package src;

import java.util.ArrayList;
import java.util.Date;

public class History {
    // Collection to store data
    private ArrayList<String> activityList;
    private ArrayList<Date> dateLog;
    private Integer topIndex;

    public History() {
        this.activityList =  new ArrayList<>();
        this.dateLog = new ArrayList<>();
        this.topIndex = -1;
    }


    // Push method (push items onto stack)
    public void push(String itemToPush) {
        this.activityList.add(itemToPush);
        this.dateLog.add(new Date());
        this.topIndex++;
// notes
    }

    // Pop method (pop items off stack and returns them)
    public String pop() {
        this.activityList.remove(topIndex);
        this.dateLog.remove(topIndex);
        return this.activityList.get(topIndex--);
    }

    // Top method (peeks at top item in stack)
    public String top() throws Exception {
        if (this.topIndex >= 0) {
            return this.activityList.get(this.topIndex);
        }else{
            throw new Exception("Stack is empty!");
        }
    }

    public boolean isEmpty() {
        return this.topIndex < 0;
    }


    //displays list of the history
    public void display() {
        if (this.topIndex < 0) {
            System.out.println("Nothing sent recently!");
            return;
        }
        System.out.println("Here are the recent messages sent");

        for (int i = this.activityList.size() - 1; i > 0; --i) {
            System.out.println("[Text]: " + this.activityList.get(i) +
                    " {" + this.dateLog.get(i).toString() + "}");
        }
    }
}