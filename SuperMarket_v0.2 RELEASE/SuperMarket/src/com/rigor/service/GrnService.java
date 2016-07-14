package com.rigor.service;
import java.util.List;

import com.rigor.entity.Grn;

public interface GrnService {
	
	public void saveGrn(Grn grn);
	
	public void updateGrn(Grn grn);
	
	public Grn findById(int id);
	
	public void deleteGrnById(int id);

	public List<Grn> findAllGrns(); 
	
	public void deleteAllGrns();
}
