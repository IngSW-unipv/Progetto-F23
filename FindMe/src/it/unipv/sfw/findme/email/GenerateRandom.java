package it.unipv.sfw.findme.email;

import java.util.Random;

public class GenerateRandom {//codice di verifica lo genero con un random tra 1000 e 9999
	private int number;
	
	public GenerateRandom() {
		Random r=new Random();
		this.number=1000 + r.nextInt(8999);
		
	}
	
	public int getRandom() {
		return this.number;
	}

}
