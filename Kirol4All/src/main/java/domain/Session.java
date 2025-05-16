package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Session implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlID
	@Id 
	private Room room;
	@Id
	private Date date;
	private Activity activity;
	
	public Session(Room room, Date date, Activity activity) {
		this.room = room;
		this.date = date;
		this.activity = activity;
	}
	
	public String toString() {
		return room.getName() + ", " + date.toString() + ", " + activity.getName();
	}
	
	public int getMaxUsers() {
		return room.getMaxUsers();
	}
	
	public double getPrice() {
		return activity.getPrice();
	}
}