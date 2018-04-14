
public class User {
	private String userName,url;
	public User(String userName,String url){
		this.userName=userName;this.url=url;
	}
	public String getUserName(){
		return userName;
	}
	public String getURL(){
		return url;
	}
	public String getPassword(){
		return userName.substring(userName.length()-6);
	}
}
