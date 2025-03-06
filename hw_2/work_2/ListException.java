public class ListException extends Exception {
	public ListException(String message){
		super(message);
		System.err.println(message);
		return;
	}
}
