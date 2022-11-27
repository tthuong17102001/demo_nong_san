package com.example.demonongsan.service;

import java.util.List;

import com.example.demonongsan.model.Review;

public interface ReviewService {
	void insert(Review review);

	void edit(Review review);

	void delete(int id);

	Review get(int id);
	
	Review get(String name);

	List<Review> getAll();
	
	List<Review> getReviewById(int id);
}
