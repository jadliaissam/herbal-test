package com.herbal.ws.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public enum StockErrorMessage {
	
	MISSING_REQUIRED_FILED("Vous avez pas rempli tous les informations naicessaire. "),
	INTERNAL_SERVER_ERROR("Le Serveur de Gestion de Stock est down"),
	NO_RECORD_FOUND("Ce produit exist pas");
	
	private String errorMessage;

	private StockErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
