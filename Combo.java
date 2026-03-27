import java.util.ArrayList;
public class Combo extends FoodOrder {
    private String[] combo = {"Popcorn + Sprite","Hotdog x2 + Cola",
                                "French Fries + 100 Plus","Sweet Corn + Sprite",
                                "Nuggets + Pepsi"};
    
    private double[] comboCost={13.00,12.00,
                                6.00,9.50,
                                8.00};
    

	public Combo(){
		this(0,0.0,new ArrayList<>(),0);
	}

    public Combo(int orderNo, double totalPrice, ArrayList<String> order, int itemNo) {
    	super(orderNo, totalPrice, order, itemNo);	
    }
    
    //getter 
    public String getComboAndCost(){
    	StringBuilder sb = new StringBuilder();
    	for(int i=0; i<combo.length; i++){
    		if(i==0 || i==4)
    			sb.append(String.format("%d. %-25s RM%.2f\n", i + 1, combo[i], comboCost[i]));
        	else
            	sb.append(String.format("%d. %-25s RM%.2f\n", i + 1, combo[i], comboCost[i]));

    	}//for end
    	
    	return sb.toString();
    }

	//setter methods
	public void setCombo(String[] combo) {
		this.combo = combo;
	}
	public void setComboCost(double[] comboCost) {
		this.comboCost = comboCost;
	}

    //-------------------other methods-------------------
    public void addCombo(int choice){
    	if(choice>0 && choice<=combo.length){
    		super.addItem(combo[choice-1],comboCost[choice-1]);
    	}
    }// method end
   
    
    public String toString(){
    	return getComboAndCost();
    }
}