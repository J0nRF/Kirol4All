package exceptions;

public class BookingAlreadyExistException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public BookingAlreadyExistException()
	  {
	    super();
	  }
	  /**This exception is triggered if the question already exists 
	  *@param s String of the exception
	  */
	  public BookingAlreadyExistException(String s)
	  {
	    super(s);
	  }
}
