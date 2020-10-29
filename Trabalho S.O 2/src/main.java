import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * Simulação Memória Virtual - Trabalho 2
 * 
 * @author Rodrigo Malaquias Viana
 */

public class main {
	public static void main(String[] args)throws IOException {
		try {
			FileReader arq = new FileReader("memoriaEntrada.txt");
    		BufferedReader lerArq = new BufferedReader(arq);
    		String line = "";
			  
			String[] firstLine = (lerArq.readLine()).split(" "); // Lendo a primeira linha e pegando variaveis entre os espaços
			int m,n,p;
			//tamanho em bytes da memória virtual/ tamanho em bytes da memória física/tamanho em bytes da página/moldura
			int lineSize = 0;
			
			//Armazenando os valores de m, n e p da primeira linha do arquivo
			m = Integer.parseInt(firstLine[0]);
			n = Integer.parseInt(firstLine[1]);
			p = Integer.parseInt(firstLine[2]);
			
				lineSize = Integer.parseInt(lerArq.readLine());//Quantidade de Enderecos de memorias acessados
				
		        line = lerArq.readLine(); //Pulando a primeira linha 
		        int[] vetor = new int[lineSize];
				for(int i = 0; i < lineSize; i++) { //Percorrendo o txt
					int auxAdd = 0;
					auxAdd = Integer.parseInt(line);
					vetor[i] = auxAdd/p;	 // armazenando os enderecos em um vetor
					line = lerArq.readLine();
				}
				
				Queue<Integer> queue = new LinkedList<>();
				for(int i = 0; i<(n/p); i++ )
				{
					queue.add(vetor[i]);
					//Criando uma queue comparativa
				}
				int tam = (n/p);
				int totalCopy = 0;
				
				 for (int i = tam; i < lineSize; i++) {
					 if(!queue.contains(vetor[i])) // Substituicao de paginas
					 {
						 queue.add(vetor[i]);
						 queue.remove();
						 totalCopy++; // Somando o número total de cópias de páginas realizadas da memória virtual para a memória principal
					 }
					 
				 }
							 			
				 int[] vetorFinal = new int[tam];
				 for(int i = 0; i < tam; i++)
				 {
					 vetorFinal[i] = queue.remove();
				 }
				 Arrays.sort(vetorFinal); // Ordenando a saida final

				 FileWriter fw = new FileWriter( "saida.txt" );
		            BufferedWriter a = new BufferedWriter( fw );
		            a.write(totalCopy+"");
		            a.newLine();
		            for(int i = 0 ; i < tam ; i++) {
		                a.write(vetorFinal[i]+" ");
		            }
		            a.close();
		            fw.close();
			

		}catch(IOException a) {
			System.err.printf("Erro ao abrir o arquivo de leitura.\n"+a.getMessage());
		}
      
    }
}





