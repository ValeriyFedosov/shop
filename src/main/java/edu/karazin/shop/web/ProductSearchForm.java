package edu.karazin.shop.web;

public class ProductSearchForm {

	private String searchText;

	public ProductSearchForm() {
	}

	public ProductSearchForm(String searchText) {
		this.searchText = searchText;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
}
