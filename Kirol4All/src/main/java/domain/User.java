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

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlID
	@Id 
	private String email;
	private String name;
	private String password;
	private boolean type; //Si es true es el encargado, sino es un socio
	private int maxWR;
	private boolean typePayment; //Si es true es cuenta, si es false es tarjeta
	@OneToOne(cascade = CascadeType.ALL)
	private Payment payment;

	public User() {
		super();
	}

	public User(String name, String password, String email, int maxWR, boolean typePayment, int paymentId) {
		this.name = name;
		this.password = password;
		this.type = false;
		this.email = email;
		this.maxWR = maxWR;
		this.typePayment = typePayment;
		this.payment = new Payment(paymentId);
	}
	
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean getType() {
		return this.type;
	}
	
	public void setType(boolean type) {
		this.type = type;
	}

	public int getMaxWR() {
		return maxWR;
	}
	
	public String toString(){
		return email+";"+name;
	}
		
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email != other.email)
			return false;
		return true;
	}
}
