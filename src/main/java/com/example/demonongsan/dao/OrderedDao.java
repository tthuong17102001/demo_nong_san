package com.example.demonongsan.dao;

import java.util.List;

import com.example.demonongsan.model.Ordered;

public interface OrderedDao {
	void insert(Ordered ordered); 
	 
	void edit(Ordered ordered); 
	
	void delete(String id); 
 
	Ordered get(int id); 
	 
	Ordered get(String name); 
 
	List<Ordered> getAll();
}
