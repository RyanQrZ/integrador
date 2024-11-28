package com.integrador.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraper {
	
	static String url = "https://www.scrapingcourse.com/ecommerce";
	
	static Document doc;
	
	static String getList()
	{
		 
		try { 
			// fetching the target website 
			doc = Jsoup.connect( url ).get(); 
		} catch (IOException e) { 
			throw new RuntimeException(e); 
		}


		// initializing the list of Java object to store
		// the scraped data
		List<Product> products = new ArrayList<>();

		// retrieving the list of product HTML elements
		Elements productElements = doc.select("li.product");

		// iterating over the list of HTML products
		for(Element productElement:productElements)
		{
			Product product = new Product();

			// extracting the data of interest from the product HTML element
			// and storing it in product
			product.setUrl(productElement.selectFirst("a").attr("href"));
			product.setImage(productElement.selectFirst("img").attr("src"));
			product.setName(productElement.selectFirst("h2").text());
			product.setPrice(productElement.selectFirst("span").text());

			// adding product to the list of the scraped products
			products.add(product);
		}
		
		return products.toString();
	}

}
