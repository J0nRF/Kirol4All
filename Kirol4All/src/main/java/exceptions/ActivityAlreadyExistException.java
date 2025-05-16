package exceptions;

public class ActivityAlreadyExistException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public ActivityAlreadyExistException()
	  {
	    super();
	  }
	  /**This exception is triggered if the question already exists 
	  *@param s String of the exception
	  */
	  public ActivityAlreadyExistException(String s)
	  {
	    super(s);
	  }
}
