package dao;

import java.util.List;

import config.JPAconfig;
import entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class CategoryDaoimpl implements CategoryDao{
	@Override
	public List<Category> findAll() {
		EntityManager em = JPAconfig.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
        } finally {
            em.close();
        }
	}

	@Override
	public List<Category> findByUserId(int userId) {
		EntityManager em = JPAconfig.getEntityManager();
        try {
            TypedQuery<Category> q = em.createQuery("SELECT c FROM Category c WHERE c.user.id = :uid", Category.class);
            q.setParameter("uid", userId);
            return q.getResultList();
        } finally {
            em.close();
        }
	}

	@Override
	public Category findById(int id) {
		EntityManager em = JPAconfig.getEntityManager();
        try {
            return em.find(Category.class, id);
        } finally {
            em.close();
        }
	}


	@Override
	public void delete(int id) {
		EntityManager em = JPAconfig.getEntityManager();
        try {
            em.getTransaction().begin();
            Category c = em.find(Category.class, id);
            if (c != null) em.remove(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
		
	}

	@Override
	public void insert(Category category) {
		EntityManager em = JPAconfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(category);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
		
	}

	@Override
	public void update(Category category) {
		EntityManager em = JPAconfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(category);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
		
	}
}
