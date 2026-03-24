
public class Snack extends FoodOrder {
	
	private String[] snacks = {"Popcorn","Hotdog x2",
								"French Fries","Chicken Meatball",
								"Nuggets"};
	
	private double[] snacksCost={11.00,10.00,
								4.00,8.00,
								6.00};
	

    public Snack() {
    	super();
    	
    }
    
    //getter methods
    public String getSnack(int x){
    	if(x>=0&&x<snacks.length){
    		addOrderNo();
    		return snacks[x];
    		
    	}
    	
    	else{
    		return null;
    	}
    		
    } //getter snack end
    	
    public double getSnackCost(int x){
    	if(x>=0&&x<snacksCost.length){
    		return snacksCost[x];
    	}
    	
    	else{
    		return 0.0;
    	}
    	
    }//getter cost end


	//setter methods
    public void setSnacks(String[] snacks) {
        this.snacks = snacks;
    }

    public void setSnacksCost(double[] snacksCost) {
        this.snacksCost = snacksCost;
    }
    
}