package it.unipv.sfw.findme.exceptions;

import it.unipv.sfw.findme.enums.UserType;

public class NotAllowedToRegisterEx implements ExHandler{
	public NotAllowedToRegisterEx(UserType type) {
		ExceptionHandler("\u274C Not Allowed to register as a "+type);
	}
	@Override
	public void ExceptionHandler(String message) {
		ExceptionFrame ex=new ExceptionFrame(message);
	}

}