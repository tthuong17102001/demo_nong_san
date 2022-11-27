package com.example.demonongsan.dao;
import java.util.List;

import com.example.demonongsan.model.Transactions;


public interface TransactionDao {
	void insert(Transactions transaction); 
	 
	void edit(Transactions admin); 
	
	void delete(String id); 
 
	Transactions get(int id); 
	 
	Transactions get(String name); 
 
	List<Transactions> getAll(); 
	
	
}
