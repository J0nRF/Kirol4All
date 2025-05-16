package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Room implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlID
	private int maxUsers;
	@Id
	private String name;
	
	public Room(int maxUsers, String name) {
		this.maxUsers = maxUsers;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public int getMaxUsers() {
		return maxUsers;
	}
}
