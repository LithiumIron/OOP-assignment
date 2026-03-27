//Object ob1= {new FoodOrder(), new Combo(),...}; can make a array and call the food i think
//look at Practical 6 Q3, its a inheritance question, can take example. 
import java.util.ArrayList;
public class FoodOrder {
//data properties
    private static int orderNo=0;
    private int itemNo=0;    
    private double totalPrice=0.0;
    private ArrayList<String> order = new ArrayList<>();
    
    
    public FoodOrder(){
    	this(0,0.0,new ArrayList<>(),0);
    }
    public FoodOrder(int orderNo, double totalPrice,ArrayList<String> order,int itemNo){
    	FoodOrder.orderNo=++orderNo;
    	this.totalPrice=totalPrice;
    	this.order=new ArrayList<>(order);
    	this.itemNo=itemNo;
    	
    }
    
    //getter
    public static int getOrderNo() {
        return orderNo;
    }
 
    public int getItemNo(){
    	return itemNo;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }
       
    public String getOrder(){
    	StringBuilder sb = new StringBuilder();
    	for(int i=0; i<itemNo; i++){
    		sb.append(order.get(i));
    		if(i<itemNo-1){
    			sb.append(", ");
    		}//if end
    	}//for end
    	return sb.toString();
    }//method end
    
    //setter
    public static void setOrderNo(int orderNo) {
        FoodOrder.orderNo = orderNo;
    }
    
    public void setItemNo(int itemNo){
    	this.itemNo=itemNo;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public void setOrder(ArrayList<String> order) {     //to reset & make a new orders
        this.order.clear();
        this.order.addAll(order);
       	this.itemNo=this.order.size();
    }

    //------------other methods------------
    //add order number
    public void addOrderNo(){
    	orderNo++;
    }//add order number end
    
    public void addItem(String item,double price){
    	order.add(item);
    	totalPrice +=price;
    	itemNo++;
    }

    //cost size
    public double largeCost(double price){
    	return price*2;
    }//double end

    public String toString(){
        return "Order No: "+orderNo+
                "\nOrder: "+getOrder()+
                "\nCost: RM"+totalPrice;
    }
    
    
}//class end