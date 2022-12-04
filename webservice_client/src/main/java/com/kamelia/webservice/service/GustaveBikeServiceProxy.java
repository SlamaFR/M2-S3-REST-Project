package com.kamelia.webservice.service;

public class GustaveBikeServiceProxy implements com.kamelia.webservice.service.GustaveBikeService {
  private String _endpoint = null;
  private com.kamelia.webservice.service.GustaveBikeService gustaveBikeService = null;
  
  public GustaveBikeServiceProxy() {
    _initGustaveBikeServiceProxy();
  }
  
  public GustaveBikeServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initGustaveBikeServiceProxy();
  }
  
  private void _initGustaveBikeServiceProxy() {
    try {
      gustaveBikeService = (new com.kamelia.webservice.service.GustaveBikeServiceServiceLocator()).getGustaveBikeService();
      if (gustaveBikeService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)gustaveBikeService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)gustaveBikeService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (gustaveBikeService != null)
      ((javax.xml.rpc.Stub)gustaveBikeService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.kamelia.webservice.service.GustaveBikeService getGustaveBikeService() {
    if (gustaveBikeService == null)
      _initGustaveBikeServiceProxy();
    return gustaveBikeService;
  }
  
  public com.kamelia.webservice.dto.PurchaseResponse buyBike(java.lang.String userId, java.lang.String bikeId) throws java.rmi.RemoteException{
    if (gustaveBikeService == null)
      _initGustaveBikeServiceProxy();
    return gustaveBikeService.buyBike(userId, bikeId);
  }
  
  
}