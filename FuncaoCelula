package senac.Listas;
import java.util.Scanner;


public class FuncaoCelula {
	
	public FuncaoCelula() {
	}
	
	public String removeInteiro(String s) {
		for (int i = 0; i < s.length(); i++) {
			//se codigo ascii for inteiro
			if ((int)s.charAt(i) > 47 && (int)s.charAt(i) < 57) {
				//retorna com todos os valores anteriores ao inteiro
				return s.substring(0,i);
			}
		}
		//excecao, uma celula tem possuir numeros
		return s;
	}
	
	public String removeAlphaNumeric(String s) {
		for (int i = 0; i < s.length(); i++) {
			if ((int)s.charAt(i) < 46 && (int)s.charAt(i) > 56) {
				return s.substring(i,s.length());
			}
		}
		return s;
		
	}
	
	public String valorCelula() {
		Scanner sc = new Scanner(System.in);
		String aux = "";
		
		while(sc.hasNext()) {
			aux += sc.next();
		}
		sc.close();
		return aux;
	}
	
}
