package com.ssafy.triends.domain.attraction.model;

public class AttractionDto {
	private int contentId;
	private int contentTypeId;
	private String title;
	private String addr1;
	private String addr2;
	private String zipCode;
	private String firstImage;
	private String firstImage2;
	private int readCount;
	private int sidoCode;
	private int gugunCode;
	private double latitude;
	private double longitude;
	private String mlevel;

	public AttractionDto() { }
	
	public AttractionDto(int contentId, int contentTypeId, String title, String addr1, String addr2, String zipCode,
			String firstImage, String firstImage2, int readCount, int sidoCode, int gugunCode, double latitude,
			double longitude, String mlevel) {
		super();
		this.contentId = contentId;
		this.contentTypeId = contentTypeId;
		this.title = title;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.zipCode = zipCode;
		this.firstImage = firstImage;
		this.firstImage2 = firstImage2;
		this.readCount = readCount;
		this.sidoCode = sidoCode;
		this.gugunCode = gugunCode;
		this.latitude = latitude; // mapy
		this.longitude = longitude; // mapx
		this.mlevel = mlevel;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public int getContentTypeId() {
		return contentTypeId;
	}

	public void setContentTypeId(int contentTypeId) {
		this.contentTypeId = contentTypeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getFirstImage() {
		return firstImage;
	}

	public void setFirstImage(String firstImage) {
		this.firstImage = firstImage;
	}

	public String getFirstImage2() {
		return firstImage2;
	}

	public void setFirstImage2(String firstImage2) {
		this.firstImage2 = firstImage2;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public int getSidoCode() {
		return sidoCode;
	}

	public void setSidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}

	public int getGugunCode() {
		return gugunCode;
	}

	public void setGugunCode(int gugunCode) {
		this.gugunCode = gugunCode;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getMlevel() {
		return mlevel;
	}

	public void setMlevel(String mlevel) {
		this.mlevel = mlevel;
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder("{ ");
		sb.append("\"contentId\" : \"").append(contentId).append("\", \"contentTypeId\" : \"").append(contentTypeId)
		.append("\", \"title\" : \"").append(title).append("\", \"addr1\" : \"").append(addr1).append("\", \"addr2\" : \"").append(addr2)
		.append("\", \"zipCode\" : \"").append(zipCode).append("\", \"firstImage\" : \"").append(firstImage)
		.append("\", \"firstImage2\" : \"").append(firstImage2).append("\", \"readCount\" : \"").append(readCount)
		.append("\", \"sidoCode\" : \"").append(sidoCode).append("\", \"gugunCode\" : \"").append(gugunCode)
		.append("\", \"latitude\" : \"").append(latitude).append("\", \"longitude\" : \"").append(longitude)
		.append("\", \"mlevel\" : \"").append(mlevel).append("\" }");
		
		return sb.toString();
	}
}
