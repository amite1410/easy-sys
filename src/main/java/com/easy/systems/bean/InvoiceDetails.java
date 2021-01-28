package com.easy.systems.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table (name="Invoice_Details")
@XmlRootElement
public class InvoiceDetails implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -517893552530055014L;
	@Id
	@Column(name = "Invoice_ID", unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;	
	@Column(name = "Invoice_Code", unique = true, updatable=false, nullable=false)
	private String invoiceCode;
	@Column(name = "Customer_Name",nullable=false)
	private  String customerName;
	@Column(name = "Route_ID",nullable=false)
	private  Integer routeId;
	@Column(name = "Vendor_Name")
	private  String vendor_Name;
	@Column(name = "Number_Of_Product",nullable=false)
	private  int numberOfProduct;
	@Column(name = "Number_Of_Piece",nullable=false)
	private  long numberOfPiece;
	@Column(name = "Total_Price",nullable=false)
	private  BigDecimal totalPrice;	
	@Column(name = "other_discount_Percent")
	private  BigDecimal otherDiscountPercent;
	@Column(name = "Total_discount")
	private  BigDecimal totalDiscount;
	@Column(name = "Tax_Collected",nullable=false)
	private  BigDecimal taxCollected;
	@Column(name = "Delivery_Charge")
	private  BigDecimal deliveyCharge;
	@Column(name = "Total_Payable_Amount", nullable=false)
	private  BigDecimal totalPayableAmount;
	@Column(name = "Order_By",nullable=false)
	private String orderBy;
	@Column(name = "Invoice_By")
	private String invoiceBy;
	@Column(name = "Delivery_Status")
	private String deliveryStatus;
	@Column(name = "Is_Active", nullable=false)
	private String isActive;
	@Temporal(TemporalType.DATE)
	@Column(name = "Create_Date", nullable = false)
	private Date createDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "Delivery_Date")
	private Date deliveryDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "Update_Date")
	private Date updateDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "Deactivate_Date")
	private Date deactivateDate;

	 	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getRouteId() {
		return routeId;
	}
	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}
	public String getVendor_Name() {
		return vendor_Name;
	}
	public void setVendor_Name(String vendor_Name) {
		this.vendor_Name = vendor_Name;
	}
	public int getNumberOfProduct() {
		return numberOfProduct;
	}
	public void setNumberOfProduct(int numberOfProduct) {
		this.numberOfProduct = numberOfProduct;
	}
	public long getNumberOfPiece() {
		return numberOfPiece;
	}
	public void setNumberOfPiece(long numberOfPiece) {
		this.numberOfPiece = numberOfPiece;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public BigDecimal getOtherDiscountPercent() {
		return otherDiscountPercent;
	}
	public void setOtherDiscountPercent(BigDecimal otherDiscountPercent) {
		this.otherDiscountPercent = otherDiscountPercent;
	}
	public BigDecimal getTotalDiscount() {
		return totalDiscount;
	}
	public void setTotalDiscount(BigDecimal totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	public BigDecimal getTaxCollected() {
		return taxCollected;
	}
	public void setTaxCollected(BigDecimal taxCollected) {
		this.taxCollected = taxCollected;
	}
	public BigDecimal getDeliveyCharge() {
		return deliveyCharge;
	}
	public void setDeliveyCharge(BigDecimal deliveyCharge) {
		this.deliveyCharge = deliveyCharge;
	}
	public BigDecimal getTotalPayableAmount() {
		return totalPayableAmount;
	}
	public void setTotalPayableAmount(BigDecimal totalPayableAmount) {
		this.totalPayableAmount = totalPayableAmount;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getInvoiceBy() {
		return invoiceBy;
	}
	public void setInvoiceBy(String invoiceBy) {
		this.invoiceBy = invoiceBy;
	}
	public String getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
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
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
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
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}
	
	
	@Override
	public String toString() {
		return "InvoiceDetails [id=" + id + ", invoiceCode=" + invoiceCode + ", customerName=" + customerName
				+ ", routeId=" + routeId + ", vendor_Name=" + vendor_Name + ", numberOfProduct=" + numberOfProduct
				+ ", numberOfPiece=" + numberOfPiece + ", totalPrice=" + totalPrice + ", otherDiscountPercent="
				+ otherDiscountPercent + ", totalDiscount=" + totalDiscount + ", taxCollected=" + taxCollected
				+ ", deliveyCharge=" + deliveyCharge + ", totalPayableAmount=" + totalPayableAmount + ", orderBy="
				+ orderBy + ", invoiceBy=" + invoiceBy + ", deliveryStatus=" + deliveryStatus + ", isActive=" + isActive
				+ ", createDate=" + createDate + ", deliveryDate=" + deliveryDate + ", updateDate=" + updateDate
				+ ", deactivateDate=" + deactivateDate + "]";
	}
	
	
	

}
