package service;

import java.util.List;

import entity.Category;

public interface CategoryService {
	List<Category> findAll();
	Category findById(int id);
	void delete(int id);
	List<Category> findByUserId(int userId);
	void insert(Category category);
    void update(Category category);
}
