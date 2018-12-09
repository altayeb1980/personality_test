package com.sparknetworks.html;

public class HtmlTag {

	private final String tag;

	public HtmlTag(final String tag) {
		this.tag = tag;
	}

	public String getTag() {
		return tag;
	}

	@Override
	public String toString() {
		return "HtmlTag [tag=" + tag + "]";
	}

}
