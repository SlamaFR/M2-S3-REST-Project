package com.kamelia.webservice.service;

public class HelloProxy implements com.kamelia.webservice.service.Hello {
  private String _endpoint = null;
  private com.kamelia.webservice.service.Hello hello = null;
  
  public HelloProxy() {
    _initHelloProxy();
  }
  
  public HelloProxy(String endpoint) {
    _endpoint = endpoint;
    _initHelloProxy();
  }
  
  private void _initHelloProxy() {
    try {
      hello = (new com.kamelia.webservice.service.HelloServiceLocator()).getHello();
      if (hello != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)hello)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)hello)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (hello != null)
      ((javax.xml.rpc.Stub)hello)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.kamelia.webservice.service.Hello getHello() {
    if (hello == null)
      _initHelloProxy();
    return hello;
  }
  
  public java.lang.String sayHello(java.lang.String name) throws java.rmi.RemoteException{
    if (hello == null)
      _initHelloProxy();
    return hello.sayHello(name);
  }
  
  public com.kamelia.webservice.dto.Bike bigTest() throws java.rmi.RemoteException{
    if (hello == null)
      _initHelloProxy();
    return hello.bigTest();
  }
  
  public com.kamelia.webservice.Foo sayFooe(java.lang.String test) throws java.rmi.RemoteException{
    if (hello == null)
      _initHelloProxy();
    return hello.sayFooe(test);
  }
  
  
}