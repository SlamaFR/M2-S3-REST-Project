/**
 * BankServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kamelia.ebc.bank;

public class BankServiceServiceLocator extends org.apache.axis.client.Service implements BankServiceService {

    public BankServiceServiceLocator() {
    }


    public BankServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public BankServiceServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BankService
    private String BankService_address = "http://localhost:8080/bankservice/services/BankService";

    public String getBankServiceAddress() {
        return BankService_address;
    }

    // The WSDD service name defaults to the port name.
    private String BankServiceWSDDServiceName = "BankService";

    public String getBankServiceWSDDServiceName() {
        return BankServiceWSDDServiceName;
    }

    public void setBankServiceWSDDServiceName(String name) {
        BankServiceWSDDServiceName = name;
    }

    public BankService getBankService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BankService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBankService(endpoint);
    }

    public BankService getBankService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            BankServiceSoapBindingStub _stub = new BankServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getBankServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBankServiceEndpointAddress(String address) {
        BankService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (BankService.class.isAssignableFrom(serviceEndpointInterface)) {
                BankServiceSoapBindingStub _stub = new BankServiceSoapBindingStub(new java.net.URL(BankService_address), this);
                _stub.setPortName(getBankServiceWSDDServiceName());
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
        if ("BankService".equals(inputPortName)) {
            return getBankService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://bank.ebc.kamelia.com", "BankServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://bank.ebc.kamelia.com", "BankService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {
        
if ("BankService".equals(portName)) {
            setBankServiceEndpointAddress(address);
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
