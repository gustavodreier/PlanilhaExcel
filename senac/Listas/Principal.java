package senac.Listas;

import java.util.Scanner;

public class Principal {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Scanner sc;
		String celula;
		String dados;
		//List l = new List();
		ListLists list = new ListLists();
		//String test = "a";
		
		/*
		for (int i = (int)'0'; i < (int)'9'; i++) {
			
			System.out.println("i = "+i+"\n");
			
		}
		*/
		
		
		
		while(true) {
						
			System.out.println("Digite a celula a ser alterada:");
			sc = new Scanner(System.in);
			celula = sc.next();
			
			if (celula.equalsIgnoreCase("fim")) {
				sc.close();
				
				System.exit(0);
			}
			
			if (list.isIndexExists(celula)) {
				if (list.getListByIndex(celula).isIndexExists(celula)) {
					dados = list.getListByIndex(celula).getNodeByIndex(celula).getData()+"";
					System.out.println(celula+" : formula = "+dados+" :");
				} else {
					System.out.println("Insira a nova fórmula: "+celula+" = ");
				}
			} else {
				System.out.println("Insira a nova fórmula: "+celula+" = ");
			}
			
			//sc.close();
			sc = new Scanner(System.in);
			
			dados = sc.nextLine();
			
			list.insere(dados, celula);
			list.printAllLists(list);
			
			//String.compareTo("after");
			
			
			//l = list.getListByIndex(celula);
			
			//l.printList();
			//l.getNodeByIndex(celula.substring(1,2));
			//System.out.println(celula+" = "+l.getNodeByIndex(celula));
			
		//List lista = l.getListByIndex("r9");
		
		}
		//lista.printList();

		/*
		Object obj = a.getData();
		String index = a.getIndex();
		//int data = (int) obj;
		System.out.println("Valor de dados = "+obj+".\nValor do indice = "+index+".");
		*/
	}
}
