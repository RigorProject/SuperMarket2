package com.rigor.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rigor.dao.SupplierDAOImpl;
import com.rigor.entity.Supplier;

@Service
public class SupplierServiceImpl implements SupplierService {
	@Autowired
	private SupplierDAOImpl supplierDAO;

	@Override
	public void saveSupplier(Supplier supplier) {
		supplierDAO.save(supplier);
	}

	@Override
	public void update(Supplier supplier) {
		supplierDAO.update(supplier);		
	}

	@Override
	public Supplier getSupplier(int id) {
		Supplier supplier = supplierDAO.findById(id);
		return supplier;
	}

	@Override
	public void deleteSupplier(int id) {
		supplierDAO.deleteById(id);
	}

	@Override
	public List<Supplier> getAllSuppliers() {
		List<Supplier> suppliers = supplierDAO.findAll();
		return suppliers;
	}

}
