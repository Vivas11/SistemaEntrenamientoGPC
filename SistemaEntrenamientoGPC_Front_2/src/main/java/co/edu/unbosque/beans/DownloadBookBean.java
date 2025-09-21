package co.edu.unbosque.beans;

import java.net.URL;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named(value = "filebean")
@RequestScoped
public class DownloadBookBean {

	public StreamedContent getAlgorithms() {
		return buildFile("Algorithms.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/AlgorithmsNotesForProfessionals.pdf");
	}

	public StreamedContent getAlgoritmosVoraces() {
		return buildFile("Algoritmos voraces.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Algoritmos%20Voraces.pdf");
	}

	public StreamedContent getBacktracking() {
		return buildFile("Backtracking.pdf", "https://biblioteca-artemisa.netlify.app/assets/pdfs/Arrays.pdf");
	}

//	Divide And Conquer (Ingles)

	public StreamedContent getDivideAndConquer() {
		return buildFile("Divide And Conquer.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Divide-And-Conquer.pdf");
	}

//Dynamic Programming (Ingles)}

	public StreamedContent getDynamicProgramming() {
		return buildFile("Dynamic Programming.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Dynamic-Programming.pdf");
	}

//Game Theory (ingles)

	public StreamedContent getGameTheory() {
		return buildFile("Game Theory.pdf", "https://biblioteca-artemisa.netlify.app/assets/pdfs/Game-Theory.pdf");
	}

//	Greedy (ingles)

	public StreamedContent getGreedy() {
		return buildFile("Greedy.pdf", "https://biblioteca-artemisa.netlify.app/assets/pdfs/Greedy.pdf");
	}

//	Hashing (ingles)

	public StreamedContent getHashing() {
		return buildFile("Hashing.pdf", "https://biblioteca-artemisa.netlify.app/assets/pdfs/Hashing.pdf");
	}

//Heaps (ingles)

	public StreamedContent getHeaps() {
		return buildFile("Heaps.pdf", "https://biblioteca-artemisa.netlify.app/assets/pdfs/Heaps.pdf");
	}

//	Introducción a la Algoritmia I - Eficiencia

	public StreamedContent getIntroducciónAlgoritmiaI() {
		return buildFile("Introducción a la Algoritmia I - Eficiencia.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Introducci%C3%B3n%20a%20la%20Algoritmia%20I%20Eficiencia.pdf");
	}

//Introducción a la Algoritmia II - Costes

	public StreamedContent getIntroducciónAlgoritmiaII() {
		return buildFile("Introducción a la Algoritmia II - Costes.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Introducci%C3%B3n%20a%20la%20Algoritmia%20II%20Costes.pdf");
	}

//Introducción a la Algoritmia III - Ejemplos de análisis de costes

	public StreamedContent getIntroducciónAlgoritmiaIII() {
		return buildFile("Introducción a la Algoritmia II - Ejemplos de análisis de costes.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Introducci%C3%B3n%20a%20la%20Algoritmia%20III%20Ejemplos%20de%20an%C3%A1lisis%20de%20costes.pdf");
	}

//	Introducción a la Algoritmia IV - Coste de ordenar	

	public StreamedContent getIntroducciónAlgoritmiaIV() {
		return buildFile("Introducción a la Algoritmia II - Coste de ordenar.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Introducci%C3%B3n%20a%20la%20Algoritmia%20IV%20Coste%20de%20ordenar.pdf");
	}

//Programación Dinámica I - Fundamentos

	public StreamedContent getProgramaciónDinámica() {
		return buildFile("Programación Dinámica I - Fundamentos.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Programaci%C3%B3n%20Din%C3%A1mica%20I%20Fundamentos.pdf");
	}

//	Programación Dinámica II - Ejemplos más avanzados

	public StreamedContent getProgramaciónDinámicaII() {
		return buildFile("Programación Dinámica I - Ejemplos más avanzados.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Programaci%C3%B3n%20Din%C3%A1mica%20II%20Ejemplos%20m%C3%A1s%20avanzados.pdf");
	}

//	Recursion (Ingles)

	public StreamedContent getRecursion() {
		return buildFile("Recursion.pdf", "https://biblioteca-artemisa.netlify.app/assets/pdfs/Recursion.pdf");
	}

//	Recursividad

	public StreamedContent getRecursividad() {
		return buildFile("Recursividad.pdf", "https://biblioteca-artemisa.netlify.app/assets/pdfs/Recursividad.pdf");
	}

//Searching (Ingles)

	public StreamedContent getSearching() {
		return buildFile("Searching.pdf", "https://biblioteca-artemisa.netlify.app/assets/pdfs/Searching.pdf");
	}

//	Sorting (Ingles)

	public StreamedContent getSorting() {
		return buildFile("Sorting.pdf", "https://biblioteca-artemisa.netlify.app/assets/pdfs/Sorting.pdf");
	}

//	Strings

	public StreamedContent getStrings() {
		return buildFile("Strings.pdf", "https://biblioteca-artemisa.netlify.app/assets/pdfs/Strings.pdf");
	}

//	Bit Magic (Ingles)

	public StreamedContent getBitMagic() {
		return buildFile("Bit Magic.pdf", "");
	}

//	Advanced Data Structure (ingles)

	public StreamedContent getAdvancedDataStructure() {
		return buildFile("Advanced Data Structure.pdf", "");
	}

//	Arrays (Ingles)

	public StreamedContent getArrays() {
		return buildFile("Arrays.pdf", "");
	}

//	Binary Search Tree (Ingles)

	public StreamedContent getBinarySearchTree() {
		return buildFile("Binary Search Tree.pdf", "");
	}

//Pilas, Colas, Listas, Árboles y Grafos
	
	public StreamedContent getPilasColasListasÁrbolesGrafos() {
		return buildFile("Pilas, Colas, Listas, Árboles y Grafos.pdf", "");
	}

//	Linked List (Ingles)
	
	
	public StreamedContent getLinkedList() {
		return buildFile("Pilas, Colas, Listas, Árboles y Grafos.pdf", "");
	}

//	Linked List
	
	
	private StreamedContent buildFile(String name, String url) {
		return DefaultStreamedContent.builder().name(name).contentType("application/pdf").stream(() -> {
			try {
				return new URL(url).openStream();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}).build();
	}
}
