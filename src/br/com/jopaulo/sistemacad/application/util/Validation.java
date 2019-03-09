package br.com.jopaulo.sistemacad.application.util;

public class Validation {
	
	//não aceita campos vazios
	public static void assetNotEmpty(Object object) {
		if (object instanceof String) {
			String s = (String) object;
			if (StringUtils.isEmpty(s)) {
				throw new ValidationException("O texto não pode ser vazio");
			}
		}
		if (object == null) {
			throw new ValidationException("O valor não pode ser vazio");
		}
	}

}
