package com.example.demonongsan.dao;
 
import java.util.List;

import com.example.demonongsan.model.Admin;
 
public interface AdminDao { 
	void insert(Admin admin); 
 
	void edit(Admin admin); 
	
	void delete(String id); 
 
	Admin get(int id); 
	 
	Admin get(String name); 
 
	List<Admin> getAll(); 
	
} 
