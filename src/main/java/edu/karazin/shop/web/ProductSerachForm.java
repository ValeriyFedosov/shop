package edu.karazin.shop.web;

public class ProductSerachForm {

	private String searchText;

	public ProductSerachForm() {
	}

	public ProductSerachForm(String searchText) {
		this.searchText = searchText;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
}
