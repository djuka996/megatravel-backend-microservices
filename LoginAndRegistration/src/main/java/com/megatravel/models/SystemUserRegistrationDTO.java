package com.megatravel.models;

import com.megatravel.dto.global_parameters.AddressDTO;
import com.megatravel.dto.global_parameters.RoleDTO;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Id" type="{http://www.megatravel.com/global_parameters}Id"/>
 *         &lt;element name="First_name" type="{http://www.megatravel.com/global_parameters}Name"/>
 *         &lt;element name="Last_name" type="{http://www.megatravel.com/global_parameters}Name"/>
 *         &lt;element name="Username" type="{http://www.megatravel.com/global_parameters}Name"/>
 *         &lt;element name="Password" type="{http://www.megatravel.com/global_parameters}Password"/>
 *         &lt;element name="Repeat_password" type="{http://www.megatravel.com/global_parameters}Password"/>
 *         &lt;element name="Email" type="{http://www.megatravel.com/global_parameters}Email"/>
 *         &lt;element name="Active" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Adress" type="{http://www.megatravel.com/global_parameters}AddressDTO"/>
 *         &lt;element name="Work_certificate_number" type="{http://www.megatravel.com/global_parameters}Work_certificate_number"/>
 *         &lt;element name="Role" type="{http://www.megatravel.com/global_parameters}RoleDTO"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */

public class SystemUserRegistrationDTO {

    protected long id;
    protected String firstName;
    protected String lastName;
    protected String username;
    protected String password;
    protected String repeatPassword;
    protected String email;
    protected boolean active;
    protected AddressDTO adress;
    protected String workCertificateNumber;
    protected RoleDTO role;

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
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the repeatPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepeatPassword() {
        return repeatPassword;
    }

    /**
     * Sets the value of the repeatPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepeatPassword(String value) {
        this.repeatPassword = value;
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
     * @return
     *     possible object is
     *     {@link RoleDTO }
     *     
     */
    public RoleDTO getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoleDTO }
     *     
     */
    public void setRole(RoleDTO value) {
        this.role = value;
    }

}

