/**
 * ConvertResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package FxtopAPI;

public class ConvertResult  implements java.io.Serializable {
    private String resultAmount;

    private String c2;

    private String originalAmount;

    private String c1;

    private String DD;

    private String MM;

    private String YYYY;

    private String exchangeRate;

    private String comment;

    public ConvertResult() {
    }

    public ConvertResult(
           String resultAmount,
           String c2,
           String originalAmount,
           String c1,
           String DD,
           String MM,
           String YYYY,
           String exchangeRate,
           String comment) {
           this.resultAmount = resultAmount;
           this.c2 = c2;
           this.originalAmount = originalAmount;
           this.c1 = c1;
           this.DD = DD;
           this.MM = MM;
           this.YYYY = YYYY;
           this.exchangeRate = exchangeRate;
           this.comment = comment;
    }


    /**
     * Gets the resultAmount value for this ConvertResult.
     * 
     * @return resultAmount
     */
    public String getResultAmount() {
        return resultAmount;
    }


    /**
     * Sets the resultAmount value for this ConvertResult.
     * 
     * @param resultAmount
     */
    public void setResultAmount(String resultAmount) {
        this.resultAmount = resultAmount;
    }


    /**
     * Gets the c2 value for this ConvertResult.
     * 
     * @return c2
     */
    public String getC2() {
        return c2;
    }


    /**
     * Sets the c2 value for this ConvertResult.
     * 
     * @param c2
     */
    public void setC2(String c2) {
        this.c2 = c2;
    }


    /**
     * Gets the originalAmount value for this ConvertResult.
     * 
     * @return originalAmount
     */
    public String getOriginalAmount() {
        return originalAmount;
    }


    /**
     * Sets the originalAmount value for this ConvertResult.
     * 
     * @param originalAmount
     */
    public void setOriginalAmount(String originalAmount) {
        this.originalAmount = originalAmount;
    }


    /**
     * Gets the c1 value for this ConvertResult.
     * 
     * @return c1
     */
    public String getC1() {
        return c1;
    }


    /**
     * Sets the c1 value for this ConvertResult.
     * 
     * @param c1
     */
    public void setC1(String c1) {
        this.c1 = c1;
    }


    /**
     * Gets the DD value for this ConvertResult.
     * 
     * @return DD
     */
    public String getDD() {
        return DD;
    }


    /**
     * Sets the DD value for this ConvertResult.
     * 
     * @param DD
     */
    public void setDD(String DD) {
        this.DD = DD;
    }


    /**
     * Gets the MM value for this ConvertResult.
     * 
     * @return MM
     */
    public String getMM() {
        return MM;
    }


    /**
     * Sets the MM value for this ConvertResult.
     * 
     * @param MM
     */
    public void setMM(String MM) {
        this.MM = MM;
    }


    /**
     * Gets the YYYY value for this ConvertResult.
     * 
     * @return YYYY
     */
    public String getYYYY() {
        return YYYY;
    }


    /**
     * Sets the YYYY value for this ConvertResult.
     * 
     * @param YYYY
     */
    public void setYYYY(String YYYY) {
        this.YYYY = YYYY;
    }


    /**
     * Gets the exchangeRate value for this ConvertResult.
     * 
     * @return exchangeRate
     */
    public String getExchangeRate() {
        return exchangeRate;
    }


    /**
     * Sets the exchangeRate value for this ConvertResult.
     * 
     * @param exchangeRate
     */
    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }


    /**
     * Gets the comment value for this ConvertResult.
     * 
     * @return comment
     */
    public String getComment() {
        return comment;
    }


    /**
     * Sets the comment value for this ConvertResult.
     * 
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ConvertResult)) return false;
        ConvertResult other = (ConvertResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultAmount==null && other.getResultAmount()==null) || 
             (this.resultAmount!=null &&
              this.resultAmount.equals(other.getResultAmount()))) &&
            ((this.c2==null && other.getC2()==null) || 
             (this.c2!=null &&
              this.c2.equals(other.getC2()))) &&
            ((this.originalAmount==null && other.getOriginalAmount()==null) || 
             (this.originalAmount!=null &&
              this.originalAmount.equals(other.getOriginalAmount()))) &&
            ((this.c1==null && other.getC1()==null) || 
             (this.c1!=null &&
              this.c1.equals(other.getC1()))) &&
            ((this.DD==null && other.getDD()==null) || 
             (this.DD!=null &&
              this.DD.equals(other.getDD()))) &&
            ((this.MM==null && other.getMM()==null) || 
             (this.MM!=null &&
              this.MM.equals(other.getMM()))) &&
            ((this.YYYY==null && other.getYYYY()==null) || 
             (this.YYYY!=null &&
              this.YYYY.equals(other.getYYYY()))) &&
            ((this.exchangeRate==null && other.getExchangeRate()==null) || 
             (this.exchangeRate!=null &&
              this.exchangeRate.equals(other.getExchangeRate()))) &&
            ((this.comment==null && other.getComment()==null) || 
             (this.comment!=null &&
              this.comment.equals(other.getComment())));
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
        if (getResultAmount() != null) {
            _hashCode += getResultAmount().hashCode();
        }
        if (getC2() != null) {
            _hashCode += getC2().hashCode();
        }
        if (getOriginalAmount() != null) {
            _hashCode += getOriginalAmount().hashCode();
        }
        if (getC1() != null) {
            _hashCode += getC1().hashCode();
        }
        if (getDD() != null) {
            _hashCode += getDD().hashCode();
        }
        if (getMM() != null) {
            _hashCode += getMM().hashCode();
        }
        if (getYYYY() != null) {
            _hashCode += getYYYY().hashCode();
        }
        if (getExchangeRate() != null) {
            _hashCode += getExchangeRate().hashCode();
        }
        if (getComment() != null) {
            _hashCode += getComment().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConvertResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:FxtopAPI", "ConvertResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ResultAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("c2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "C2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("originalAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OriginalAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("c1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "C1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("YYYY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "YYYY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("exchangeRate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ExchangeRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comment");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Comment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
