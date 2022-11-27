package com.example.demonongsan.dao;

import java.util.List;

import com.example.demonongsan.model.Catalog;
import com.example.demonongsan.model.Product;

public interface CategoryDao {
	void insert(Catalog category);

	void edit(Catalog category);

	void delete(String id);

	Catalog get(int id);
	
	Catalog get(String name);

	List<Catalog> getAll();
	
	List<Catalog> getCateByProduct(int id);

}
