/**
 * FxtopServicesLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package FxtopAPI;

public class FxtopServicesLocator extends org.apache.axis.client.Service implements FxtopServices {

/**
 * This document describes the Fxtop services, for more information,
 * please contact us at webmaster@fxtop.com see more on https://fxtop.com/en/developpers.php#ws
 */

    public FxtopServicesLocator() {
    }


    public FxtopServicesLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public FxtopServicesLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for FxtopServicesPort
    private String FxtopServicesPort_address = "https://fxtop.com/dev/FxtopServices.php";

    public String getFxtopServicesPortAddress() {
        return FxtopServicesPort_address;
    }

    // The WSDD service name defaults to the port name.
    private String FxtopServicesPortWSDDServiceName = "FxtopServicesPort";

    public String getFxtopServicesPortWSDDServiceName() {
        return FxtopServicesPortWSDDServiceName;
    }

    public void setFxtopServicesPortWSDDServiceName(String name) {
        FxtopServicesPortWSDDServiceName = name;
    }

    public FxtopServicesPortType getFxtopServicesPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(FxtopServicesPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFxtopServicesPort(endpoint);
    }

    public FxtopServicesPortType getFxtopServicesPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            FxtopServicesBindingStub _stub = new FxtopServicesBindingStub(portAddress, this);
            _stub.setPortName(getFxtopServicesPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setFxtopServicesPortEndpointAddress(String address) {
        FxtopServicesPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (FxtopServicesPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                FxtopServicesBindingStub _stub = new FxtopServicesBindingStub(new java.net.URL(FxtopServicesPort_address), this);
                _stub.setPortName(getFxtopServicesPortWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("FxtopServicesPort".equals(inputPortName)) {
            return getFxtopServicesPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:FxtopAPI", "FxtopServices");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:FxtopAPI", "FxtopServicesPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {
        
if ("FxtopServicesPort".equals(portName)) {
            setFxtopServicesPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
