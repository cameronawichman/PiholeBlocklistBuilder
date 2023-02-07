package com.scrape;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import com.scrape.data.PokemonProduct;

/*
 * Tutorial Referenced:
 * https://www.zenrows.com/blog/web-scraping-java#parallel-web-scraping-in-java
 * 
 */

public class Test {

    public static final String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64; rv:109.0) Gecko/20100101 Firefox/109.0";
    public static final String DATA_PATH = "src/main/java/com/scrape/data/";

    public static void main(String[] args) {
        //initialize document
        Document doc;

        try {
            //fetch target
            doc = Jsoup.connect("https://scrapeme.live/shop").userAgent(USER_AGENT).get();
            
            //scraped product list
            List<PokemonProduct> pkmnProducts = new ArrayList<>();

            //fetch products
            Elements products = doc.select("li.product");
            
            //iteration
            for(Element product : products) {
                PokemonProduct pkmnProduct = new PokemonProduct();
                
                //extract data
                pkmnProduct.setUrl(product.selectFirst("a").attr("href"));
                pkmnProduct.setImage(product.selectFirst("img").attr("src"));
                pkmnProduct.setName(product.selectFirst("h2").text());
                pkmnProduct.setPrice(product.selectFirst("span").text());

                //add scraped product
                pkmnProducts.add(pkmnProduct);
            }
            //export scrape list to JSON format
            exportList(pkmnProducts);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void exportList(List<PokemonProduct> list) throws FileNotFoundException {
        File export = new File(DATA_PATH + "pkmnProducts.json");
        for(int i = 0; export.isFile(); i++) {
            File newExport = new File(DATA_PATH + "pkmnProducts (" + i + ").json");
            export = newExport;
        }
        PrintWriter out = new PrintWriter(export);
        out.println(list.toString());
        out.close();
    }
}


