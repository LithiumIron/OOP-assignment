

public class FoodOrder {
	private static int orderNo=0;

	private String[] drinks = {"Sprite","Cola","100 Plus",
								"Water","Pepsi"};
	
	private double[] costDrink={3.00,3.00,3.00,
								1.50,4.00};	
	
	private String[] combo = {""};
	
	
	

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