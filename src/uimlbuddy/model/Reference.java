package uimlbuddy.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attGroup ref="{}attlist.reference"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "reference")
public class Reference {

    @XmlAttribute(name = "constant-name")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String constantName;
    @XmlAttribute(name = "url-name")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String urlName;

    /**
     * Gets the value of the constantName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConstantName() {
        return constantName;
    }

    /**
     * Sets the value of the constantName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConstantName(String value) {
        this.constantName = value;
    }

    /**
     * Gets the value of the urlName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlName() {
        return urlName;
    }

    /**
     * Sets the value of the urlName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlName(String value) {
        this.urlName = value;
    }

}
