package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Bill implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlID
	@Id
	private int id;
	private double price;
	private User user;
	private List<Booking> bookings;
	private boolean payed;
	
	public Bill(int id, User user) {
		this.id = id;
		this.user = user;
		this.payed = false;
		this.bookings = new ArrayList<Booking>();
	}
	
	public void addBooking(Booking b) {
		bookings.add(b);
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String toString() {
		return "Id: " + id + ", Usuario: " + user.getName() + ", Precio: " + price;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPayed() {
		this.payed = true;
	}

}
