package senac.Listas;

public class ListLists {
	List firstList;
	List lastList;
	//String index;
	//Lists next;
	
	public ListLists() {
		firstList = lastList = null;
		//index = null;
	}
	/*
	public ListLists(Object data, String index) {
		this.index = index;
		firstList = lastList = null;
	}
	*/
	public void insere(Object data, String index) {
		List l;
		if (lastList == null) {
			//first, last next, prev = null
			l = new List(index);
			l.insere(data, index);
			firstList = lastList = l;
		} else if (isIndexExists(index)) {
			l = getListByIndex(index);//acha a lista
			l.insere(data, index);//insere o dado no nodo da lista
		} else {//se o indice não existir
			insertListByIndex(index);
			l = getListByIndex(index);
			l.insere(data, index);//insere o nodo
		}
	}
	
	public boolean isIndexExists(String index) {
		List current = firstList;
		while(current != null) {//enquanto não for o ultimo elemento
			String a = new FuncaoCelula().removeInteiro(current.getIndex());
			String b = new FuncaoCelula().removeInteiro(index);
			if (a.compareToIgnoreCase(b) == 0)
				return true;
			current = current.next();
		}
		return false;//se não encontrar o elemento retorna falso
	}
	
	public void insertListByIndex(String index) {
		String a = new FuncaoCelula().removeInteiro(firstList.getIndex());
		String b = new FuncaoCelula().removeInteiro(index);
		
		if (b.compareToIgnoreCase(a) < 0) {
			
			firstList.setPrevList(new List(index));
			//seta o proximo do anterior = ao atual
			firstList.prev().setNextList(firstList);
			firstList = firstList.prev();
			
		} else {
			
			List current = firstList;
			//verifica se chegou ao final e se o valor do indice atual é menor que o indice
			//para quando chegar no fim ou achar um posição maior ou igual ao indice
			while (current != null && (current.getIndex().compareToIgnoreCase(index)) < 0)
				current = current.next();
			//cria o novo nodo
			//Node novo = new Node(data,index);
			
			if (current == null) {//se chegou ao fim da lista deve inserir após o last
				lastList.setNextList(new List(index));
				lastList.next().setPrevList(lastList);
				lastList = lastList.next();
				
			} else if (!(current.getIndex().charAt(0) == index.charAt(0))) { 
				//se o indice existir não é necessario criar a lista

				//define o proximo do anterior como um novo elemento
				current.prev().setNextList(new List(index));
				//atualiza a referencia do proximo do novo objet
				current.prev().next().setNextList(current);
				//atualiza a referencia do anterior do novo para o anterior
				current.prev().next().setPrevList(current.prev());
				//atualizo o anterior com o novo
				current.setPrevList(current.prev().next());
			} 
		}
	}
	
	public List getListByIndex(String index) {
		//deve ser testado se o indice existe antes de chamar esta função ou terá erro
		List current = firstList;
		while(current != null) {//enquanto não for o ultimo elemento  (current == null)
			String a = new FuncaoCelula().removeInteiro(current.getIndex());
			String b = new FuncaoCelula().removeInteiro(index);
			
			if (a.compareToIgnoreCase(b) == 0) 
				return current;
			current = current.next();
		}
		//throw new EmptyIndexException;
		//return current esta apenas para não ter erro de compilação
		//ate criar a excessão
		return current;
	}
	
	public void printAllLists(ListLists lista) throws Exception {
		
		List current = firstList;
		while (current != null) {
			//System.out.println("firstNode : "+firstNode+", lastNode : "+lastNode+".\n");
			System.out.println(current.getIndex()+".\n");
			current.printList(lista);
			current = current.next();
		}
	}
	
	public boolean isEmpty() {
		if (lastList == null)
			return true;
		return false;
	}
}
