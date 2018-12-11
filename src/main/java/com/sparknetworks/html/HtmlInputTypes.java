package com.sparknetworks.html;

public enum HtmlInputTypes {

	
	single_choice("radio"), single_choice_conditional("radio"), number_range("number");
	
	private String htmlInputType;

	HtmlInputTypes(String htmlInputType){
		this.htmlInputType = htmlInputType;
	}
	public String getHtmlInputType() {
		return htmlInputType;
	}
	
}
