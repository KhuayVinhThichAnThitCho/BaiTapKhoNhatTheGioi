package dao;

import java.util.List;
import entity.Category;

public interface CategoryDao {
	List<Category> findAll();
    List<Category> findByUserId(int userId);
    Category findById(int id);
    void delete(int id);
    void insert(Category category);
    void update(Category category);
}
