public class Customer extends User {
 
    private String customerID;
    private int    rewardPoint;
 
    public Customer(String customerID, String username, String password) {
        super(customerID, username, password, "CUSTOMER");
        this.customerID  = customerID;
        this.rewardPoint = 0;
    }
 
    public String getCustomerID()  { return customerID; }
    public int    getRewardPoint() { return rewardPoint; }
 
    public void setRewardPoint(int pts) {
        if (pts < 0) throw new IllegalArgumentException("Reward points cannot be negative.");
        this.rewardPoint = pts;
    }
 
    public void addRewardPoints(int pts) {
        if (pts <= 0) throw new IllegalArgumentException("Points to add must be positive.");
        this.rewardPoint += pts;
    }
 
    // ---------- Domain methods ----------
 
    public void redeemReward(int pointsRequired) {
        if (rewardPoint < pointsRequired) {
            System.out.println("Insufficient reward points. You have " + rewardPoint + " pts.");
            return;
        }
        rewardPoint -= pointsRequired;
        System.out.println("Reward redeemed! Remaining points: " + rewardPoint);
    }

    public void displayMenu() {
        System.out.println("\n========== CUSTOMER MENU ==========");
        System.out.println(" [1] Browse Movies");
        System.out.println(" [2] Book Ticket");
        System.out.println(" [3] View My Bookings");
        System.out.println(" [4] Food & Concessions");
        System.out.println(" [5] Redeem Rewards");
        System.out.println(" [6] Logout");
        System.out.println("====================================");
    }
 
    public String getDisplayName() {
        return "Customer: " + getUsername() + " (" + rewardPoint + " pts)";
    }
 
    // ---------- Polymorphism ----------
    public String toString() {
        return String.format("Customer[%s | %s | Points: %d]",
            customerID, getUsername(), rewardPoint);
    }
 
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Customer)) return false;
        Customer c = (Customer) obj;
        return customerID.equals(c.customerID);
    }
}
