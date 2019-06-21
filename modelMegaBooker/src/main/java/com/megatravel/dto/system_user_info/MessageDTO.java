//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.05.11 at 09:48:04 PM CEST 
//


package com.megatravel.dto.system_user_info;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.megatravel.dto.hotel.Adapter1;


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
 *         &lt;element name="Caption" type="{http://www.megatravel.com/global_parameters}Description"/>
 *         &lt;element name="Text" type="{http://www.megatravel.com/global_parameters}Description"/>
 *         &lt;element name="Opened" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Date" type="{http://www.megatravel.com/global_parameters}Date"/>
 *         &lt;element ref="{http://www.megatravel.com/system_user_info}System_user_infoDTO"/>
 *         &lt;element ref="{http://www.megatravel.com/system_user_info}System_user_infoDTO"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "caption",
    "text",
    "opened",
    "date",
    "sender",
    "receiver"
})
@XmlRootElement(name = "MessageDTO")
public class MessageDTO {

    @XmlElement(name = "Id")
    protected long id;
    @XmlElement(name = "Caption", required = true)
    protected String caption;
    @XmlElement(name = "Text", required = true)
    protected String text;
    @XmlElement(name = "Opened")
    protected boolean opened;
    @XmlElement(name = "Date", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    protected Date date;
    @XmlElement(name = "System_user_infoDTO", required = true)
    protected SystemUserInfoDTO sender;
    @XmlElement(name = "System_user_infoDTO", required = true)
    protected SystemUserInfoDTO receiver;
    // TODO : Anotacija mozda?
    protected Date lastChangedTime;

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
     * Gets the value of the caption property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaption() {
        return caption;
    }

    /**
     * Sets the value of the caption property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaption(String value) {
        this.caption = value;
    }

    /**
     * Gets the value of the text property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the value of the text property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText(String value) {
        this.text = value;
    }

    /**
     * Gets the value of the opened property.
     * 
     */
    public boolean isOpened() {
        return opened;
    }

    /**
     * Sets the value of the opened property.
     * 
     */
    public void setOpened(boolean value) {
        this.opened = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(Date value) {
        this.date = value;
    }

    /**
     * Gets the value of the sender property.
     * 
     * @return
     *     possible object is
     *     {@link SystemUserInfoDTO }
     *     
     */
    public SystemUserInfoDTO getSender() {
        return sender;
    }

    /**
     * Sets the value of the sender property.
     * 
     * @param value
     *     allowed object is
     *     {@link SystemUserInfoDTO }
     *     
     */
    public void setSender(SystemUserInfoDTO value) {
        this.sender = value;
    }

    /**
     * Gets the value of the receiver property.
     * 
     * @return
     *     possible object is
     *     {@link SystemUserInfoDTO }
     *     
     */
    public SystemUserInfoDTO getReceiver() {
        return receiver;
    }

    /**
     * Sets the value of the receiver property.
     * 
     * @param value
     *     allowed object is
     *     {@link SystemUserInfoDTO }
     *     
     */
    public void setReceiver(SystemUserInfoDTO value) {
        this.receiver = value;
    }

	public Date getLastChangedTime() {
		return lastChangedTime;
	}

	public void setLastChangedTime(Date lastChangedTime) {
		this.lastChangedTime = lastChangedTime;
	}

}
