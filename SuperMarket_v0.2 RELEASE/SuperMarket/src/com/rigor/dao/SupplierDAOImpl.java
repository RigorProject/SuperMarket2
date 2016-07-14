package com.rigor.dao;

/** 

* Data Access Object Interface Implementation for supplier. 
* 
* Copyright (c) Virtusa Corporation 2016, All Rights Reserved.
* 
* This class making it easy to work with data access technologies like JDBC, Hibernate.
* basically help to perform CRUD operations.
* 
* @author MadhukaraK 
* 
* @version 1.0
* 
* @see see also SupplierDAOImpl.java

*/

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import com.rigor.entity.Supplier;
import com.rigor.util.HibernateUtility;

@Repository
public class SupplierDAOImpl implements SupplierDAO {
	
	public int save(Supplier supplier) {
		Session session = HibernateUtility.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			int saved = (int) session.save(supplier);
			session.getTransaction().commit();
			return saved;
			
		} catch (HibernateException e) {
			try {
				session.getTransaction().rollback();
			} catch (RuntimeException e2) {
				System.out.println("Couldn’t roll back transaction"+e2);
			}
			throw e;
		}finally {
			session.close();
		}
	}

	public void update(Supplier supplier) {
		Session session = HibernateUtility.getSessionFactory().openSession();

		try {
			session.beginTransaction();
			session.update(supplier);
			session.getTransaction().commit();
			
		} catch (HibernateException e) {
			try {
				session.getTransaction().rollback();
			} catch (RuntimeException e2) {
				System.out.println("Couldn’t roll back transaction"+e2);
			}
			throw e;
		}finally {
			session.close();
		}
	}

	public Supplier findById(int id) {
		Session session = HibernateUtility.getSessionFactory().openSession();
		try {
			return (Supplier) session.get(Supplier.class, id);
			
		} catch (HibernateException e) {
			try {
				session.getTransaction().rollback();
			} catch (RuntimeException e2) {
				System.out.println("Couldn’t roll back transaction"+e2);
			}
			throw e;
		}finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Supplier> findAll() {
		Session session = HibernateUtility.getSessionFactory().openSession();
		
		try {
			Query query = session.createQuery("from Supplier s");
			return query.list();
			
		} catch (HibernateException e) {
			try {
				session.getTransaction().rollback();
			} catch (RuntimeException e2) {
				System.out.println("Couldn’t roll back transaction"+e2);
			}
			throw e;
		}finally {
			session.close();
		}
	}

	public void deleteById(int id) {
		Session session = HibernateUtility.getSessionFactory().openSession();

		try {
			session.beginTransaction();
			session.delete(findById(id));
			session.getTransaction().commit();
			
			findAll();
			
		} catch (HibernateException e) {
			try {
				session.getTransaction().rollback();
			} catch (RuntimeException e2) {
				System.out.println("Couldn’t roll back transaction"+e2);
			}
			throw e;
		}finally {
			session.close();
		}

	}
	
	@Transactional
	   public void indexSupplier(){
	      try
	      {
			Session session = HibernateUtility.getSessionFactory().openSession();
			FullTextSession fullTextSession = Search.getFullTextSession(session);
	    	fullTextSession.createIndexer().startAndWait();
	    }
	    catch (InterruptedException e) {
	      System.out.println("An error occurred trying to build the search index: " + e.toString());
	    }
	    return;
	  }
	
	@Transactional
	public List<Supplier> search(String searchText){
		Session session = HibernateUtility.getSessionFactory().openSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Supplier.class).get();
		org.apache.lucene.search.Query query = qb.keyword().onFields("supplierName").matching(searchText).createQuery();
		org.hibernate.Query hibQuery = fullTextSession.createFullTextQuery(query, Supplier.class);
		@SuppressWarnings("unchecked")
		List<Supplier> results = hibQuery.list();
		return results;
	}

}
