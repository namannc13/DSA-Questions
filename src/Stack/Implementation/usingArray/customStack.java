package Stack.Implementation.usingArray;

public class customStack {
    protected int[] data;
    private static final int default_size = 10;

    int ptr = -1;

    public customStack(){
        this(default_size);
    }

    public customStack(int size){
        this.data = new int[size];
    }

    public boolean push(int item){
        if(isFull()){
            System.out.println("Stack is full!!");
            return false;
        }
        ptr ++;
        data[ptr] = item;
        return true;
    }

    public boolean isFull() {
        return ptr == data.length -1;
    }

    public boolean isEmpty(){
        return ptr == -1;
    }

    public int pop() throws Exception{
        if(isEmpty()){
            throw new Exception("Stack is Empty!! Can't be popped");
        }
        return data[ptr--];
    }

    public int peek() throws Exception{
        if(isEmpty()){
            throw new Exception("Stack is Empty!! Can't be peeked");
        }
        return data[ptr];
    }
}
