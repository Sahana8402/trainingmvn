package com.example.demo.controller;

import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.logging.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/product/api1.0")//root of resource/controller

public class ProductController {
	
	Logger logger=Logger.getLogger("ProductController.class");
	@Autowired
	ProductService service;
	ArrayList<Product>prodlist=new ArrayList<>();
	/*{
	prodlist.add(new Product(101,"Nike","feather walk",10,15000));
	prodlist.add(new Product(102,"Adidas","comfort walk",30,13000));
	prodlist.add(new Product(103,"puma","firm grip",12,12000));
	}*/
	
	@GetMapping(value="/product/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> getById(@PathVariable long id){
		Optional<Product>optional=service.getProductId(id);
		return ResponseEntity.status(HttpStatus.OK).body(optional.get());
		//return null;
	}
	
	@GetMapping(value="/productsinfo",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> displayprods(){
		return ResponseEntity.ok(prodlist);
		//return null;
	}
	
	@GetMapping(value="/prod/{brand}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> getbybrand(@PathVariable String brand){
		//Optional<Product> opt=prodlist.stream().filter(product->product.getBrand().equals(brand)).findFirst();
		//return ResponseEntity.status(HttpStatus.OK).body(opt.get());
		return null;
	}
	
	@PostMapping(value="/addprod",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> addprod(@RequestBody Product product){
		
		logger.info("Entered POST method "+ product);
		Product savedProduct=service.addProduct(product);
		logger.info("Persisted product "+ savedProduct);
		return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(savedProduct);
		//return null;
		/*if(product!=null)
			prodlist.add(product);
		return ResponseEntity.status(HttpStatus.OK).body(product);*/
	}
	
	@PutMapping(value="/modify",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> updateprod(@RequestBody Product product){
		return null;
		/*Optional<Product> optional=prodlist.stream().filter(p->p.getProductId()==product.getProductId()).findFirst();
		Product temp=optional.get();
		temp.setBrand(product.getBrand());
		temp.setDescription(product.getDescription());
		temp.setQty(product.getQty());
		temp.setPrice(product.getPrice());
		
		int index=prodlist.indexOf(temp);
		prodlist.remove(index);
		prodlist.add(index,temp);
	
		return null;
		/*long count=prodlist.stream().filter(p->p.getProductId()==product.getProductId()).count();
		 if(count==1)
		prodlist.add(product);
		return ResponseEntity.accepted().body(null);
		*/
	}
	
	@DeleteMapping(value="/delete/{id}")
	public void removeprod(@PathVariable long id) {
		//Optional<Product> opt=prodlist.stream().filter(p->p.getProductId()==id).findFirst();
		//prodlist.remove(opt.get());
		}
 

	}
	
	


	
	

