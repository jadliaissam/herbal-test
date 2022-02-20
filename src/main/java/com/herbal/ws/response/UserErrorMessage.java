package com.herbal.ws.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public enum UserErrorMessage {
	MISSING_REQUIRED_FILED_USER("Vous avez ratté un champ obligatoire "),
	INTERNAL_SERVER_ERROR("Le Serveur de Gestion de Stock est down "),
	NO_RECORD_FOUND("Utilisateur exist pas");
	
	private String errorMessage;

	private UserErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
