package edu.cqu.fly.erawler.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="data")
public class OutputData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column(name="dataID")
	int dataID;
	
	@Column(name="productInnerId")
	int productInnerId;
	
	@Column(name="TaskDataID")
	int taskDataID;
	
	@Column(name="productActualID")
	String productActualID;
	
	@Column(name="productURL")
	String productURL;
	
	@Column(name="productName")
	String productName;
	
	@Column(name="productDescription")
	String productDescription;
	
	@Column(name="shelveDate")
	String shelveDate;
	
	@Column(name="weight")
	String weight;
	
	@Column(name="origin")
	String origin;
	
	@Column(name="province")
	String province;
	
	@Column(name="city")
	String city;
	
	@Column(name="category")	
	String category;
	
	@Column(name="specialtyCategory")
	String specialtyCategory;
	
	@Column(name="brand")
	String brand;
	
	@Column(name="factoryName")
	String factoryName;
	
	@Column(name="factoryAddress")
	String factoryAddress;
	
	@Column(name="series")
	String series;
	
	@Column(name="specification")
	String specification;
	
	@Column(name="deliveryStartArea")
	String deliveryStartArea;
	
	@Column(name="productPrice")
	String productPrice;
	
	@Column(name="productPromPrice")
	String productPromPrice;
	
	@Column(name="monthSaleCount")
	String monthSaleCount;
	
	@Column(name="commentCount")
	String commentCount;
	
	@Column(name="storeActualID")
	String storeActualID;
	
	@Column(name="storeName")
	String storeName;
	
	@Column(name="storeURL")
	String storeURL;
	
	@Column(name="shopkeeper")
	String shopkeeper;
	
	@Column(name="companyName")
	String companyName;
	
	@Column(name="storeLocation")
	String storeLocation;	
	
	@Column(name="goodCommentCount")
	String goodCommentCount;
	
	@Column(name="midCommentCount")
	String midCommentCount;
	
	@Column(name="badCommentCount")
	String badCommentCount;
	
	@Column(name="goodCommentRate")
	String goodCommentRate;
	
	@Column(name="pictureCommentCount")
	String pictureCommentCount;
	
	@Column(name="expirationDay")
	String expirationDay;
	
	@Column(name="produceDay")
	String produceDay;
	
	@Column(name="productPromotionID")
	String productPromotionID;
	
	@Column(name="productCompleteID")
	String productCompleteID;
	
	@Column(name="category1")
	String category1;
	
	@Column(name="category2")
	String category2;
	
	@Column(name="category3")
	String category3;
	
	@Column(name="category4")
	String category4;
	
	@Column(name="SaleCount")
	String SaleCount;
	

	@Column(name="taxRate")
	String taxRate;
	
	@Column(name="productHighVipPrice")
	String productHighVipPrice;
	
	@Column(name="productLowVipPrice")
	String productLowVipPrice;
	
	@Column(name="productTax")
	String productTax;

	
	@Column(name="keyword")
	String keyword;
	
	@Column(name="website")
	String website;
	
	@Column(name="extractTime")
	Date extractTime;
	
	@Column(name="errorInfo")
	String errorInfo;

	public int getProductInnerId() {
		return productInnerId;
	}

	public void setProductInnerId(int productInnerId) {
		this.productInnerId = productInnerId;
	}

	public int getTaskDataID() {
		return taskDataID;
	}

	public void setTaskDataID(int taskDataID) {
		this.taskDataID = taskDataID;
	}

	public String getProductActualID() {
		return productActualID;
	}

	public void setProductActualID(String productActualID) {
		this.productActualID = productActualID;
	}

	public String getProductURL() {
		return productURL;
	}

	public void setProductURL(String productURL) {
		this.productURL = productURL;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getShelveDate() {
		return shelveDate;
	}

	public void setShelveDate(String shelveDate) {
		this.shelveDate = shelveDate;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSpecialtyCategory() {
		return specialtyCategory;
	}

	public void setSpecialtyCategory(String specialtyCategory) {
		this.specialtyCategory = specialtyCategory;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getFactoryAddress() {
		return factoryAddress;
	}

	public void setFactoryAddress(String factoryAddress) {
		this.factoryAddress = factoryAddress;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getDeliveryStartArea() {
		return deliveryStartArea;
	}

	public void setDeliveryStartArea(String deliveryStartArea) {
		this.deliveryStartArea = deliveryStartArea;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductPromPrice() {
		return productPromPrice;
	}

	public void setProductPromPrice(String productPromPrice) {
		this.productPromPrice = productPromPrice;
	}

	public String getMonthSaleCount() {
		return monthSaleCount;
	}

	public void setMonthSaleCount(String monthSaleCount) {
		this.monthSaleCount = monthSaleCount;
	}

	public String getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}

	public String getStoreActualID() {
		return storeActualID;
	}

	public void setStoreActualID(String storeActualID) {
		this.storeActualID = storeActualID;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreURL() {
		return storeURL;
	}

	public void setStoreURL(String storeURL) {
		this.storeURL = storeURL;
	}

	public String getShopkeeper() {
		return shopkeeper;
	}

	public void setShopkeeper(String shopkeeper) {
		this.shopkeeper = shopkeeper;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getStoreLocation() {
		return storeLocation;
	}

	public void setStoreLocation(String storeLocation) {
		this.storeLocation = storeLocation;
	}

	public String getGoodCommentCount() {
		return goodCommentCount;
	}

	public void setGoodCommentCount(String goodCommentCount) {
		this.goodCommentCount = goodCommentCount;
	}

	public String getMidCommentCount() {
		return midCommentCount;
	}

	public void setMidCommentCount(String midCommentCount) {
		this.midCommentCount = midCommentCount;
	}

	public String getBadCommentCount() {
		return badCommentCount;
	}

	public void setBadCommentCount(String badCommentCount) {
		this.badCommentCount = badCommentCount;
	}

	public String getGoodCommentRate() {
		return goodCommentRate;
	}

	public void setGoodCommentRate(String goodCommentRate) {
		this.goodCommentRate = goodCommentRate;
	}

	public String getPictureCommentCount() {
		return pictureCommentCount;
	}

	public void setPictureCommentCount(String pictureCommentCount) {
		this.pictureCommentCount = pictureCommentCount;
	}

	public String getExpirationDay() {
		return expirationDay;
	}

	public void setExpirationDay(String expirationDay) {
		this.expirationDay = expirationDay;
	}

	public String getProduceDay() {
		return produceDay;
	}

	public void setProduceDay(String produceDay) {
		this.produceDay = produceDay;
	}

	public String getProductPromotionID() {
		return productPromotionID;
	}

	public void setProductPromotionID(String productPromotionID) {
		this.productPromotionID = productPromotionID;
	}

	public String getProductCompleteID() {
		return productCompleteID;
	}

	public void setProductCompleteID(String productCompleteID) {
		this.productCompleteID = productCompleteID;
	}

	public String getCategory1() {
		return category1;
	}

	public void setCategory1(String category1) {
		this.category1 = category1;
	}

	public String getCategory2() {
		return category2;
	}

	public void setCategory2(String category2) {
		this.category2 = category2;
	}

	public String getCategory3() {
		return category3;
	}

	public void setCategory3(String category3) {
		this.category3 = category3;
	}

	public String getCategory4() {
		return category4;
	}

	public void setCategory4(String category4) {
		this.category4 = category4;
	}

	public String getSaleCount() {
		return SaleCount;
	}

	public void setSaleCount(String saleCount) {
		SaleCount = saleCount;
	}

	public String getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	public String getProductHighVipPrice() {
		return productHighVipPrice;
	}

	public void setProductHighVipPrice(String productHighVipPrice) {
		this.productHighVipPrice = productHighVipPrice;
	}

	public String getProductLowVipPrice() {
		return productLowVipPrice;
	}

	public void setProductLowVipPrice(String productLowVipPrice) {
		this.productLowVipPrice = productLowVipPrice;
	}

	public String getProductTax() {
		return productTax;
	}

	public void setProductTax(String productTax) {
		this.productTax = productTax;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Date getExtractTime() {
		return extractTime;
	}

	public void setExtractTime(Date extractTime) {
		this.extractTime = extractTime;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	
	
}
