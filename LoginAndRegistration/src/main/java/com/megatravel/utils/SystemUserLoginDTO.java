package com.megatravel.utils;

//
//This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
//See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
//Any modifications to this file will be lost upon recompilation of the source schema. 
//Generated on: 2019.04.21 at 06:51:34 PM CEST 
//



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
*         &lt;element name="Email" type="{http://www.megatravel.com/global_parameters}Email"/>
*         &lt;element name="Password" type="{http://www.megatravel.com/global_parameters}Password"/>
*       &lt;/sequence>
*     &lt;/restriction>
*   &lt;/complexContent>
* &lt;/complexType>
* </pre>
* 
* 
*/

public class SystemUserLoginDTO {

 protected String email;
 protected String password;

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

}
