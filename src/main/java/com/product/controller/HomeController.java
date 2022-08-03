package com.product.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.product.entity.Products;
import com.product.repository.ProductRepository;

@Controller
public class HomeController {
	
	@Autowired
	private ProductRepository productRepo;
	
	@GetMapping("/")
	public String home(Model m) {
		List<Products> list = productRepo.findAll();
		m.addAttribute("all_products",list);
		return "index";
	}
	
	@GetMapping("/loadform")
	public String loadForm() {
		return "add";
	}
	
	@GetMapping("/editform/{id}")
	public String editForm(@PathVariable(value = "id")long id, Model m) {
		
		Optional<Products> product= productRepo.findById(id);
		Products pro=product.get();
		m.addAttribute("product",pro);
		
		return "edit";
	}
	
	@PostMapping("/save_products")
	public String saveProduct(@ModelAttribute Products products,HttpSession session) {
		
		productRepo.save(products);
		session.setAttribute("msg", "Product Added Succesfully");
		
		return "redirect:/";
	}
	@PostMapping("/update_products")
	public String updateProduct(@ModelAttribute Products products,HttpSession session) {
		
		productRepo.save(products);
		session.setAttribute("msg", "Product Update Succesfully");
		
		return "redirect:/";
	}
	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(value = "id")long id, HttpSession session) {
		
		productRepo.deleteById(id);
		session.setAttribute("msg", "Product Delete Succesfully");
		
		return "redirect:/";
	}
}
