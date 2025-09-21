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
		return buildFile("Bit Magic.pdf", "https://biblioteca-artemisa.netlify.app/assets/pdfs/Bit-Magic.pdf");
	}

//	Advanced Data Structure (ingles)

	public StreamedContent getAdvancedDataStructure() {
		return buildFile("Advanced Data Structure.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Advanced-Data-Structure.pdf");
	}

//	Arrays (Ingles)

	public StreamedContent getArrays() {
		return buildFile("Arrays.pdf", "https://biblioteca-artemisa.netlify.app/assets/pdfs/Arrays.pdf");
	}

//	Binary Search Tree (Ingles)

	public StreamedContent getBinarySearchTree() {
		return buildFile("Binary Search Tree.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Binary-Search-Tree.pdf");
	}

//Pilas, Colas, Listas, Árboles y Grafos

	public StreamedContent getPilasColasListasÁrbolesGrafos() {
		return buildFile("Pilas, Colas, Listas, Árboles y Grafos.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/estructuras%20de%20datos.pdf");
	}

//	Linked List (Ingles)

	public StreamedContent getLinkedList() {
		return buildFile("Pilas, Colas, Listas, Árboles y Grafos.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Linked-List.pdf");
	}

//	Principles of Algorithmic Problem Solving (Ingles)

	public StreamedContent getPrinciplesAlgorithmicProblemSolving() {
		return buildFile("Principles of Algorithmic Problem Solving.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Matrix.pdf");
	}

//	Competitive programming handbook

	public StreamedContent getCompetitiveProgrammingHandbook() {
		return buildFile("Competitive programming handbook.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/(Espa%C3%B1ol)%20Competitive%20Programmer%E2%80%99s%20Handbook.pdf");
	}

//	Geometric Algorithms (ingles)

	public StreamedContent getGeometricAlgorithms() {
		return buildFile("Geometric Algorithms.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Geometric-Algorithms.pdf");
	}

//Graphs (ingles)

	public StreamedContent getGraphs() {
		return buildFile("Graphs.pdf", "https://biblioteca-artemisa.netlify.app/assets/pdfs/Graphs.pdf");
	}

//Guía de programación en Java

	public StreamedContent getGuíaprogramaciónJava() {
		return buildFile("Guía de programación en Java.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Intro%20java.pdf");
	}

//Guia de programación en C++

	public StreamedContent getGuiaprogramaciónC() {
		return buildFile("Guia de programación en C++.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/intro%20cpp.pdf");
	}

//Guia de programación en Python

	public StreamedContent getGuiaProgramaciónPython() {
		return buildFile("Guia de programación en Python.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/intro%20py.pdf");
	}

//	C++ (Ingles)

	public StreamedContent getC() {
		return buildFile("C++.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/CPlusPlusNotesForProfessionals.pdf");
	}

//	Java (Ingles)

	public StreamedContent getJava() {
		return buildFile("Java.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/JavaNotesForProfessionals.pdf");
	}

//	Python (Ingles)

	public StreamedContent getPython() {
		return buildFile("Python.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/PythonNotesForProfessionals.pdf");
	}

//	Ruby (Ingles)

	public StreamedContent getRuby() {
		return buildFile("Ruby.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/RubyNotesForProfessionals.pdf");
	}

//	Discrete Structures (ingles)

	public StreamedContent getDiscreteStructures() {
		return buildFile("Discrete Structures.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Matematica%20discreta.pdf");
	}

//	Matemática discreta

	public StreamedContent getMatemáticaDiscreta() {
		return buildFile("Matemática discreta.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/matemtica_discreta_para_informticos.pdf");
	}

//	Principles of Algorithmic Problem Solving (ingles)

	public StreamedContent getPrinciplesAlgorithmicProblemSolvingIngles() {
		return buildFile("Principles of Algorithmic Problem Solving (ingles).pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Principles%20of%20Algorithmic%20Problem%20Solving.pdf");
	}

//	Combinatorial (Ingles)

	public StreamedContent getCombinatorial() {
		return buildFile("Combinatorial.pdf", "https://biblioteca-artemisa.netlify.app/assets/pdfs/Combinatorial.pdf");
	}

//	Discrete Structures (Ingles)

	public StreamedContent getDiscreteStructuresIngles() {
		return buildFile("Discrete Structures.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Matematica%20discreta.pdf");
	}

//	Matemática discreta

	public StreamedContent getMatemáticaDiscreta2() {
		return buildFile("Matemática discreta.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/matemtica_discreta_para_informticos.pdf");
	}

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
