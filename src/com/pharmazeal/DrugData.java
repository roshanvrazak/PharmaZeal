package com.pharmazeal;

import java.sql.Date;

public class DrugData {

	private Integer drug_id;
	private String drug_name;
	private String conditions;
	private Double unit_price;
	private Date expiry;
	private Integer stock;
	private String idCheck;
	private String stk_avail;
	private String tun_avail;
	private String han_avail;
	private String fen_avail;
	private String long_avail;
	private String image;
	private Double qty;
	private Double total;

	public DrugData(Integer drug_id, String drug_name, Double unit_price, Date expiry, Double qty, Double total) {
		super();
		this.drug_id = drug_id;
		this.drug_name = drug_name;
		this.unit_price = unit_price;
		this.expiry = expiry;
		this.qty = qty;
		this.total = total;
	}

	public DrugData(Integer drug_id, String drug_name, String conditions, Double unit_price, Date expiry,
			Integer stock) {
		this.drug_id = drug_id;
		this.drug_name = drug_name;
		this.conditions = conditions;
		this.unit_price = unit_price;
		this.expiry = expiry;
		this.stock = stock;
	}

	public DrugData(Integer drug_id, String drug_name, String conditions, Double unit_price, Date expiry, Integer stock,
			String idCheck, String stk_avail, String tun_avail, String fen_avail, String han_avail, String long_avail,
			String image) {
		super();
		this.drug_id = drug_id;
		this.drug_name = drug_name;
		this.conditions = conditions;
		this.unit_price = unit_price;
		this.expiry = expiry;
		this.stock = stock;
		this.idCheck = idCheck;
		this.stk_avail = stk_avail;
		this.tun_avail = tun_avail;
		this.fen_avail = fen_avail;
		this.han_avail = han_avail;
		this.long_avail = long_avail;
		this.image = image;
	}

	public String getFen_avail() {
		return fen_avail;
	}

	public Integer getDrug_id() {
		return drug_id;
	}

	public String getDrug_name() {
		return drug_name;
	}

	public String getConditions() {
		return conditions;
	}

	public Double getUnit_price() {
		return unit_price;
	}

	public Date getExpiry() {
		return expiry;
	}

	public Integer getStock() {
		return stock;
	}

	public String getIdCheck() {
		return idCheck;
	}

	public String getStk_avail() {
		return stk_avail;
	}

	public String getTun_avail() {
		return tun_avail;
	}

	public String getHan_avail() {
		return han_avail;
	}

	public String getLong_avail() {
		return long_avail;
	}

	public String getImage() {
		return image;
	}

	public Double getQty() {
		return qty;
	}

	public Double getTotal() {
		return total;
	}
	
}
