package com.megatravel.LoginAndRegistration.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.megatravel.LoginAndRegistration.dto.global_parameters.PrivilegeDTO;
import com.megatravel.LoginAndRegistration.validation.StaticData;

@Entity
public class Privilege {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
	@NotNull
	@Size(min=StaticData.minLength, max=StaticData.lengthValue)
    private String name;
 
    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
    
    public Privilege() {
    	
    }

	public Privilege(Long id, String name) {
		this.id = id;
		this.name = name;
	}


	public Privilege(PrivilegeDTO privilegeDTO) {
		this.id = privilegeDTO.getId();
		this.name = privilegeDTO.getName();
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
}