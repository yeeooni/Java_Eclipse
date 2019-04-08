package com.kitri.haksa.test;

import com.kitri.haksa.data.HaksaDto;
import com.kitri.haksa.service.HaksaServiceImpl;

public class HaksaMain {
	public static void main(String[] args) {
		
		HaksaServiceImpl haksa = new HaksaServiceImpl();
		haksa.menu();

	}
}
