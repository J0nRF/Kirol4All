package dataAccess;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.User;
import domain.Activity;
import domain.Bill;
import domain.Booking;
import domain.Payment;
import domain.Ride;
import domain.Room;
import domain.Session;
import exceptions.ActivityAlreadyExistException;
import exceptions.BookingAlreadyExistException;
import exceptions.BookingIsFullException;
import exceptions.SessionAlreadyExistException;
import exceptions.UserAlreadyExistException;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	private  EntityManager  db;
	private  EntityManagerFactory emf;


	ConfigXML c=ConfigXML.getInstance();

     public DataAccess()  {
		if (c.isDatabaseInitialized()) {
			String fileName=c.getDbFilename();

			File fileToDelete= new File(fileName);
			if(fileToDelete.delete()){
				File fileToDeleteTemp= new File(fileName+"$");
				fileToDeleteTemp.delete();

				  System.out.println("File deleted");
				} else {
				  System.out.println("Operation failed");
				}
		}
		open();
		if  (c.isDatabaseInitialized())initializeDB();
		
		System.out.println("DataAccess created => isDatabaseLocal: "+c.isDatabaseLocal()+" isDatabaseInitialized: "+c.isDatabaseInitialized());

		close();

	}
     
    public DataAccess(EntityManager db) {
    	this.db=db;
    }

	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		db.getTransaction().begin();

		try {
		   
		   User Encargado = new User("Encargado", "1234", "qwer", 0, false, 0);
		   Encargado.setType(true);
		   User jon = new User("Jon","4321","Jon", 10, false, 584632);
		   
		   db.persist(Encargado);
		   db.persist(jon);

		   Room grande = new Room(20,"Grande");
		   Room pequeña = new Room(6, "Pequeña");
		   Room cancha = new Room(10, "Cancha");
		   Room pistaTenis = new Room(4, "Pista de Tenis");
		   
		   db.persist(grande);
		   db.persist(pequeña);
		   db.persist(cancha);
		   db.persist(pistaTenis);
		   
		   Activity tenis = new Activity("Tenis", 4, 5.9);
		   Activity padel = new Activity("Padel", 5, 3.2);
		   Activity spinning = new Activity("Spinning", 8, 4);
		   
		   db.persist(tenis);
		   db.persist(padel);
		   db.persist(spinning);
		   
		   SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
		   
		   Session Padel1 = new Session(pistaTenis, formatter.parse("MON May 19 17:00:00 CEST 2025"), padel);
		   Session Padel2 = new Session(pistaTenis, formatter.parse("MON May 19 18:00:00 CEST 2025"), padel);
		   Session Padel3 = new Session(pistaTenis, formatter.parse("MON May 19 19:00:00 CEST 2025"), padel);
		   
		   Session Tenis1 = new Session(pistaTenis, formatter.parse("MON May 19 10:00:00 CEST 2025"), tenis);
		   Session Tenis2 = new Session(pistaTenis, formatter.parse("MON May 19 11:00:00 CEST 2025"), tenis);
		   Session Tenis3 = new Session(pistaTenis, formatter.parse("MON May 19 12:00:00 CEST 2025"), tenis);
		   
		   Session Spinning1 = new Session(pequeña, formatter.parse("MON May 19 10:00:00 CEST 2025"), spinning);
		   Session Spinning2 = new Session(pequeña, formatter.parse("MON May 19 14:00:00 CEST 2025"), spinning);
		   Session Spinning3 = new Session(pequeña, formatter.parse("MON May 19 18:00:00 CEST 2025"), spinning);
		   
		   db.persist(Padel1);
		   db.persist(Padel2);
		   db.persist(Padel3);
		   
		   db.persist(Tenis1);
		   db.persist(Tenis2);
		   db.persist(Tenis3);
		   
		   db.persist(Spinning1);
		   db.persist(Spinning2);
		   db.persist(Spinning3);
		   
		   Booking reserva1 = new Booking(jon, Padel1, 0);
		   Booking reserva2 = new Booking(jon, Padel2, 1);
		   Booking reserva3 = new Booking(jon, Padel3, 2);
		   Booking reserva4 = new Booking(jon, Tenis1, 3);
		   Booking reserva5 = new Booking(jon, Spinning1, 4);
		   
		   db.persist(reserva1);
		   db.persist(reserva2);
		   db.persist(reserva3);
		   db.persist(reserva4);
		   db.persist(reserva5);
		   
		   Bill bill = new Bill(0, jon);
		   
		   bill.addBooking(reserva5);
		   bill.setPrice(4);
		   
		   db.persist(bill);
		   
			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method returns all the cities where rides depart 
	 * @return collection of cities
	 */
	public List<String> getDepartCities(){
			TypedQuery<String> query = db.createQuery("SELECT DISTINCT r.from FROM Ride r ORDER BY r.from", String.class);
			List<String> cities = query.getResultList();
			return cities;
		
	}
	/**
	 * This method returns all the arrival destinations, from all rides that depart from a given city  
	 * 
	 * @param from the depart location of a ride
	 * @return all the arrival destinations
	 */
	public List<String> getArrivalCities(String from){
		TypedQuery<String> query = db.createQuery("SELECT DISTINCT r.to FROM Ride r WHERE r.from=?1 ORDER BY r.to",String.class);
		query.setParameter(1, from);
		List<String> arrivingCities = query.getResultList(); 
		return arrivingCities;
		
	}
	
	/**
	 * This method retrieves the rides from two locations on a given date 
	 * 
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date the date of the ride 
	 * @return collection of rides
	 */
	public List<Ride> getRides(String from, String to, Date date) {
		System.out.println(">> DataAccess: getRides=> from= "+from+" to= "+to+" date "+date);

		List<Ride> res = new ArrayList<>();	
		TypedQuery<Ride> query = db.createQuery("SELECT r FROM Ride r WHERE r.from=?1 AND r.to=?2 AND r.date=?3",Ride.class);   
		query.setParameter(1, from);
		query.setParameter(2, to);
		query.setParameter(3, date);
		List<Ride> rides = query.getResultList();
	 	 for (Ride ride:rides){
		   res.add(ride);
		  }
	 	return res;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride 
	 * @param date of the month for which days with rides want to be retrieved 
	 * @return collection of rides
	 */
	public List<Date> getThisMonthDatesWithRides(String from, String to, Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		List<Date> res = new ArrayList<>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT r.date FROM Ride r WHERE r.from=?1 AND r.to=?2 AND r.date BETWEEN ?3 and ?4",Date.class);   
		
		query.setParameter(1, from);
		query.setParameter(2, to);
		query.setParameter(3, firstDayMonthDate);
		query.setParameter(4, lastDayMonthDate);
		List<Date> dates = query.getResultList();
	 	 for (Date d:dates){
		   res.add(d);
		  }
	 	return res;
	}
	
	public User createUser(String name, String password, String email, int maxWR, boolean type, int paymentNumber) throws UserAlreadyExistException {
		try {
			TypedQuery<User> query = db.createQuery("SELECT u FROM User u WHERE u.email=?1",User.class);
			
			query.setParameter(1, email);
			
			db.getTransaction().begin();
			
			if(query.getResultList().size()==0) {
				User u = new User(name, password, email, maxWR, type, paymentNumber);
				db.persist(u);
				db.getTransaction().commit();
				
				return u;
			} else {
				db.getTransaction().commit();
				throw new UserAlreadyExistException(ResourceBundle.getBundle("Etiquetas").getString("DataAccess.UserAlreadyExist"));
			}
			
		} catch (RollbackException e) {
			throw new UserAlreadyExistException("User Already Exists");
		}
	}
	
	public User getUser(String email) {
		TypedQuery<User> query = db.createQuery("SELECT u FROM User u WHERE u.email=?1",User.class);
		
		query.setParameter(1, email);
		if (query.getResultList().size()==1) {
			return query.getResultList().get(0);
		} else return null;		
	}
	
	public Activity addActivity(String name, int ge, double price) throws ActivityAlreadyExistException{
		try {
			TypedQuery<Activity> query = db.createQuery("SELECT a FROM Activity a WHERE a.name=?1", Activity.class);
			
			query.setParameter(1, name);
			
			db.getTransaction().begin();
			
			if (query.getResultList().size()==0) {
				Activity a = new Activity(name, ge, price);
				
				db.persist(a);
				db.getTransaction().commit();
				
				return a;
			}
			else {
				db.getTransaction().commit();
				throw new ActivityAlreadyExistException("La actividad ya existe");
			}			
		} catch(Exception e){
			throw new ActivityAlreadyExistException("La actividad ya existe");
		}
	}
	
	public List<String> getRooms(){
		try {
			TypedQuery<String> query = db.createQuery("SELECT r.name FROM Room r",String.class);
			
			return query.getResultList();		
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<String> getActivities(){
		try {
			TypedQuery<String> query = db.createQuery("SELECT a.name FROM Activity a",String.class);
			
			return query.getResultList();		
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Session addSession(String room, Date date, String activity)throws SessionAlreadyExistException{
		try {
			TypedQuery<Room> query = db.createQuery("SELECT r FROM Room r WHERE r.name=?1", Room.class);			
			query.setParameter(1, room);			
			Room r = query.getResultList().getFirst();
			
			TypedQuery<Session> query1 = db.createQuery("SELECT s FROM Session s WHERE s.room=?1 AND s.date=?2", Session.class);
			query1.setParameter(1, r);
			query1.setParameter(2, date);
			
			db.getTransaction().begin();
			
			if (query1.getResultList().size()==0) {
				TypedQuery<Activity> query2 = db.createQuery("Select a FROM Activity a WHERE a.name=?1", Activity.class);
				query2.setParameter(1, activity);
				
				Session s = new Session(r,date,query2.getResultList().getFirst());
				
				db.persist(s);
				
				db.getTransaction().commit();
				return s;
			}
			else {
				db.getTransaction().commit();
				
				throw new SessionAlreadyExistException("La sala esta ocupada esa fecha");
			}
			
			
		} catch (Exception e) {
			throw new SessionAlreadyExistException("La sala esta ocupada esa fecha");
		}	
	}
	
	public List<String> getGradoExigencia(){
		try {
			TypedQuery<Integer> query = db.createQuery("SELECT a.ge FROM Activity a",int.class);
			List<String> l = new ArrayList<String>();
			List<Integer> n = query.getResultList();
			for (int i=0; i<n.size(); i++) {
				l.add(n.get(i).toString());
			}
			return l;			
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<String> getSessionsWA(String activity){
		try {
			TypedQuery<Session> query = db.createQuery("SELECT s FROM Session s WHERE s.activity.name=?1", Session.class);
			
			query.setParameter(1, activity);
			
			List<Session> s = query.getResultList();
			List<String> l = new ArrayList<String>();
			
			for (int i=0; i<s.size(); i++) {
				l.add(s.get(i).toString());
			}
			
			return l;
			
		}	catch (Exception e) {
			throw e;
		}
	}
	
	public List<String> getSessionsWGe(String ge){
		try {
			TypedQuery<Session> query = db.createQuery("SELECT s FROM Session s WHERE s.activity.ge=?1", Session.class);
			
			query.setParameter(1, Integer.parseInt(ge));
			
			List<Session> s = query.getResultList();
			List<String> l = new ArrayList<String>();
			
			for (int i=0; i<s.size(); i++) {
				l.add(s.get(i).toString());
			}
			
			return l;
			
		}	catch (Exception e) {
			throw e;
		}
	}
	
	public Booking bookSession(String room, Date date, User user)throws BookingAlreadyExistException, BookingIsFullException {
		try {
			TypedQuery<Session> query = db.createQuery("SELECT s FROM Session s WHERE s.room.name=?1 AND s.date=?2", Session.class);
			
			query.setParameter(1, room);
			query.setParameter(2, date);
			
			TypedQuery<Booking> query1 = db.createQuery("SELECT b FROM Booking b WHERE b.session=?1 AND b.user=?2", Booking.class);
			
			Session s = query.getResultList().getFirst();
			
			query1.setParameter(1, s);
			query1.setParameter(2, user);
			
			db.getTransaction().begin();
			
			if (query1.getResultList().size()==0) {
				
				TypedQuery<Booking> query2 = db.createQuery("SELECT b FROM Booking b WHERE b.session=?1", Booking.class);
				
				query2.setParameter(1, s);
				
				if (query2.getResultList().size()<s.getMaxUsers()) {
					TypedQuery<Booking> query4 = db.createQuery("SELECT b FROM Booking b WHERE b.user=?1", Booking.class);
					
					query4.setParameter(1, user);
					
					if (query4.getResultList().size()<user.getMaxWR()) {
						TypedQuery<Integer> query3 = db.createQuery("SELECT b.id FROM Booking b",int.class);
						int id = 0;
						List<Integer> l = query3.getResultList();
						for (int i=0; i<l.size(); i++) {
							if (l.get(i)>id) {
								id = l.get(i);
							}
						}
						Booking b = new Booking(user, s, id+1);

						db.persist(b);
						db.getTransaction().commit();
						return b;
					}
					else {
						db.getTransaction().commit();
						throw new BookingIsFullException("Has llegado al limite de reservas");
					}
				}
				else {
					db.getTransaction().commit();
					throw new BookingIsFullException("No quedan hueco libres");
				}			
			}
			else {
				db.getTransaction().commit();
				throw new BookingAlreadyExistException("Esta reserva ya existe");				
			}		
		} catch (Exception e) {
			throw new BookingAlreadyExistException(e.getMessage());
		}	
	}
	
	
	public List<String> getBookingsFromUser(User u) {
		try {
			TypedQuery<Booking> query = db.createQuery("SELECT b FROM Booking b WHERE b.user=?1", Booking.class);
			
			query.setParameter(1, u);
			
			List<Booking> s = query.getResultList();
			List<String> l = new ArrayList<String>();
			
			for (int i=0; i<s.size(); i++) {
				l.add(s.get(i).toString());
			}
			
			return l;
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public String deleteBooking(int id) {
		try {
			TypedQuery<Booking> query = db.createQuery("SELECT b FROM Booking b WHERE b.id=?1", Booking.class);
			
			query.setParameter(1, id);
			
			Booking b = query.getResultList().getFirst();
			
			db.getTransaction().begin();
			if(b!=null) {
				db.remove(b);
				db.getTransaction().commit();
				return "Reserva cancelada";		
			}
			else {
				db.getTransaction().commit();
				return "Ha ocurrido un error";
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public List<String> getUsers(){
		try {	
			TypedQuery<String> query = db.createQuery("SELECT u.name FROM User u", String.class);
			return query.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public List<String> getBookingsWU(String user){
		try {
			TypedQuery<Booking> query = db.createQuery("SELECT b FROM Booking b WHERE b.user.name=?1", Booking.class);
			
			query.setParameter(1, user);
			
			List<Booking> s = query.getResultList();
			List<String> l = new ArrayList<String>();
			
			for (int i=0; i<s.size(); i++) {
				l.add(s.get(i).toString());
			}
			
			return l;
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public boolean sendBill(String user, List<Integer> idReservas) {
		try {
			TypedQuery<User> query = db.createQuery("SELECT u FROM User u WHERE u.name=?1", User.class);
			
			query.setParameter(1, user);
			
			TypedQuery<Integer> query1 = db.createQuery("SELECT b.id FROM Bill b",int.class);
			int id = 0;
			List<Integer> l = query1.getResultList();
			for (int i=0; i<l.size(); i++) {
				if (l.get(i)>id) {
					id = l.get(i);
				}
			}
			
			db.getTransaction().begin();
			
			Bill b = new Bill(id+1, query.getResultList().getFirst());
			double price = 0;
			
			TypedQuery<Booking> query2 = db.createQuery("SELECT b FROM Booking b WHERE b.id=?1",Booking.class);
			for (int i=0; i<idReservas.size(); i++) {
				query2.setParameter(1, idReservas.get(i));
				Booking res = query2.getResultList().getFirst();
				b.addBooking(res);
				price += res.getPrice();
			}
			b.setPrice(price);
			
			db.persist(b);
			
			db.getTransaction().commit();		
			return true;
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public List<String> getBillsWU(User user){
		try {
			TypedQuery<Bill> query = db.createQuery("SELECT b FROM Bill b WHERE b.user=?1 AND b.payed=?2", Bill.class);
			
			query.setParameter(1, user);
			query.setParameter(2, false);
			
			List<Bill> s = query.getResultList();
			List<String> l = new ArrayList<String>();
			
			for (int i=0; i<s.size(); i++) {
				l.add(s.get(i).toString());
			}
			
			return l;
		} catch (Exception e) {
			throw e;
		}	
	}
	
	
	public List<Integer> getBillsIDWU(User user) {
		try {
			TypedQuery<Integer> query = db.createQuery("SELECT b.id FROM Bill b WHERE b.user=?1 AND b.payed=?2", int.class);
			
			query.setParameter(1, user);
			query.setParameter(2, false);
			
			return query.getResultList();			
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public boolean payBill(int id, User user) {
		try {
			TypedQuery<Bill> query = db.createQuery("SELECT b FROM Bill b WHERE b.id=?1", Bill.class);
			
			query.setParameter(1, id);
			
			
			TypedQuery<Payment> query1 = db.createQuery("Select u.payment FROM User u WHERE u.name=?1", Payment.class);
			
			query1.setParameter(1, user.getName());
			
			Bill b = query.getResultList().getFirst();
			Payment p = query1.getResultList().getFirst();
			
			db.getTransaction().begin();
			
			if (b.getPrice()<p.getMoney()) {
				b.setPayed();
				p.pay(b.getPrice());;
				db.getTransaction().commit();
				return true;
			}
			else {
				db.getTransaction().commit();
				return false;
			}
					
		} catch (Exception e) {
			throw e;
		}
	}
	

public void open(){
		
		String fileName=c.getDbFilename();
		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);
			  db = emf.createEntityManager();
    	   }
		System.out.println("DataAccess opened => isDatabaseLocal: "+c.isDatabaseLocal());
		
	}

	public void close(){
		db.close();
		System.out.println("DataAcess closed");
	}	
}
