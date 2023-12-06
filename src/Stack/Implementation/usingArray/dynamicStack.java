package Stack.Implementation.usingArray;

public class dynamicStack extends customStack{
    public dynamicStack(){
        super(); // it will call customStack();
    }

    public dynamicStack(int size){
        super(size); // it will call customStack(int size);
    }

    @Override
    public boolean push(int item){
        if(this.isFull()){
            int[] temp = new int[data.length * 2]; // create an array double the size and then copy the elements

            for( int i =0; i < data.length; i ++){
                temp[i] = data[i];
            }

            data = temp;
        }

        return super.push(item); // at this point , we know that the array is not full so use the push function of the customStack() via super.push();
    }
}
