import java.util.ArrayList;

public class Bug implements Contract{
    private String name;
    private BugType b; 
    private int positionx;      //current x position
    private int positiony;   //current y position
    private double size;
    private ArrayList<String> inventory;  //inventory for objects picked up
    private ArrayList<String> lastmove;   //stores last move and object interacted with 
    private ArrayList<Integer> position;  // stores two positions

/**
 * Constructor
 * @param name
 * @param b
 * @param size
 */
    public Bug(String name, BugType b, double size){
        this.name=name;
        this.b=b;
        this.positionx=0;
        this.positiony=0;
        this.size= size;
        this.inventory=new ArrayList<String>();
        this.lastmove= new ArrayList<String>();
        this.lastmove.add("");
        this.lastmove.add("");
        this.position.add(0);
        this.position.add(0);

    }
    /**
     * adds an item to inventory
     * @param item
     */
    public void grab(String item) {
        this.inventory.add(item);
        this.lastmove.set(0,"grab");
        this.lastmove.set(1,item);
        System.out.println("You've grabbed the " + item);
        }
    /**
     * removes item from inventory 
     * @param item
     */
    public String drop(String item) {
        if(this.inventory.contains(item)) {
            this.inventory.remove(item);
            this.lastmove.set(0,"drop");
             this.lastmove.set(1,item);
             System.out.println("You've dropped the " + item); 
        } else {
            throw new RuntimeException("The object does not exist");
        }
        return item;
    }
    /**
     * examines an item 
     */
    public void examine(String item) {
        System.out.println("You are examining the " + item);
        this.lastmove.set(0,"examine");
    }
    /**
     * uses an item in the inventory, then removes it
     */
    public void use(String item) {
        if(this.inventory.contains(item)) {
            System.out.println("you have used the" + item);
            this.lastmove.set(0,"use");
            this.inventory.remove(item);
        } else {
            throw new RuntimeException("You cannot use an item you don't have in your inventory");
        }
    }
    /**
     * walk in a given direction
     * @param direction
     */
    public boolean walk(String direction) {
        if (direction != null) {
            System.out.println("you are walking" + direction);
            this.lastmove.set(0,"walk");
            return true;
        } else {
            throw new RuntimeException("You must give a direction");
        }
    }
    /**
     * fly x, y coordinates
     * @param x
     * @param y
     */
    public boolean fly(int x, int y) {
        System.out.println("You are flying!");
        this.positionx += x;
        this.positiony += y;
        this.lastmove.set(0,"fly");
        this.position.set(0, x);
        this.position.set(1, y);
        return true;
    }
    /**
     * shrink by 0.8
     * @return size
     */
    public Number shrink(){
        this.size = this.size * 0.8;
        this.lastmove.set(0,"shrink");
        return this.size;
    }
    /**
     * grow by 1.2
     */
    public Number grow() {
        this.size = this.size * 1.2;
        this.lastmove.set(0,"grow");
        return this.size;
    }
    /**
     * rest...
     */
    public void rest() {
        System.out.println("resting... zzz...");
        this.lastmove.set(0,"rest");
    }
    /**
     * undoes actions, except those that cannot be undone. 
     */
    public void undo() {

        if (this.lastmove.get(0).equals("grab")) {
            this.drop(this.lastmove.get(1));
        }
        else if (this.lastmove.get(0).equals("drop")) {
            this.grab(this.lastmove.get(1));
        }
        else if(this.lastmove.get(0).equals("examine")) {
            throw new RuntimeException("You cannot un-see that");
        }
        else if(this.lastmove.get(0).equals("use")) {
            throw new RuntimeException("You cannot un-use that");
        }
        else if(this.lastmove.get(0).equals("walk")) {
            System.out.println("you return to your earlier position");
        }
        else if(this.lastmove.get(0).equals("rest")) {
            System.out.println("That time is already lost...");
        }
        else if(this.lastmove.get(0).equals("fly")) {
            this.positionx=this.positionx - this.position.get(0);
            this.positiony=this.positiony - this.position.get(1);
            System.out.println("You've returned to your previous position");
            
        }
        else if(this.lastmove.get(0).equals("shrink")) {
            this.grow();
        }
        else if(this.lastmove.get(0).equals("grow")) {
            this.shrink();
        }
    }

public static void main(String[] args) {
    Bug myBug = new Bug("Lewis", BugType.BEE, 1);
    myBug.examine("flower");
    myBug.grab("stick");
    myBug.undo();

}

}   
