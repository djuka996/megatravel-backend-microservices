//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.05.11 at 09:48:04 PM CEST 
//


package com.megatravel.dto.system_user_info;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the vp.spring.mtb.dto.system_user_info package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: vp.spring.mtb.dto.system_user_info
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ChatDTO }
     * 
     */
    public ChatDTO createChatDTO() {
        return new ChatDTO();
    }

    /**
     * Create an instance of {@link MessageDTO }
     * 
     */
    public MessageDTO createMessageDTO() {
        return new MessageDTO();
    }

    /**
     * Create an instance of {@link SystemUserInfoDTO }
     * 
     */
    public SystemUserInfoDTO createSystemUserInfoDTO() {
        return new SystemUserInfoDTO();
    }

    /**
     * Create an instance of {@link UserReviewDTO }
     * 
     */
    public UserReviewDTO createUserReviewDTO() {
        return new UserReviewDTO();
    }

    /**
     * Create an instance of {@link SystemUserLoginDTO }
     * 
     */
    public SystemUserLoginDTO createSystemUserLoginDTO() {
        return new SystemUserLoginDTO();
    }

    /**
     * Create an instance of {@link SystemUserRegistrationDTO }
     * 
     */
    public SystemUserRegistrationDTO createSystemUserRegistrationDTO() {
        return new SystemUserRegistrationDTO();
    }

}
