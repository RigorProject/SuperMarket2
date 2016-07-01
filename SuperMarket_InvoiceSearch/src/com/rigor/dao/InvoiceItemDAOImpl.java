package com.rigor.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.rigor.entity.InvoiceItem;
import com.rigor.util.HibernateUtility;

@Repository("invoiceItemDAO")
public class InvoiceItemDAOImpl implements InvoiceItemDAO {

	@Override
	public int createInvoice(InvoiceItem invoiceItem) {
		int saved = 0;
		Session session = HibernateUtility.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			saved = (Integer) session.save(invoiceItem);

			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return saved;
	}

	@Override
	public void searchInvoice(int id) {

	}

	@Override
	public void editInvoice(InvoiceItem invoiceItem) {
		Session session = HibernateUtility.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(invoiceItem);
			session.getTransaction().commit();

		} catch (HibernateException e) {
			try {
				session.getTransaction().rollback();
			} catch (RuntimeException e2) {
				System.out.println("Couldn�t roll back transaction" + e2);
			}
			throw e;
		} finally {
			session.close();
		}

	}

	@Override
	public void cancelInvoice(int id) {
		Session session = HibernateUtility.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.delete(findbyID(id));
			session.getTransaction().commit();

		} catch (HibernateException e) {
			try {
				session.getTransaction().rollback();
			} catch (RuntimeException e2) {
				System.out.println("Couldn�t roll back transaction" + e2);
			}
			throw e;
		} finally {
			session.close();
			findAll();
		}

	}

	@Override
	public void setAmount(double amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public InvoiceItem findbyID(int id) {
		Session session = HibernateUtility.getSessionFactory().openSession();
		try {

			System.out.println("before return");
			// InvoiceItem invoice = (InvoiceItem)
			// session.load(InvoiceItem.class, id);
			// System.out.println("this is the invoice item
			// amount"+invoice.getAmount());
			InvoiceItem invoiceItem = (InvoiceItem) session.get(InvoiceItem.class, id);
			
			return invoiceItem;

		} catch (Exception e) {
			System.out.println("Hibernate Error is there");
			try {
				session.getTransaction().rollback();
				System.out.println("called Rollback");
			} catch (RuntimeException e2) {
				System.out.println("Couldn�t roll back transaction" + e2);
			}
			throw e;
		} finally {
			session.close();
			System.out.println("Session was closed");
		}

	}

	@Override
	public List<InvoiceItem> findAll() {
		Session session = HibernateUtility.getSessionFactory().openSession();
		try {

			Query query = session.createQuery("from InvoiceItem g");
			return query.list();

		} catch (HibernateException e) {
			try {
				session.getTransaction().rollback();
			} catch (RuntimeException e2) {
				System.out.println("Couldn�t roll back transaction" + e2);
			}
			throw e;
		}

	}

}
