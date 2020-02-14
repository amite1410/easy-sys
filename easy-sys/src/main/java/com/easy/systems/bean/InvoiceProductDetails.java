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
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name="Invoice_Product_Details")
@XmlRootElement
public class InvoiceProductDetails implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7746154882912123870L;
	@Id
	@Column(name = "ID", unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "Invoice_Code",nullable=false)
	private String invoiceCode;
	@Column(name = "Product_Code", nullable = false)
	private String productCode;
	@Column(name = "HSN_CODE", nullable = false)
	private String hsnCode;
	@Column(name = "Tax_Rate", nullable = false)
	private BigDecimal taxRate;
	@Column(name = "Unit_price", nullable = false)
	private BigDecimal unitPrice;
	@Column(name = "Case_Sold",nullable=false)
	private int caseSold;
	@Column(name = "Unit_Sold",nullable=false)
	private long unitSold;
	@Column(name = "Loose_Quantity",nullable=false)
	private long looseQuantity;
	@Column(name = "Discount_Name")
	private String discountName;
	@Column(name = "Discount")
	private BigDecimal discount;
	@Column(name = "Discounted_piece")
	private long discountedPiece;
	@Column(name = "Discount_Amt")
	private BigDecimal discountAmt;
	@Column(name = "sub_tax", nullable = false)
	private BigDecimal subTax;
	@Column(name = "Sub_Total", nullable = false)
	private BigDecimal subTotal;
	@Temporal(TemporalType.DATE)
	@Column(name = "Create_Date", nullable = false)
	private Date createDate;
	

	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}
	public BigDecimal getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getCaseSold() {
		return caseSold;
	}
	public void setCaseSold(int caseSold) {
		this.caseSold = caseSold;
	}
	public long getUnitSold() {
		return unitSold;
	}
	public void setUnitSold(long unitSold) {
		this.unitSold = unitSold;
	}
	public long getLooseQuantity() {
		return looseQuantity;
	}
	public void setLooseQuantity(long looseQuantity) {
		this.looseQuantity = looseQuantity;
	}
	public String getDiscountName() {
		return discountName;
	}
	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public long getDiscountedPiece() {
		return discountedPiece;
	}
	public void setDiscountedPiece(long discountedPiece) {
		this.discountedPiece = discountedPiece;
	}
	public BigDecimal getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	public BigDecimal getDiscountAmt() {
		return discountAmt;
	}
	public void setDiscountAmt(BigDecimal discountAmt) {
		this.discountAmt = discountAmt;
	}
	
	
	
	public BigDecimal getSubTax() {
		return subTax;
	}
	public void setSubTax(BigDecimal subTax) {
		this.subTax = subTax;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "InvoiceProductDetails [id=" + id + ", invoiceCode=" + invoiceCode + ", productCode=" + productCode
				 + ", taxRate=" + taxRate + ", unitPrice=" + unitPrice + ", unitSold="
				+ unitSold + ", discountName=" + discountName + ", discount=" + discount + ", discountedPiece="
				+ discountedPiece + ", subTotal=" + subTotal + "]";
	}
	
}
