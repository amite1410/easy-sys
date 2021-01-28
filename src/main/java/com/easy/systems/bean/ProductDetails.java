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

@Entity(name = "Product_Details")
@XmlRootElement
public class ProductDetails {
	@Id
	@Column(name = "Product_ID", unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "Product_Name", nullable = false)
	private String productName;	 
	@Column(name = "Product_Code", nullable = false, unique=true)
	private String productCode;
	@Column(name = "Vendor_ID", nullable = false) 
	private Integer vendorID;
	@Column(name = "Cost_price", nullable = false)
	private BigDecimal costPrice;
	@Column(name = "Sell_Price", nullable = false)
	private BigDecimal sellPrice;
	@Column(name = "Tax_Rate", nullable = false)
	private BigDecimal taxPecent;
	@Column(name = "Actual_Sell_Price", nullable = false)
	private BigDecimal actualSellPrice;
	@Column(name = "MRP", nullable = false)
	private BigDecimal mrp;
	@Column(name = "Piece_Per_Case", nullable = false)
	private BigDecimal piecePerCase;
	@Column(name = "HSN_Code")
	private String hsnCode;
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public Integer getVendorID() {
		return vendorID;
	}
	public void setVendorID(Integer vendorID) {
		this.vendorID = vendorID;
	}
	public BigDecimal getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}
	public BigDecimal getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}
	public BigDecimal getTaxPecent() {
		return taxPecent;
	}
	public void setTaxPecent(BigDecimal taxPecent) {
		this.taxPecent = taxPecent;
	}
	public BigDecimal getActualSellPrice() {
		return actualSellPrice;
	}
	public void setActualSellPrice(BigDecimal actualSellPrice) {
		this.actualSellPrice = actualSellPrice;
	}
	public BigDecimal getMrp() {
		return mrp;
	}
	public void setMrp(BigDecimal mrp) {
		this.mrp = mrp;
	}
	public BigDecimal getPiecePerCase() {
		return piecePerCase;
	}
	public void setPiecePerCase(BigDecimal piecePerCase) {
		this.piecePerCase = piecePerCase;
	}
	public String getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
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
		return "ProductDetails [id=" + id + ", productName=" + productName + ", productCode=" + productCode
				+ ", vendorID=" + vendorID + ", costPrice=" + costPrice + ", sellPrice=" + sellPrice + ", taxPecent="
				+ taxPecent + ", actualSellPrice=" + actualSellPrice + ", mrp=" + mrp + ", piecePerCase=" + piecePerCase
				+ ", isActive=" + isActive + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ ", deactivateDate=" + deactivateDate + "]";
	}

	
	
}
