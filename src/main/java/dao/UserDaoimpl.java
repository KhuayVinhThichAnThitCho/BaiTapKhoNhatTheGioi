package dao;

import config.JPAconfig;
import entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class UserDaoimpl implements UserDao{
	@Override
	public User login(String username, String password) {
		EntityManager em = JPAconfig.getEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.getResultList().isEmpty() ? null : query.getResultList().get(0);
        } finally {
            em.close();
        }
	}
}
