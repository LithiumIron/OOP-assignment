

public class FoodOrder {
	private static int orderNo=0;

    public FoodOrder() {
    	this(0);
    }
    public FoodOrder(int i){
    	
    }
    
    public void addOrderNo(){
    	orderNo++;
    }
    
    //getter method
    public int getOrderNo(){
    	return orderNo;
    }
    
    //cost size
    public double largeCost(double x){
    	return x*2;
    }//double end
    
}//class end