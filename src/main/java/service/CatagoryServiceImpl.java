package service;

import java.util.List;
import entity.Category;

import dao.CategoryDao;
import dao.CategoryDaoimpl;

public class CatagoryServiceImpl implements CategoryService{
	private CategoryDao dao = new CategoryDaoimpl();
	
	@Override
	public List<Category> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Category> findByUserId(int userId) {
		return dao.findByUserId(userId);
	}

	@Override
	public Category findById(int id) {
		return dao.findById(id);
	}


	@Override
	public void delete(int id) {
		dao.delete(id);
	}

	@Override
	public void insert(Category category) {
		dao.insert(category);
		
	}

	@Override
	public void update(Category category) {
		dao.update(category);
		
	}
}
