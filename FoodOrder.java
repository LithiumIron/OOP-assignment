//Object ob1= {new FoodOrder(), new Combo(),...}; can make a array and call the food i think

public class FoodOrder {
    private static int foodOrderId=0;
    private int orderNo=0;
    
    @SuppressWarnings("FieldMayBeFinal")//ignore this
    private String[] order=new String[100];
    private double totalPrice=0.0;
    
    //getter

    public static int getFoodOrderId() {
        return foodOrderId;
    }
 
    public int getOrderNo(){
    	return orderNo;
    }
    public String getOrder() {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<orderNo;i++){
            sb.append(order[i]).append(", ");
        }
        return sb.toString();
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    //setter
    public static void setFoodOrderId(int foodOrderId) {
        FoodOrder.foodOrderId = foodOrderId;
    }
    public void setOrder(int x, String order) {     //for programmer or sum
        this.order[x] = order;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    //------------other methods------------
    //add order number
    public void addOrderNo(){
    	orderNo++;
    }//add order number end

    //cost size
    public double largeCost(double x){
    	return x*2;
    }//double end

    @Override   //ignore this
    public String toString(){
        return "Food Order ID: "+foodOrderId+
                "\nOrder: "+getOrder()+
                "\nCost: RM"+totalPrice;
    }
    
    
}//class end
