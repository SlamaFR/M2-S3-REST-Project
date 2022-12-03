/**
 * Bike.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kamelia.webservice.dto;

public class Bike  implements java.io.Serializable {
    private java.lang.String availability;

    private com.kamelia.webservice.dto.Order[] history;

    private java.lang.String id;

    private java.lang.String owner;

    private int value;

    public Bike() {
    }

    public Bike(
           java.lang.String availability,
           com.kamelia.webservice.dto.Order[] history,
           java.lang.String id,
           java.lang.String owner,
           int value) {
           this.availability = availability;
           this.history = history;
           this.id = id;
           this.owner = owner;
           this.value = value;
    }


    /**
     * Gets the availability value for this Bike.
     * 
     * @return availability
     */
    public java.lang.String getAvailability() {
        return availability;
    }


    /**
     * Sets the availability value for this Bike.
     * 
     * @param availability
     */
    public void setAvailability(java.lang.String availability) {
        this.availability = availability;
    }


    /**
     * Gets the history value for this Bike.
     * 
     * @return history
     */
    public com.kamelia.webservice.dto.Order[] getHistory() {
        return history;
    }


    /**
     * Sets the history value for this Bike.
     * 
     * @param history
     */
    public void setHistory(com.kamelia.webservice.dto.Order[] history) {
        this.history = history;
    }


    /**
     * Gets the id value for this Bike.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this Bike.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the owner value for this Bike.
     * 
     * @return owner
     */
    public java.lang.String getOwner() {
        return owner;
    }


    /**
     * Sets the owner value for this Bike.
     * 
     * @param owner
     */
    public void setOwner(java.lang.String owner) {
        this.owner = owner;
    }


    /**
     * Gets the value value for this Bike.
     * 
     * @return value
     */
    public int getValue() {
        return value;
    }


    /**
     * Sets the value value for this Bike.
     * 
     * @param value
     */
    public void setValue(int value) {
        this.value = value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Bike)) return false;
        Bike other = (Bike) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.availability==null && other.getAvailability()==null) || 
             (this.availability!=null &&
              this.availability.equals(other.getAvailability()))) &&
            ((this.history==null && other.getHistory()==null) || 
             (this.history!=null &&
              java.util.Arrays.equals(this.history, other.getHistory()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.owner==null && other.getOwner()==null) || 
             (this.owner!=null &&
              this.owner.equals(other.getOwner()))) &&
            this.value == other.getValue();
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
        if (getAvailability() != null) {
            _hashCode += getAvailability().hashCode();
        }
        if (getHistory() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getHistory());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getHistory(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getOwner() != null) {
            _hashCode += getOwner().hashCode();
        }
        _hashCode += getValue();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Bike.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dto.webservice.kamelia.com", "Bike"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("availability");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dto.webservice.kamelia.com", "availability"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("history");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dto.webservice.kamelia.com", "history"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dto.webservice.kamelia.com", "Order"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://service.webservice.kamelia.com", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dto.webservice.kamelia.com", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("owner");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dto.webservice.kamelia.com", "owner"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("value");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dto.webservice.kamelia.com", "value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
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
