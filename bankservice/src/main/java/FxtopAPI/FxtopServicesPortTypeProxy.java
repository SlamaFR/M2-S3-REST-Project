package FxtopAPI;

public class FxtopServicesPortTypeProxy implements FxtopServicesPortType {
  private String _endpoint = null;
  private FxtopServicesPortType fxtopServicesPortType = null;
  
  public FxtopServicesPortTypeProxy() {
    _initFxtopServicesPortTypeProxy();
  }
  
  public FxtopServicesPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initFxtopServicesPortTypeProxy();
  }
  
  private void _initFxtopServicesPortTypeProxy() {
    try {
      fxtopServicesPortType = (new FxtopServicesLocator()).getFxtopServicesPort();
      if (fxtopServicesPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)fxtopServicesPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)fxtopServicesPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (fxtopServicesPortType != null)
      ((javax.xml.rpc.Stub)fxtopServicesPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public FxtopServicesPortType getFxtopServicesPortType() {
    if (fxtopServicesPortType == null)
      _initFxtopServicesPortTypeProxy();
    return fxtopServicesPortType;
  }
  
  public ConvertResult convert(String originalAmount, String c1, String c2, String date, String user, String password) throws java.rmi.RemoteException{
    if (fxtopServicesPortType == null)
      _initFxtopServicesPortTypeProxy();
    return fxtopServicesPortType.convert(originalAmount, c1, c2, date, user, password);
  }
  
  public CurrencyDescription descCurrency(String lang, String isocode) throws java.rmi.RemoteException{
    if (fxtopServicesPortType == null)
      _initFxtopServicesPortTypeProxy();
    return fxtopServicesPortType.descCurrency(lang, isocode);
  }
  
  public String listCurrencies(String user, String password) throws java.rmi.RemoteException{
    if (fxtopServicesPortType == null)
      _initFxtopServicesPortTypeProxy();
    return fxtopServicesPortType.listCurrencies(user, password);
  }
  
  
}