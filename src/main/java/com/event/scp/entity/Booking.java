package com.event.scp.entity;

import javax.persistence.*;

//it will auto create a table once application will run
@Entity
@Table(name ="bookings")
public class Booking {

	// unique auto generated id
	//PK
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	// column names will display in db
	@Column(name="f_name")
	//variables for get and set methods
    private String fName;
	
	@Column(name="l_name")
    private String lName;
	
	@Column(name="address")
    private String address;
	
	@Column(name="city")
    private String city;
	
	@Column(name="eir_code")
    private String eircode;
	
	@Column(name="email")
    private String email;
	
	@Column(name="number")
    private String number;

	@Column(name="booking_from")
    private String booking_from;
	
	@Column(name="booking_till")
    private String booking_till;
	
	@Column(name="no_people")
    private int no_people;
	
	@Column(name="request")
    private String request;
	
	// Constructors 
    public Booking(){
    
    }
    
    public Booking(String fName, String lName, String address,String city, String eircode, String email, String number, String booking_till, String booking_from, String request, int no_people) {
        
        this.fName = fName;
        this.lName = lName;
        this.address = address;
        this.email = email;
        this.number = number;
        this.city=city;
        this.eircode=eircode;
        this.request=request;
        this.booking_from=booking_from;
        this.booking_till=booking_till;
        this.no_people=no_people;
    }

    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEircode() {
		return eircode;
	}

	public void setEircode(String eircode) {
		this.eircode = eircode;
	}

	public String getBooking_from() {
		return booking_from;
	}

	public void setBooking_from(String booking_from) {
		this.booking_from = booking_from;
	}

	public String getBooking_till() {
		return booking_till;
	}

	public void setBooking_till(String booking_till) {
		this.booking_till = booking_till;
	}

	public int getNo_people() {
		return no_people;
	}

	public void setNo_people(int no_people) {
		this.no_people = no_people;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
	
	
}
