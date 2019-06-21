//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.01 at 06:25:46 PM CEST 
//


package com.megatravel.dtosoap.global_parameters;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.megatravel.model.system_user_info.Privilege;
import com.megatravel.model.system_user_info.Role;


public class RoleDTO {

    protected long id;
    protected String roleName;
    protected List<PrivilegeDTO> privileges;
    protected Date lastChangedTime;

public RoleDTO() {
    	
    }

    public RoleDTO(Role tempRole) {
		this.id = tempRole.getId();
		this.roleName = tempRole.getRoleName();
		this.lastChangedTime = tempRole.getLastChangedTime();
		this.privileges = new ArrayList<>();
		if(tempRole.getPrivileges() != null) {
			for (Privilege tempPrivilege: tempRole.getPrivileges()) {
				this.privileges.add(new PrivilegeDTO(tempPrivilege));
			}
		}
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
     * Gets the value of the roleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets the value of the roleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoleName(String value) {
        this.roleName = value;
    }

    /**
     * Gets the value of the privileges property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the privileges property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrivileges().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrivilegeDTO }
     * 
     * 
     */
    public List<PrivilegeDTO> getPrivileges() {
        if (privileges == null) {
            privileges = new ArrayList<PrivilegeDTO>();
        }
        return this.privileges;
    }

}