
package com.iti.parking.ws.client.generated;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "ParkingOrderEmulatorWebServiceService", targetNamespace = "http://server.ws.parking.iti.com/", wsdlLocation = "http://localhost:8080/services/emulator?wsdl")
public class ParkingOrderEmulatorWebServiceService
    extends Service
{

    private final static URL PARKINGORDEREMULATORWEBSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException PARKINGORDEREMULATORWEBSERVICESERVICE_EXCEPTION;
    private final static QName PARKINGORDEREMULATORWEBSERVICESERVICE_QNAME = new QName("http://server.ws.parking.iti.com/", "ParkingOrderEmulatorWebServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/services/emulator?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        PARKINGORDEREMULATORWEBSERVICESERVICE_WSDL_LOCATION = url;
        PARKINGORDEREMULATORWEBSERVICESERVICE_EXCEPTION = e;
    }

    public ParkingOrderEmulatorWebServiceService() {
        super(__getWsdlLocation(), PARKINGORDEREMULATORWEBSERVICESERVICE_QNAME);
    }

    public ParkingOrderEmulatorWebServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), PARKINGORDEREMULATORWEBSERVICESERVICE_QNAME, features);
    }

    public ParkingOrderEmulatorWebServiceService(URL wsdlLocation) {
        super(wsdlLocation, PARKINGORDEREMULATORWEBSERVICESERVICE_QNAME);
    }

    public ParkingOrderEmulatorWebServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, PARKINGORDEREMULATORWEBSERVICESERVICE_QNAME, features);
    }

    public ParkingOrderEmulatorWebServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ParkingOrderEmulatorWebServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ParkingOrderEmulatorWebService
     */
    @WebEndpoint(name = "ParkingOrderEmulatorWebServicePort")
    public ParkingOrderEmulatorWebService getParkingOrderEmulatorWebServicePort() {
        return super.getPort(new QName("http://server.ws.parking.iti.com/", "ParkingOrderEmulatorWebServicePort"), ParkingOrderEmulatorWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ParkingOrderEmulatorWebService
     */
    @WebEndpoint(name = "ParkingOrderEmulatorWebServicePort")
    public ParkingOrderEmulatorWebService getParkingOrderEmulatorWebServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://server.ws.parking.iti.com/", "ParkingOrderEmulatorWebServicePort"), ParkingOrderEmulatorWebService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (PARKINGORDEREMULATORWEBSERVICESERVICE_EXCEPTION!= null) {
            throw PARKINGORDEREMULATORWEBSERVICESERVICE_EXCEPTION;
        }
        return PARKINGORDEREMULATORWEBSERVICESERVICE_WSDL_LOCATION;
    }

}