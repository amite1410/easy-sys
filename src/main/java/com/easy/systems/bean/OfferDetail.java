package com.easy.systems.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "Offer_Details")
@XmlRootElement
public class OfferDetail  implements Serializable{
	
	public OfferDetail(){};
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7734059312259701013L;
	@Id
	@Column(name = "Offer_ID", unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "Offer_Name", nullable = false)
	private String offerName;	 
	@Column(name = "Offer_Code", nullable = false,unique=true)
	private String offerCode;
	@Column(name = "Vendor_ID", nullable = false) 
	private int vendorID;
	@Column(name = "Offer_Percent")
	private BigDecimal offerPercent;
	@Column(name = "Min_Order")
	private BigDecimal minOrder;
	@Column(name = "Offer_Count")
	private BigDecimal offerCount;
	@Column(name = "Is_Active")
	private String isActive;
	@Temporal(TemporalType.DATE)
	@Column(name = "Create_Date", nullable = false)
	private Date createDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "Update_Date")
	private Date updateDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "Deactivate_Date")
	private Date deactivateDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOfferName() {
		return offerName;
	}
	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}
	public String getOfferCode() {
		return offerCode;
	}
	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}
	public int getVendorID() {
		return vendorID;
	}
	public void setVendorID(int vendorID) {
		this.vendorID = vendorID;
	}
	public BigDecimal getOfferPercent() {
		return offerPercent;
	}
	public void setOfferPercent(BigDecimal offerPercent) {
		this.offerPercent = offerPercent;
	}
	public BigDecimal getMinOrder() {
		return minOrder;
	}
	public void setMinOrder(BigDecimal minOrder) {
		this.minOrder = minOrder;
	}
	public BigDecimal getOfferCount() {
		return offerCount;
	}
	public void setOfferCount(BigDecimal offerCount) {
		this.offerCount = offerCount;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Date getDeactivateDate() {
		return deactivateDate;
	}
	public void setDeactivateDate(Date deactivateDate) {
		this.deactivateDate = deactivateDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "OfferDetail [id=" + id + ", offerName=" + offerName + ", offerCode=" + offerCode + ", vendorID="
				+ vendorID + ", offerPercent=" + offerPercent + ", minOrder=" + minOrder + ", offerCount=" + offerCount
				+ ", isActive=" + isActive + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ ", deactivateDate=" + deactivateDate + "]";
	}

	
}
