public class User {
        
    //data properties
    private String username;
    private String password;
    private String role;
    
    //constructor
    /*public User(){
    	  
    }*/
    public User(String username, String password, String role){
    	  this.username = username;
    	  this.password = password;
    	  this.role = role;
    }
    
    //getter
    public String getUsername(){
    	  return username;
    }
    public String getPassword(){
    	  return password;
    }
    public String getRole(){
    	  return role;
    }
    
    //setter
    public void setUsername(String username){
    	  this.username = username;
    }
    public void setPassword(String password){
    	  this.password = password;
    }
    public void setRole(String role){
    	  this.role = role;
    }
    
    //other methods
    public boolean login(String username, String password){
    	  return this.username.equals(username)&& this.password.equals(password);
    }
    /*public boolean register(String username, String password){
    	  
    }*/
    public void logout(){
    }
    
    //toString methods (testing purpose)
    public String toString(){
    	  return "\nUsername: " + username + "\nPassword: " + password + "\nRole: " + role;
    }
}
