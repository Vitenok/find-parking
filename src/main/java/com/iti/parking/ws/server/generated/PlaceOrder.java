
package com.iti.parking.ws.server.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "placeOrder", namespace = "http://server.ws.parking.iti.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "placeOrder", namespace = "http://server.ws.parking.iti.com/", propOrder = {
    "arg0",
    "arg1",
    "arg2"
})
public class PlaceOrder {

    @XmlElement(name = "arg0", namespace = "")
    private String arg0;
    @XmlElement(name = "arg1", namespace = "")
    private int arg1;
    @XmlElement(name = "arg2", namespace = "")
    private String arg2;

    /**
     * 
     * @return
     *     returns String
     */
    public String getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(String arg0) {
        this.arg0 = arg0;
    }

    /**
     * 
     * @return
     *     returns int
     */
    public int getArg1() {
        return this.arg1;
    }

    /**
     * 
     * @param arg1
     *     the value for the arg1 property
     */
    public void setArg1(int arg1) {
        this.arg1 = arg1;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getArg2() {
        return this.arg2;
    }

    /**
     * 
     * @param arg2
     *     the value for the arg2 property
     */
    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

}
