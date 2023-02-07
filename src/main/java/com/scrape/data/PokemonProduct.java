package com.scrape.data;

public class PokemonProduct {
    private String url;
    private String image;
    private String name;
    private String price;

    //data output
    @Override
    public String toString() {
		return "\n\t{"
        + "\n\t\t\"url\":\"" + url + "\"," 
				+ "\n\t\t\"image\": \"" + image + "\"," 
				+ "\n\t\t\"name\":\"" + name + "\"," 
				+ "\n\t\t\"price\": \"" + price + "\""
        + "\n\t}"
        + "\n";
    }

    //all the sets
    public void setUrl(String url) {
      this.url = url;
    }

    public void setImage(String image) {
      this.image = image;
    }

    public void setName(String name) {
      this.name = name;
    }

    public void setPrice(String price) {
      this.price = price;
    }
}