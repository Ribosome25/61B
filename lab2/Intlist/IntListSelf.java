import javax.naming.InitialContext;

public class IntListSelf {
    public int first;
    public IntListSelf rest;

    public IntListSelf(int f, IntListSelf r){
        first = f;
        rest = r;
    }

    public int size(){
        if (this.rest == null){
            return 1;
        } else {
            return 1 + this.rest.size();
        }
    }

    public int iterativeSize(){
        int length = 0;
        IntListSelf current = this;
        while(current != null){
            length += 1;
            current = current.rest;
        }
        return length;
    }

    public int get(int ii){
        if(ii>=this.size()){
            return 0;
        }
        if(ii == 0){
            return this.first;
        } else {
            return this.rest.get(ii-1);
        }
    }

    public static void main(String[] args){
        IntListSelf L = new IntListSelf(5, null);
        L = new IntListSelf(10,L);
        L = new IntListSelf(15,L);
        System.out.println(L.iterativeSize());
        System.out.println(L.get(3));
    }
}
