//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.21 at 06:51:34 PM CEST 
//


package com.megatravel.dtosoap.system_user_info;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.megatravel.dtosoap.global_parameters.AddressDTO;
import com.megatravel.dtosoap.global_parameters.RoleDTO;
import com.megatravel.dtosoap.hotel.HotelDTO;
import com.megatravel.dtosoap.room_reservation.RoomReservationDTO;
import com.megatravel.model.system_user_info.User;

public class SystemUserInfoDTO {

    protected long id;
    protected String firstName;
    protected String lastName;
    protected String username;
    protected String email;
    protected String password;
    protected String salt;
    protected boolean active;
    protected Date lastChangedTime;
    protected AddressDTO adress;
    protected String workCertificateNumber;
    protected List<RoleDTO> role = new ArrayList<RoleDTO>();
    protected HotelDTO hotelDTO;
    protected Collection<RoomReservationDTO> roomReservationDTO = new ArrayList<RoomReservationDTO>();

    
    public SystemUserInfoDTO() { }

   	public SystemUserInfoDTO(User user) {
   		this.id = user.getId();
   		this.email = user.getEmail();
   		this.firstName = user.getName();
   		this.lastName = user.getLastName();
   		this.password = user.getPassword();
   		this.salt = user.getSalt();
   		this.lastChangedTime = user.getLastChangedTime();
   		this.active = user.isActive();
   	}
    
    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the active property.
     * 
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the value of the active property.
     * 
     */
    public void setActive(boolean value) {
        this.active = value;
    }

    /**
     * Gets the value of the adress property.
     * 
     * @return
     *     possible object is
     *     {@link AddressDTO }
     *     
     */
    public AddressDTO getAdress() {
        return adress;
    }

    /**
     * Sets the value of the adress property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressDTO }
     *     
     */
    public void setAdress(AddressDTO value) {
        this.adress = value;
    }

    /**
     * Gets the value of the workCertificateNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkCertificateNumber() {
        return workCertificateNumber;
    }

    /**
     * Sets the value of the workCertificateNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkCertificateNumber(String value) {
        this.workCertificateNumber = value;
    }

    /**
     * Gets the value of the role property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the role property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRole().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RoleDTO }
     * 
     * 
     */
    public List<RoleDTO> getRole() {
        if (role == null) {
            role = new ArrayList<RoleDTO>();
        }
        return this.role;
    }

    /**
     * Gets the value of the hotelDTO property.
     * 
     * @return
     *     possible object is
     *     {@link HotelDTO }
     *     
     */
    public HotelDTO getHotelDTO() {
        return hotelDTO;
    }

    /**
     * Sets the value of the hotelDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link HotelDTO }
     *     
     */
    public void setHotelDTO(HotelDTO value) {
        this.hotelDTO = value;
    }

    /**
     * Gets the value of the roomReservationDTO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the roomReservationDTO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoomReservationDTO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RoomReservationDTO }
     * 
     * 
     */
    public Collection<RoomReservationDTO> getRoomReservationDTO() {
        if (roomReservationDTO == null) {
            roomReservationDTO = new ArrayList<RoomReservationDTO>();
        }
        return this.roomReservationDTO;
    }

	public Date getLastChangedTime() {
		return lastChangedTime;
	}

	public void setLastChangedTime(Date lastChangedTime) {
		this.lastChangedTime = lastChangedTime;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public void setRole(List<RoleDTO> role) {
		this.role = role;
	}

	public void setRoomReservationDTO(Collection<RoomReservationDTO> roomReservationDTO) {
		this.roomReservationDTO = roomReservationDTO;
	}

	
}
