package com.kamelia.ebc.bank;

public class BankServiceProxy implements com.kamelia.ebc.bank.BankService {
  private String _endpoint = null;
  private com.kamelia.ebc.bank.BankService bankService = null;
  
  public BankServiceProxy() {
    _initBankServiceProxy();
  }
  
  public BankServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initBankServiceProxy();
  }
  
  private void _initBankServiceProxy() {
    try {
      bankService = (new com.kamelia.ebc.bank.BankServiceServiceLocator()).getBankService();
      if (bankService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)bankService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)bankService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (bankService != null)
      ((javax.xml.rpc.Stub)bankService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.kamelia.ebc.bank.BankService getBankService() {
    if (bankService == null)
      _initBankServiceProxy();
    return bankService;
  }
  
  public com.kamelia.ebc.bank.dto.Response debit(java.lang.String bankAccount, java.lang.String currency, double amount) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.debit(bankAccount, currency, amount);
  }
  
  public com.kamelia.ebc.bank.dto.Response credit(java.lang.String bankAccount, java.lang.String currency, double amount) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.credit(bankAccount, currency, amount);
  }
  
  public com.kamelia.ebc.bank.dto.Response checkBalance(java.lang.String bankAccount, double amount) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.checkBalance(bankAccount, amount);
  }
  
  public double getBalance(java.lang.String bankAccount, java.lang.String currency) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getBalance(bankAccount, currency);
  }
  
  
}