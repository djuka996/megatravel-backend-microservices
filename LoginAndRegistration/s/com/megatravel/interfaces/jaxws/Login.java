
package com.megatravel.interfaces.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "login", namespace = "http://interfaces.megatravel.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "login", namespace = "http://interfaces.megatravel.com/")
public class Login {

    @XmlElement(name = "loginDTO", namespace = "")
    private com.megatravel.models.SystemUserLoginDTO loginDTO;

    /**
     * 
     * @return
     *     returns SystemUserLoginDTO
     */
    public com.megatravel.models.SystemUserLoginDTO getLoginDTO() {
        return this.loginDTO;
    }

    /**
     * 
     * @param loginDTO
     *     the value for the loginDTO property
     */
    public void setLoginDTO(com.megatravel.models.SystemUserLoginDTO loginDTO) {
        this.loginDTO = loginDTO;
    }

}
