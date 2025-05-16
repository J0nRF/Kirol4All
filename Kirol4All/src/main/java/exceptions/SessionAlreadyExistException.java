package exceptions;

public class SessionAlreadyExistException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public SessionAlreadyExistException()
	  {
	    super();
	  }
	  /**This exception is triggered if the question already exists 
	  *@param s String of the exception
	  */
	  public SessionAlreadyExistException(String s)
	  {
	    super(s);
	  }
}
