public class Combo extends FoodOrder {
    private String[] combo = {"Popcorn + Sprite","Hotdog x2 + Cola",
                                "French Fries + 100 Plus","Chicken Meatball + Water",
                                "Nuggets + Pepsi"};
    
    private double[] comboCost={13.00,12.00,
                                6.00,9.50,
                                8.00};
    

    public Combo() {
    	super();
    	
    }
    
    //getter methods
    public String getCombo(int x){
    	if(x>=0&&x<combo.length){
    		addOrderNo();
    		return combo[x];
    		
    	}
    	
    	else{
    		return null;
    	}
    		
    } //getter combo end
    	
    public double getComboCost(int x){
    	if(x>=0&&x<comboCost.length){
    		return comboCost[x];
    	}
    	
    	else{
    		return 0.0;
    	}
    	
    }//getter cost end

    public double[] getComboCost() {
        return comboCost;
    }


    //setter methods
    public void setComboCost(double[] comboCost) {
        this.comboCost = comboCost;
    }

    public void setCombo(String[] combo) {
        this.combo = combo;
    }
    
}       