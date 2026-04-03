public class Staff extends User {
 
    private String staffID;
    private String staffName;
 
    public Staff(String staffID, String staffName, String username, String password) {
        super(staffID, username, password, "STAFF");
        this.staffID   = staffID;
        this.staffName = staffName;
    }

    public String getStaffID()   { return staffID; }
    public String getStaffName() { return staffName; }
    public void   setStaffName(String name) { this.staffName = name; }
 
    
    public void displayMenu() {
        System.out.println("\n========== STAFF MENU ==========");
        System.out.println(" [1] Validate Ticket");
        System.out.println(" [2] View Reports");
        System.out.println(" [3] Manage Concession Stock");
        System.out.println(" [4] Logout");
        System.out.println("=================================");
    }
 
    public String getDisplayName() {
        return "Staff: " + staffName;
    }
 
    // ---------- Polymorphism ----------
    public String toString() {
        return String.format("Staff[%s | %s]", staffID, staffName);
    }
 
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Staff)) return false;
        Staff s = (Staff) obj;
        return staffID.equals(s.staffID);
    }
}
