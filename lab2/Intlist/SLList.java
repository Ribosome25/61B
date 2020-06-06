public class SLList {
    public IntNode first;
    private int size;

    public SLList(){
        first = null;
        size = 0;
    }
    public SLList(int x){
        first = new IntNode(x,null);
        size = 1;
    }

    public void addFirst(int x){
        this.first = new IntNode(x, this.first);
        size +=1;
    }
    public int getFirst(){
        return this.first.item;
    }

    public void addLast(int x){
        /*Iterative*/
        IntNode p = this.first;
        if(this.first==null){
            this.first=new IntNode(x,null);
        } else {
            while (p.next != null) {
                p = p.next;
            }
            p.next = new IntNode(x, null);
        }
        size += 1;
    }

    public int size(){
        int count = 1;
        IntNode p = this.first;
        while(p.next != null){
            p = p.next;
            count += 1;
        }
        return count;
    }

    public static void main(String[] args){
        System.out.println("Starts here.");
        SLList L = new SLList();
        L.addLast(1);
        L.addLast(5);
        System.out.println(L.size());
    }
}
