package br.com.jopaulo.sistemacad.application.util;
/**
 * @author joao
 * Classe de utilitários, que pode reaproveitar em outros projetos
 */
public class StringUtils {
	
	//verifica se uma String está vazia
	public static boolean isEmpty(String s) {
		if (s == null) {
			return true; // String vazia
		}
		return s.trim().length() == 0; //retira espaço em branco no início e final
	}
	
	//concatena 0 a esquerda até atingir limite definido
	public static String leftZeroes(int value, int finalSize) {
		return String.format("%0" + finalSize + "d", value);
	}
	
	public static void main(String[] args) {
		String str = "";
		
		boolean b = StringUtils.isEmpty(str);
		System.out.println(b);
		
		int v = 1211111;
		System.out.println(StringUtils.leftZeroes(v, 10));
	}

}
