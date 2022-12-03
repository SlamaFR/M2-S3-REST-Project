/**
 * Hello.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kamelia.webservice.service;

public interface Hello extends java.rmi.Remote {
    public java.lang.String sayHello(java.lang.String name) throws java.rmi.RemoteException;
    public com.kamelia.webservice.dto.Bike bigTest() throws java.rmi.RemoteException;
    public com.kamelia.webservice.Foo sayFooe(java.lang.String test) throws java.rmi.RemoteException;
}
