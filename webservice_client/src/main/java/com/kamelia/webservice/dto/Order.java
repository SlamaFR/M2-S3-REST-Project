/**
 * Order.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kamelia.webservice.dto;

public class Order  implements java.io.Serializable {
    private com.kamelia.webservice.dto.Comment comment;

    private long date;

    private java.lang.String orderer;

    private com.kamelia.webservice.dto.ReturnState state;

    public Order() {
    }

    public Order(
           com.kamelia.webservice.dto.Comment comment,
           long date,
           java.lang.String orderer,
           com.kamelia.webservice.dto.ReturnState state) {
           this.comment = comment;
           this.date = date;
           this.orderer = orderer;
           this.state = state;
    }


    /**
     * Gets the comment value for this Order.
     * 
     * @return comment
     */
    public com.kamelia.webservice.dto.Comment getComment() {
        return comment;
    }


    /**
     * Sets the comment value for this Order.
     * 
     * @param comment
     */
    public void setComment(com.kamelia.webservice.dto.Comment comment) {
        this.comment = comment;
    }


    /**
     * Gets the date value for this Order.
     * 
     * @return date
     */
    public long getDate() {
        return date;
    }


    /**
     * Sets the date value for this Order.
     * 
     * @param date
     */
    public void setDate(long date) {
        this.date = date;
    }


    /**
     * Gets the orderer value for this Order.
     * 
     * @return orderer
     */
    public java.lang.String getOrderer() {
        return orderer;
    }


    /**
     * Sets the orderer value for this Order.
     * 
     * @param orderer
     */
    public void setOrderer(java.lang.String orderer) {
        this.orderer = orderer;
    }


    /**
     * Gets the state value for this Order.
     * 
     * @return state
     */
    public com.kamelia.webservice.dto.ReturnState getState() {
        return state;
    }


    /**
     * Sets the state value for this Order.
     * 
     * @param state
     */
    public void setState(com.kamelia.webservice.dto.ReturnState state) {
        this.state = state;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Order)) return false;
        Order other = (Order) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.comment==null && other.getComment()==null) || 
             (this.comment!=null &&
              this.comment.equals(other.getComment()))) &&
            this.date == other.getDate() &&
            ((this.orderer==null && other.getOrderer()==null) || 
             (this.orderer!=null &&
              this.orderer.equals(other.getOrderer()))) &&
            ((this.state==null && other.getState()==null) || 
             (this.state!=null &&
              this.state.equals(other.getState())));
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
        if (getComment() != null) {
            _hashCode += getComment().hashCode();
        }
        _hashCode += new Long(getDate()).hashCode();
        if (getOrderer() != null) {
            _hashCode += getOrderer().hashCode();
        }
        if (getState() != null) {
            _hashCode += getState().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Order.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dto.webservice.kamelia.com", "Order"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comment");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dto.webservice.kamelia.com", "comment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dto.webservice.kamelia.com", "Comment"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("date");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dto.webservice.kamelia.com", "date"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderer");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dto.webservice.kamelia.com", "orderer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dto.webservice.kamelia.com", "state"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dto.webservice.kamelia.com", "ReturnState"));
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
