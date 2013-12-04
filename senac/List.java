package senac.Listas;
import java.util.Scanner;

import senac.algoritmos.AvaliadorExpressao;


public class List {

	private Node firstNode;
	private Node lastNode;
	private String index;//indice alphanumerico
	//private Node data;
	private List nextList;
	private List prevList;
	
	public List() {
		this.firstNode = null;
		this.lastNode = null;
		this.index = null;
		nextList = prevList = null;
		//this.data = null;
	}
	
	public List(String index) {
		
		this.firstNode = null;
		this.lastNode = null;
		this.index = new FuncaoCelula().removeInteiro(index);
		//System.out.println("indice = "+ this.index);
		nextList = prevList = null;
		//this.data = null;
	}
	

	//talvez necessario retornar dados para ListLists
	public void insere(Object data, String index) {
		Node n;
		if (isEmpty()) {
			n = new Node(data,index);
			firstNode = lastNode = n;
		} else if (isIndexExists(index)) {
			n = getNodeByIndex(index);//se indice ja existe atualiza os dados (indice de node eh numero) 
			n.setData(data);
		} else {
			//funcao para inserção apartir do indice
			insertByIndex(data, index);
		}
	}
	
	private void insertByIndex(Object data, String index) {
		
		//insere em um nodo anterior ao primeiro
		//indice menor que o do primeiro nodo e
		//...tamanho total tem que ser menor
		//ex: ab4 compare ab12 retorna maior, mas eh menor
		if (((index.compareToIgnoreCase(firstNode.getIndex())<0) && (index.length() == firstNode.getIndex().length())) || 
			(index.length() < firstNode.getIndex().length())) {
			
			firstNode.setprev(new Node(data,index));
			//seta o proximo do anterior = ao atual
			firstNode.prev().setnext(firstNode);
			firstNode = firstNode.prev();
			
		} else {
		
			Node current = firstNode;
			//verifica se chegou ao final e se o valor do indice atual é menor que o indice
			//para quando chegar no fim ou achar um posição maior ou igual ao indice
			while (current != null && (current.getIndex().compareToIgnoreCase(index))<0)
				current = current.next();
			//cria o novo nodo
			//Node novo = new Node(data,index);
			
			if (current == null || index.length() > current.getIndex().length()) {
				//se chegar ao final o nodo deve ser inserido como ultimo
				lastNode.setnext(new Node(data, index));
				//lastNode.next().setprev(current);
				lastNode.next().setprev(lastNode);
				lastNode = lastNode.next();
				//novo = current.prev();
				//novo.setprev(lastNode);
				//novo.setnext(null);
				//lastNode.setnext(novo);
				//lastNode = novo;
			} else if (current.getIndex().compareToIgnoreCase(index) == 0) { 
				
				//System.out.println("Passou por aqui");
				current.setData(data);
			} else {//if (current != null) 
				//se nao for o ultimo elemento
				//current = atual
				//current.prev() = anterior
				//current.prev().next() = proximo
			
				//define o proximo do anterior como um novo elemento
				current.prev().setnext(new Node(data,index));
				//atualiza a referencia do proximo do novo objeto
				current.prev().next().setnext(current);
				//atualiza a referencia do anterior do novo para o anterior
				current.prev().next().setprev(current.prev());
				//atualizo o anterior com o novo
				current.setprev(current.prev().next());
			} 
		}
	}
	
	public boolean isIndexExists(String index) {
		Node current = firstNode;
		while(current != null) {//enquanto não for o ultimo elemento
			if (current.getIndex().equalsIgnoreCase(index)) //depois testar com char
				return true;
			current = current.next();
		}
		return false;//se não encontrar o elemento retorna falso
	}
	
	public Node getNodeByIndex(String index) {
		//deve ser testado se o indice existe antes de chamar esta função ou terá erro
		Node current = firstNode;
		while(current != null) {//enquanto não for o ultimo elemento  (current == null)
			if (current.getIndex().compareToIgnoreCase(index) == 0 ) {
				return current;
			}
			current = current.next();
		}
		//throw new EmptyIndexException;
		//return current esta apenas para não ter erro de compilação
		//ate criar a excessão
		return current;
	}
	
	public void printList(ListLists list) throws Exception {
		Node current = firstNode;
		String aux = "";
		String dados = "";
		while (current != null) {
			//System.out.println("firstNode : "+firstNode+", lastNode : "+lastNode+".\n");
			
			dados = ""+current.getData()+"";
			//esta retornando null em RPNConverter
			aux += current.getIndex()+": "+RPNConverter(dados, list)+".\t";
			current = current.next();
		}
		System.out.println(aux+"\n");
	}
	
	public String RPNConverter(String dados, ListLists list) throws Exception{
		//funcao que converte o valor da celula
		//para os dados da RPNMath
		//dados tem que ser convertido pela expressão regular
		//para que tenham espaços
		
		String saida = "";
		String sAux;
		List l;
		Scanner sc = new Scanner(dados);
		
		while (sc.hasNext()) {
			if (sc.hasNextInt()) {
				saida += sc.next();
			} else {
				sAux = sc.next();
				if (isOperador(sAux)) {
					saida += sAux;
				} else if (list.isIndexExists(sAux)){//é uma celula ou valor invalido
					l = list.getListByIndex(sAux);
					//sAux = ""+l.getNodeByIndex(sAux).getData();
					
					//System.out.println(sAux);
					//System.out.println(l.getNodeByIndex(sAux));
					saida += l.getNodeByIndex(sAux).getData();
					//pode ser o valor armazenado em outra celula
					//se o indice existir insere os dados do indice
					//e joga dentro de uma nova instancia de
					//RPNConverter, para ver se não aponta para outra celula;
					//senão retorna erro dizendo que o valor daquela celula não existe
					
				}
			}
		}
		
		String postfix = AvaliadorExpressao.convertInfixToPostfix(saida);
		double result = AvaliadorExpressao.evaluateRPN( postfix );
		saida = ""+result;
		
		return saida;
	}
	
	public boolean isEmpty() {
		if (lastNode == null)
			return true;
		return false;
	}
	
	public List next() {
		return nextList;
	}
	
	public void setNextList(List lista) {
		this.nextList = lista;
	}
	
	public List prev() {
		return prevList;
	}
	
	public void setPrevList(List lista) {
		this.prevList = lista;
	}
	
	public String getIndex() {
		return index;
	}
	
	public void setIndex(String index) {
		this.index = index;
	}
	
	public boolean isOperador(String s) {
		switch (s) {
		case "+":
		case "-":
		case "*":
		case "/":
		case "(":
		case ")":
			return true;
		default:
			return false;
		}
	}//fim isOperador
}
