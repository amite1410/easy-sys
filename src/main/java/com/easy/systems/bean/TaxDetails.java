package com.easy.systems.bean;

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


@Entity(name = "Tax_Details")
@XmlRootElement
public class TaxDetails {
	
	public TaxDetails(){}
	
	
	@Id
	@Column(name = "TAX_ID", unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "Tax_Name", nullable = false)
	private String taxName;
	@Column(name = "Tax_Percent", nullable = false)
	private BigDecimal taxPercent;
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
	public String getTaxName() {
		return taxName;
	}
	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}
	public BigDecimal getTaxPercent() {
		return taxPercent;
	}
	public void setTaxPercent(BigDecimal taxPercent) {
		this.taxPercent = taxPercent;
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
	@Override
	public String toString() {
		return "TaxDetails [id=" + id + ", taxName=" + taxName + ", taxPercent=" + taxPercent + ", isActive=" + isActive
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + ", deactivateDate=" + deactivateDate
				+ "]";
	}
	
	
	
	
	
}
