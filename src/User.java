/*
Created by Owen van Mantgem: 3/26/2023
 */
public class User {
    private String userName;
    private String passHash;

    public User(String userName, String passHash) {
        this.userName = userName;
        this.passHash = passHash;
    }

    public String getUserName() {return userName;}
    public String getPassHash() {return  passHash;}

}
