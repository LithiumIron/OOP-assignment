import java.util.ArrayList;
public class Snack extends FoodOrder {
	
	private String[] snack = {"Popcorn","Hotdog x2",
								"French Fries","Sweet Corn",
								"Nuggets"};
	
	private double[] snackCost={8.00,10.00,
								4.00,4.00,
								6.00};
	
	public Snack(){
		this(0,0.0,new ArrayList<>(),0);
	}

    public Snack(int orderNo, double totalPrice, ArrayList<String> order, int itemNo) {
    	super(orderNo, totalPrice, order, itemNo);	
    }
    
    //getter 
    public String getSnackAndCost(){
    	StringBuilder sb = new StringBuilder();
    	for(int i=0; i<snack.length; i++){
    		if(i==0 || i==4)
    			sb.append(String.format("%d. %-15s RM%.2f\n", i + 1, snack[i], snackCost[i]));
        	else
            	sb.append(String.format("%d. %-15s RM%.2f\n", i + 1, snack[i], snackCost[i]));

    	}//for end
    	
    	return sb.toString();
    }
    /* getter 
    public String getSnackCost(){
    	StringBuilder sb = new StringBuilder();
    	for(int i=0; i<snackCost.length; i++){
    		sb.append("RM"+snackCost[i]);
    		if(i<snack.length-1){
    			sb.append("\n");
    		}//if end
    	}//for end
    	
    	return sb.toString();
    }*/
    
	//setter methods
	public void setSnack(String[] snack) {
		this.snack = snack;
	}
	public void setSnackCost(double[] snackCost) {
		this.snackCost = snackCost;
	}

    //-------------------other methods-------------------
    public void addSnack(int choice){
    	if(choice>0 && choice<=snack.length){
    		super.addItem(snack[choice-1],snackCost[choice-1]);
    	}
    }// method end
   
    
    public String toString(){
    	return getSnackAndCost();
    }
}