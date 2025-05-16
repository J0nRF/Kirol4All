package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import gui.MainGUI;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Booking implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public User user;
	public Session session;
	@Id
	public int id;
	
	public Booking(User u, Session s, int id) {
		this.user = u;
		this.session = s;
		this.id = id;
	}
	
	public String toString() {
		return id + ", " + user.getName() + ", " + session.toString();
	}
	
	public double getPrice() {
		return session.getPrice();
	}
}
