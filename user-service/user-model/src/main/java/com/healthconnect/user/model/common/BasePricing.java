package com.healthconnect.user.model.common;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import com.healthconnect.user.model.core.BaseEntity;
import com.healthconnect.user.model.enums.PricingUnit;

@MappedSuperclass
public abstract class BasePricing extends BaseEntity {

	@Column(name = "Price")
	private Double price;

	@Column(name = "Unit")
	@Enumerated(EnumType.STRING)
	private PricingUnit unit;

	public PricingUnit getUnit() {
		return unit;
	}

	public void setUnit(PricingUnit unit) {
		this.unit = unit;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}

