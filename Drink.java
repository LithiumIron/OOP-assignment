public class Drink extends FoodOrder {
    private String[] drinks = {"Sprite","Cola","100 Plus",
								"Water","Pepsi"};
	
	private double[] costDrink={3.00,3.00,3.00,
								1.50,4.00};		

    public Drink() {
    	super();
    }

    //getter methods
    public String getDrink(int x){
    	if(x>=0&&x<drinks.length){
    		addOrderNo();
    		return drinks[x];
        }
        else{
    		return null; 
        }
    
    } //getter drink end
    
    public double getCostDrink(int x){
    	if(x>=0&&x<costDrink.length){
    		return costDrink[x];
    	}
    	
    	else{
    		return 0.0;
    	}
    	
    }//getter cost end


    //setter methods
    public void setCostDrink(double[] costDrink) {
        this.costDrink = costDrink;
    }

    public void setDrinks(String[] drinks) {
        this.drinks = drinks;
    }
    




}

