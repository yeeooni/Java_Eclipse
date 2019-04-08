package com.kitri.haksa.service;

import com.kitri.haksa.data.HaksaDto;

public interface HaksaService {

	public void menu();

	public void registerMenu();
	
	public void resister(HaksaDto haksa);

	public void findNameMenu();

	public HaksaDto findName(String name);

	public void deleteMenu();

	public int delete(String name);

	public void selectAll();

	public void processExit();

}
