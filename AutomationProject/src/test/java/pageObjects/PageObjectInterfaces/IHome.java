package pageObjects.PageObjectInterfaces;

public interface IHome {
	
	public boolean validateUserIsLoggedIn();
	
	public boolean getAllBoards();
	
	public boolean creatBorad(String boardName);
	
	public boolean deleteBoard(String boardID);
	
	public boolean deleteAllBoard();
	
	public boolean addList(String listName);
	
	public boolean removeList(String listName);
	
	public boolean addCard(String cardName);
	
	public boolean removeCard(String cardName);
	
	public boolean moveAcard(String from ,String to, String cardName);
	
}
