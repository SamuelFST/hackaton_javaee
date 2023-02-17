package com.usersapp.modules.user.dao;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.usersapp.modules.user.model.User;

@ApplicationScoped
public class UserDAO {
	
	@PersistenceContext
    EntityManager em;
	
	public List<User> findAll() {
		return em.createQuery("from User", User.class).getResultList();
	}
	
	public Optional<User> findById(Integer id) {
		return Optional.of(em.find(User.class, id));
	}
	
	public List<User> findAllBirthdaysOfMonth(Integer month) {
		TypedQuery<User> query = em.createQuery("from User where MONTH(dateBirth) = :month", User.class);
		query.setParameter("month", month);
		return query.getResultList();
	}
	
	@Transactional
	public User save(User user) {
		em.persist(user);
		return user;
	}
	
	@Transactional
	public User update(User user) {
		em.merge(user);
		return user;
	}
	
	@Transactional
	public void delete(User user) {
		em.remove(em.getReference(User.class, user.getId()));			
	}
}
