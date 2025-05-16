package businessLogic;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Activity;
import domain.Booking;
import domain.Ride;
import domain.Session;
import domain.User;
import exceptions.RideMustBeLaterThanTodayException;
import exceptions.SessionAlreadyExistException;
import exceptions.UserAlreadyExistException;
import exceptions.ActivityAlreadyExistException;
import exceptions.BookingAlreadyExistException;
import exceptions.BookingIsFullException;
import exceptions.RideAlreadyExistException;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	DataAccess dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		
		
		    dbManager=new DataAccess();
		    
		//dbManager.close();

		
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		
		dbManager=da;		
	}
    
    
    /**
     * {@inheritDoc}
     */
    @WebMethod public List<String> getDepartCities(){
    	dbManager.open();	
		
		 List<String> departLocations=dbManager.getDepartCities();		

		dbManager.close();
		
		return departLocations;
    	
    }
    /**
     * {@inheritDoc}
     */
	@WebMethod public List<String> getDestinationCities(String from){
		dbManager.open();	
		
		 List<String> targetCities=dbManager.getArrivalCities(from);		

		dbManager.close();
		
		return targetCities;
	}
	
   /**
    * {@inheritDoc}
    */
	@WebMethod 
	public List<Ride> getRides(String from, String to, Date date){
		dbManager.open();
		List<Ride>  rides=dbManager.getRides(from, to, date);
		dbManager.close();
		return rides;
	}

    
	/**
	 * {@inheritDoc}
	 */
	@WebMethod 
	public List<Date> getThisMonthDatesWithRides(String from, String to, Date date){
		dbManager.open();
		List<Date>  dates=dbManager.getThisMonthDatesWithRides(from, to, date);
		dbManager.close();
		return dates;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@WebMethod
	public User createUser(String name, String password, String email, int maxWR, boolean type, int paymentNumber) throws UserAlreadyExistException {
		
		dbManager.open();
		User u = dbManager.createUser(name, password, email, maxWR, type, paymentNumber);
		dbManager.close();
		return u;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@WebMethod
	public User getUser(String email) {
		
		dbManager.open();
		User u = dbManager.getUser(email);
		dbManager.close();
		return u;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@WebMethod
	public boolean Login(String password, User user) {
		if (password.equals(user.getPassword())) {
			return true;
		}
		else return false;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@WebMethod
	public Activity addActivity(String name, int ge, double price) throws ActivityAlreadyExistException{
		dbManager.open();
		Activity a = dbManager.addActivity(name, ge, price);
		dbManager.close();
		return a;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@WebMethod
	public List<String> getRooms(){
		dbManager.open();
		List<String> r = dbManager.getRooms();
		dbManager.close();
		return r;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@WebMethod
	public List<String> getActivities(){
		dbManager.open();
		List<String> a = dbManager.getActivities();
		dbManager.close();
		return a;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@WebMethod
	public Session addSession(String room, Date date, String activity)throws SessionAlreadyExistException{
		dbManager.open();
		Session s = dbManager.addSession(room, date, activity);
		dbManager.close();
		return s;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@WebMethod
	public List<String> getGradosExigencia() {
		dbManager.open();
		List<String> ge = dbManager.getGradoExigencia();
		dbManager.close();
		return ge;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@WebMethod 
	public List<String> getSessionsWA(String activity){
		dbManager.open();
		List<String> s = dbManager.getSessionsWA(activity);
		dbManager.close();
		return s;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@WebMethod 
	public List<String> getSessionsWGe(String ge){
		dbManager.open();
		List<String> s = dbManager.getSessionsWGe(ge);
		dbManager.close();
		return s;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@WebMethod
	public Booking bookSession(String room, Date date, User user)throws BookingAlreadyExistException, BookingIsFullException  {
		dbManager.open();
		Booking b = dbManager.bookSession(room, date, user);
		dbManager.close();
		return b;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@WebMethod
	public List<String> getBookingsFromUser(User u) {
		dbManager.open();
		List<String> s = dbManager.getBookingsFromUser(u);
		dbManager.close();
		return s;		
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@WebMethod 
	public String deleteBooking(int id) {
		dbManager.open();
		String s = dbManager.deleteBooking(id);
		dbManager.close();
		return s;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@WebMethod
	public List<String> getUsers(){
		dbManager.open();
		List<String> s = dbManager.getUsers();
		dbManager.close();
		return s;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@WebMethod 
	public List<String> getBookingsWU(String user){
		dbManager.open();
		List<String> s = dbManager.getBookingsWU(user);
		dbManager.close();
		return s;
	}

	
	/**
	 * {@inheritDoc}
	 */
	@WebMethod 
	public boolean sendBill(String user, List<Integer> idReservas) {
		dbManager.open();
		boolean b = dbManager.sendBill(user, idReservas);
		dbManager.close();
		return b;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@WebMethod 
	public List<String> getBillsWU(User user){
		dbManager.open();
		List<String> s = dbManager.getBillsWU(user);
		dbManager.close();
		return s;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@WebMethod
	public List<Integer> getBillsIDWU(User user){
		dbManager.open();
		List<Integer> s = dbManager.getBillsIDWU(user);
		dbManager.close();
		return s;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@WebMethod
	public boolean payBill(int id,User user) {
		dbManager.open();
		boolean b = dbManager.payBill(id, user);
		dbManager.close();
		return b;
	}
	
	
	public void close() {
		DataAccess dB4oManager=new DataAccess();

		dB4oManager.close();

	}

	/**
	 * {@inheritDoc}
	 */
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open();
		dbManager.initializeDB();
		dbManager.close();
	}

}

