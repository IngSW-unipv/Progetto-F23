package it.unipv.sfw.findme.model.email;

import java.util.Random;

public class GenerateRandom {
	private int number;
	
	public GenerateRandom() {
		Random r=new Random();
		this.number=1000 + r.nextInt(8999);
		
	}
	
	public int getRandom() {
		return this.number;
	}

}
