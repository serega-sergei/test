package com.test.services.data.parameters;

public enum Order {
	
	  ASCENDING("ascending"), 
	  DESCENDING("descending");
	  
	  private String text;

	  Order(String text) {
	    this.text = text;
	  }

	  public String getText() {
	    return this.text;
	  }	
	  
	  public static Order fromString(String text) {
		    if (text != null) {
		      for (Order b : Order.values()) {
		        if (text.equalsIgnoreCase(b.text)) {
		          return b;
		        }
		      }
		    }
		    return null;
	}
}