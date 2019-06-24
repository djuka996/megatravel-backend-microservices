package com.megatravel.model.system_user_info;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.megatravel.validation.StaticData;

@Entity
public class Privilege {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastChangedTime;
	
	@NotNull
	@Size(min=StaticData.minLength, max=StaticData.lengthValue)
    private String name;
 
    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
    
    public Privilege() { }

    public Privilege(com.megatravel.dto.global_parameters.PrivilegeDTO privilegeDTO) {
    	this.id = privilegeDTO.getId();
    	this.name = privilegeDTO.getName();
    	this.lastChangedTime = privilegeDTO.getLastChangedTime();
    }
    
    public Privilege(com.megatravel.dtosoap.global_parameters.PrivilegeDTO privilegeDTO) {
    	this.id = privilegeDTO.getId();
    	this.name = privilegeDTO.getName();
    	this.lastChangedTime = privilegeDTO.getLastChangedTime();
    }
    
	public Privilege(Long id, String name, Date lastChangedTime) {
		this.id = id;
		this.name = name;
		this.lastChangedTime = lastChangedTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
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
		Privilege other = (Privilege) obj;
		return id == other.id;
	}

	public Date getLastChangedTime() {
		return lastChangedTime;
	}

	public void setLastChangedTime(Date lastChangedTime) {
		this.lastChangedTime = lastChangedTime;
	}
}