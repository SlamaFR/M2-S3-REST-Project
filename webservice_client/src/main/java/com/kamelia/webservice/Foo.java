/**
 * Foo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kamelia.webservice;

public class Foo  implements java.io.Serializable {
    private int ouep;

    private java.lang.String test;

    public Foo() {
    }

    public Foo(
           int ouep,
           java.lang.String test) {
           this.ouep = ouep;
           this.test = test;
    }


    /**
     * Gets the ouep value for this Foo.
     * 
     * @return ouep
     */
    public int getOuep() {
        return ouep;
    }


    /**
     * Sets the ouep value for this Foo.
     * 
     * @param ouep
     */
    public void setOuep(int ouep) {
        this.ouep = ouep;
    }


    /**
     * Gets the test value for this Foo.
     * 
     * @return test
     */
    public java.lang.String getTest() {
        return test;
    }


    /**
     * Sets the test value for this Foo.
     * 
     * @param test
     */
    public void setTest(java.lang.String test) {
        this.test = test;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Foo)) return false;
        Foo other = (Foo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.ouep == other.getOuep() &&
            ((this.test==null && other.getTest()==null) || 
             (this.test!=null &&
              this.test.equals(other.getTest())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getOuep();
        if (getTest() != null) {
            _hashCode += getTest().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Foo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.kamelia.com", "Foo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ouep");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservice.kamelia.com", "ouep"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("test");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservice.kamelia.com", "test"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
