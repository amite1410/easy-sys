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

@Entity(name = "Inventoy_Details")
@XmlRootElement
public class ProductInventoryDetails {

	public ProductInventoryDetails() {
	}

	@Id
	@Column(name = "Inventory_ID", unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "Product_Name", nullable = false, unique = true)
	private String productName;
	
	@Column(name = "vendorId", nullable = false)
	private Integer vendorID;

	@Column(name = "Piece_Per_Case", nullable = false)
	private BigDecimal piecePerCase;
	@Column(name = "Total_Case", nullable = false)
	private BigDecimal totalCase;

	@Column(name = "Case_Added", nullable = false)
	private BigDecimal caseToAdd;
	@Column(name = "Loose_Quantity")
	private BigDecimal looseQuantity;
	@Column(name = "Total_Quantity", nullable = false)
	private BigDecimal totalQuantity;
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

	public BigDecimal getPiecePerCase() {
		return piecePerCase;
	}

	public void setPiecePerCase(BigDecimal piecePerCase) {
		this.piecePerCase = piecePerCase;
	}

	public BigDecimal getTotalCase() {
		return totalCase;
	}

	public void setTotalCase(BigDecimal totalCase) {
		this.totalCase = totalCase;
	}

	public BigDecimal getLooseQuantity() {
		return looseQuantity;
	}

	public void setLooseQuantity(BigDecimal looseQuantity) {
		this.looseQuantity = looseQuantity;
	}

	public BigDecimal getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(BigDecimal totalQuantity) {
		this.totalQuantity = totalQuantity;
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

	public BigDecimal getCaseToAdd() {
		return caseToAdd;
	}

	public void setCaseToAdd(BigDecimal caseToAdd) {
		this.caseToAdd = caseToAdd;
	}

	public Integer getVendorID() {
		return vendorID;
	}

	public void setVendorID(Integer vendorID) {
		this.vendorID = vendorID;
	}

	@Override
	public String toString() {
		return "ProductInventoryDetails [id=" + id + ", productName=" + productName + ", piecePerCase=" + piecePerCase
				+ ", totalCase=" + totalCase + ", looseQuantity=" + looseQuantity + ", totalQuantity=" + totalQuantity
				+ ", isActive=" + isActive + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ ", deactivateDate=" + deactivateDate + "]";
	}

}
