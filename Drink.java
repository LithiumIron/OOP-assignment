import java.util.ArrayList;
public class Drink extends FoodOrder {
    private String[] drinks = {"Sprite","Cola","100 Plus",
								"Water","Pepsi"};
	
	private double[] costDrink={3.00,3.00,3.00,
								1.50,4.00};		

	public Drink(){
		this(0,0.0,new ArrayList<>(),0);
	}

    public Drink(int orderNo, double totalPrice, ArrayList<String> order, int itemNo) {
    	super(orderNo, totalPrice, order, itemNo);	
    }
    
    //getter 
    public String getDrinksAndCost(){
    	StringBuilder sb = new StringBuilder();
    	for(int i=0; i<drinks.length; i++){
    		if(i==0 || i==4)
    			sb.append(String.format("%d. %-15s RM%.2f\n", i + 1, drinks[i], costDrink[i]));
        	else
            	sb.append(String.format("%d. %-15s RM%.2f\n", i + 1, drinks[i], costDrink[i]));

    	}//for end
    	
    	return sb.toString();
    }

	//setter methods
	public void setDrinks(String[] drinks) {
		this.drinks = drinks;
	}
	public void setCostDrink(double[] costDrink) {
		this.costDrink = costDrink;
	}

    //-------------------other methods-------------------
    public void addDrink(int choice){
    	if(choice>0 && choice<=drinks.length){
    		super.addItem(drinks[choice-1],costDrink[choice-1]);
    	}
    }// method end
   
    
    public String toString(){
    	return getDrinksAndCost();
    }
}

