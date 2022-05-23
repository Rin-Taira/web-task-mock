package entity;

import java.sql.Timestamp;

public class Product {

    private int productId;
    private String category;
    private String name;
    private int price;
    private String description;
    private Timestamp createdDate;
    private Timestamp updatedDate;

    public Product() {
    }

    public Product(int productId, String category, String name, int price, String description, Timestamp createdDate, Timestamp updatedDate) {
    	this.productId = productId;
        this.category = category;
        this.name = name;
        this.price = price;
        this.description = description;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

}