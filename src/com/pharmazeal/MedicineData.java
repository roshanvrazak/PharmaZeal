package com.pharmazeal;

import java.sql.Date;

public class MedicineData {

	private Integer medicineId;
	private String brand;
	private String productName;
	private String type;
	private String stat;
	private Double price;
	private Date date;
	private String image;

	public MedicineData(Integer medicineId, String brand, String productName, String type, String status, Double price,
			String image,Date date) {
		this.medicineId = medicineId;
		this.brand = brand;
		this.productName = productName;
		this.type = type;
		this.stat = status;
		this.price = price;
		this.date = date;
		this.image = image;
	}

	public Integer getMedicineId() {
		return medicineId;
	}

	public String getBrand() {
		return brand;
	}

	public String getProductName() {
		return productName;
	}

	public String getType() {
		return type;
	}

	public String getstatus() {
		return stat;
	}

	public String getImage() {
		return image;
	}

	public Double getPrice() {
		return price;
	}

	public Date getDate() {
		return date;
	}

}
