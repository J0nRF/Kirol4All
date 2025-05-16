package exceptions;

public class BookingIsFullException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public BookingIsFullException()
	  {
	    super();
	  }
	  /**This exception is triggered if the question already exists 
	  *@param s String of the exception
	  */
	  public BookingIsFullException(String s)
	  {
	    super(s);
	  }
}
