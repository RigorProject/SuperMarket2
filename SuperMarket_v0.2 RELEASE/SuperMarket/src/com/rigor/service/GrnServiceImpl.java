package com.rigor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rigor.dao.GrnDAO;
import com.rigor.entity.Grn;


@Service
public class GrnServiceImpl implements GrnService {
	
	@Autowired
	private GrnDAO grnDAO;
	private static List<Grn> grns;
	
	public void saveGrn(Grn grn) {
		grnDAO.save(grn);
	}
	
	@Override
	public void updateGrn(Grn grn) {
		grnDAO.update(grn);
	}
	
	public void deleteAllGrns(){
		grns.clear();
	}
	
	public Grn findById(int id) {
		Grn grn = grnDAO.findById(id);
		return grn;
	}

	public void deleteGrnById(int id) {
		grnDAO.deleteById(id);
	}
	
	public List<Grn> findAllGrns() {
		List<Grn> grns = grnDAO.findAll();
		return grns;
	}
}
