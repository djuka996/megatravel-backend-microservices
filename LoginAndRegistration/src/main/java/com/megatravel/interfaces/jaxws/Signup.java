
package com.megatravel.interfaces.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "signup", namespace = "http://interfaces.megatravel.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "signup", namespace = "http://interfaces.megatravel.com/")
public class Signup {

    @XmlElement(name = "registrationDTO", namespace = "")
    private com.megatravel.models.SystemUserRegistrationDTO registrationDTO;

    /**
     * 
     * @return
     *     returns SystemUserRegistrationDTO
     */
    public com.megatravel.models.SystemUserRegistrationDTO getRegistrationDTO() {
        return this.registrationDTO;
    }

    /**
     * 
     * @param registrationDTO
     *     the value for the registrationDTO property
     */
    public void setRegistrationDTO(com.megatravel.models.SystemUserRegistrationDTO registrationDTO) {
        this.registrationDTO = registrationDTO;
    }

}
