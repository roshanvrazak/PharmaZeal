package com.pharmazeal;

import java.sql.Date;

public class SalesData {
	Integer salesSessionId;
	Integer salesDrugId;
	String salesDrugName;
	Date salesExpiryDate;
	Double salesPrice;
	Integer salesQty;
	Double salesTotal;
	public SalesData(Integer salesSessionId, Integer salesDrugId, String salesDrugName, Date salesExpiryDate,
			Integer salesQty, Double salesPrice, Double salesTotal) {
		super();
		this.salesSessionId = salesSessionId;
		this.salesDrugId = salesDrugId;
		this.salesDrugName = salesDrugName;
		this.salesExpiryDate = salesExpiryDate;
		this.salesQty = salesQty;
		this.salesPrice = salesPrice;
		this.salesTotal = salesTotal;
	}
	public Integer getSalesSessionId() {
		return salesSessionId;
	}
	public Integer getSalesDrugId() {
		return salesDrugId;
	}
	public String getSalesDrugName() {
		return salesDrugName;
	}
	public Date getSalesExpiryDate() {
		return salesExpiryDate;
	}
	public Integer getSalesQty() {
		return salesQty;
	}
	public Double getSalesPrice() {
		return salesPrice;
	}
	public Double getSalesTotal() {
		return salesTotal;
	}
	
	
	

}
