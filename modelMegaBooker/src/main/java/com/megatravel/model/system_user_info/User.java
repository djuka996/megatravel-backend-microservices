package com.megatravel.model.system_user_info;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.megatravel.model.hotel.Hotel;
import com.megatravel.model.room_reservation.RoomReservation;
import com.megatravel.validation.EmailValidation;
import com.megatravel.validation.StaticData;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastChangedTime;
	
	@NotNull
    @Size(min=StaticData.minLength, max=StaticData.lengthValue)
	private String name;
	
	@NotNull
    @Size(min=StaticData.minLength, max=StaticData.lengthValue)
	private String lastName;
	
	@NotNull
	private String password;
	
	@NotNull
	private String salt;
	
	@EmailValidation
	@NotNull
    @Size(min=StaticData.minLengthEmail, max=StaticData.maxLengthEmail)
	private String email;

	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( 
        name = "users_roles", 
        joinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;
	
	@OneToMany(mappedBy = "usersReservation")
	private Set<RoomReservation> roomReservations;
	
	@OneToMany(mappedBy = "usersHotel")
	private Set<Hotel> hotels;
	
	@OneToMany(mappedBy = "sender")
	private Set<Message> senders;
	
	@OneToMany(mappedBy = "receiver")
	private Set<Message> receivers;
		
	public User() { }

	public User(Long id, String name, String lastName, String password, String salt, String email, Date lastChangedTime) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.password = password;
		this.salt = salt;
		this.email = email;
		this.lastChangedTime = lastChangedTime;
	}

	public User(com.megatravel.dto.system_user_info.SystemUserInfoDTO systemUserInfoDTO) {
		this.id = systemUserInfoDTO.getId();
		this.name = systemUserInfoDTO.getFirstName();
		this.lastName = systemUserInfoDTO.getLastName();
		this.email = systemUserInfoDTO.getEmail();
	}
	
	public User(com.megatravel.dtosoap.system_user_info.SystemUserInfoDTO systemUserInfoDTO) {
		this.id = systemUserInfoDTO.getId();
		this.name = systemUserInfoDTO.getFirstName();
		this.lastName = systemUserInfoDTO.getLastName();
		this.email = systemUserInfoDTO.getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
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
		return id == other.id;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Date getLastChangedTime() {
		return lastChangedTime;
	}

	public void setLastChangedTime(Date lastChangedTime) {
		this.lastChangedTime = lastChangedTime;
	}
}
