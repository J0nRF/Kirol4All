package businessLogic;

import java.util.Date;
import java.util.List;

import domain.Activity;
import domain.Booking;
//import domain.Booking;
import domain.Ride;
import domain.Room;
import domain.Session;
import domain.User;
import exceptions.RideMustBeLaterThanTodayException;
import exceptions.SessionAlreadyExistException;
import exceptions.UserAlreadyExistException;
import exceptions.ActivityAlreadyExistException;
import exceptions.BookingAlreadyExistException;
import exceptions.BookingIsFullException;
import exceptions.RideAlreadyExistException;

import javax.jws.WebMethod;
import javax.jws.WebService;
 
/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	  
	/**
	 * This method returns all the cities where rides depart 
	 * @return collection of cities
	 */
	@WebMethod public List<String> getDepartCities();
	
	/**
	 * This method returns all the arrival destinations, from all rides that depart from a given city  
	 * 
	 * @param from the depart location of a ride
	 * @return all the arrival destinations
	 */
	@WebMethod public List<String> getDestinationCities(String from);
	
	/**
	 * This method retrieves the rides from two locations on a given date 
	 * 
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date the date of the ride 
	 * @return collection of rides
	 */
	@WebMethod public List<Ride> getRides(String from, String to, Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride 
	 * @param date of the month for which days with rides want to be retrieved 
	 * @return collection of rides
	 */
	@WebMethod public List<Date> getThisMonthDatesWithRides(String from, String to, Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public User createUser(String name, String password, String email, int maxWR, boolean type, int paymentNumber) throws UserAlreadyExistException;
	
	@WebMethod public User getUser(String email);
	
	@WebMethod public boolean Login(String password, User user);
	
	@WebMethod public Activity addActivity(String name, int ge, double price) throws ActivityAlreadyExistException;
	
	@WebMethod public List<String> getRooms();
	
	@WebMethod public List<String> getActivities();
	
	@WebMethod public Session addSession(String room, Date date, String activity)throws SessionAlreadyExistException;
	
	@WebMethod public List<String> getGradosExigencia();
	
	@WebMethod public List<String> getSessionsWA(String activity);
	
	@WebMethod public List<String> getSessionsWGe(String ge);
	
	@WebMethod public Booking bookSession(String room, Date date, User user)throws BookingAlreadyExistException, BookingIsFullException ;
	
	@WebMethod public List<String> getBookingsFromUser(User u);
	
	@WebMethod public String deleteBooking(int id);
	
	@WebMethod public List<String> getUsers();
	
	@WebMethod public List<String> getBookingsWU(String user);
	
	@WebMethod public boolean sendBill(String user, List<Integer> idReservas);
	
	@WebMethod public List<String> getBillsWU(User user);
	
	@WebMethod public List<Integer> getBillsIDWU(User user);
	
	@WebMethod public boolean payBill(int id,User user);
	
	@WebMethod public void initializeBD();

	
}
