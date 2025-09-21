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

	public StreamedContent getDivideAndConquer() {
		return buildFile("Divide And Conquer.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Divide-And-Conquer.pdf");
	}

	public StreamedContent getDynamicProgramming() {
		return buildFile("Dynamic Programming.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Dynamic-Programming.pdf");
	}

	public StreamedContent getGameTheory() {
		return buildFile("Game Theory.pdf", "https://biblioteca-artemisa.netlify.app/assets/pdfs/Game-Theory.pdf");
	}

	public StreamedContent getGreedy() {
		return buildFile("Greedy.pdf", "https://biblioteca-artemisa.netlify.app/assets/pdfs/Greedy.pdf");
	}

	public StreamedContent getHashing() {
		return buildFile("Hashing.pdf", "https://biblioteca-artemisa.netlify.app/assets/pdfs/Hashing.pdf");
	}

	public StreamedContent getHeaps() {
		return buildFile("Heaps.pdf", "https://biblioteca-artemisa.netlify.app/assets/pdfs/Heaps.pdf");
	}

	public StreamedContent getIntroducciónAlgoritmiaI() {
		return buildFile("Introducción a la Algoritmia I - Eficiencia.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Introducci%C3%B3n%20a%20la%20Algoritmia%20I%20Eficiencia.pdf");
	}

	public StreamedContent getIntroducciónAlgoritmiaII() {
		return buildFile("Introducción a la Algoritmia II - Costes.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Introducci%C3%B3n%20a%20la%20Algoritmia%20II%20Costes.pdf");
	}

	public StreamedContent getIntroducciónAlgoritmiaIII() {
		return buildFile("Introducción a la Algoritmia II - Ejemplos de análisis de costes.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Introducci%C3%B3n%20a%20la%20Algoritmia%20III%20Ejemplos%20de%20an%C3%A1lisis%20de%20costes.pdf");
	}

	public StreamedContent getIntroducciónAlgoritmiaIV() {
		return buildFile("Introducción a la Algoritmia II - Coste de ordenar.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Introducci%C3%B3n%20a%20la%20Algoritmia%20IV%20Coste%20de%20ordenar.pdf");
	}

	public StreamedContent getProgramaciónDinámica() {
		return buildFile("Programación Dinámica I - Fundamentos.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Programaci%C3%B3n%20Din%C3%A1mica%20I%20Fundamentos.pdf");
	}

	public StreamedContent getProgramaciónDinámicaII() {
		return buildFile("Programación Dinámica I - Ejemplos más avanzados.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Programaci%C3%B3n%20Din%C3%A1mica%20II%20Ejemplos%20m%C3%A1s%20avanzados.pdf");
	}

	public StreamedContent getRecursion() {
		return buildFile("Recursion.pdf", "https://biblioteca-artemisa.netlify.app/assets/pdfs/Recursion.pdf");
	}

	public StreamedContent getRecursividad() {
		return buildFile("Recursividad.pdf", "https://biblioteca-artemisa.netlify.app/assets/pdfs/Recursividad.pdf");
	}

	public StreamedContent getSearching() {
		return buildFile("Searching.pdf", "https://biblioteca-artemisa.netlify.app/assets/pdfs/Searching.pdf");
	}

	public StreamedContent getSorting() {
		return buildFile("Sorting.pdf", "https://biblioteca-artemisa.netlify.app/assets/pdfs/Sorting.pdf");
	}

	public StreamedContent getStrings() {
		return buildFile("Strings.pdf", "https://biblioteca-artemisa.netlify.app/assets/pdfs/Strings.pdf");
	}

	public StreamedContent getBitMagic() {
		return buildFile("Bit Magic.pdf", "https://biblioteca-artemisa.netlify.app/assets/pdfs/Bit-Magic.pdf");
	}

	public StreamedContent getAdvancedDataStructure() {
		return buildFile("Advanced Data Structure.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Advanced-Data-Structure.pdf");
	}

	public StreamedContent getArrays() {
		return buildFile("Arrays.pdf", "https://biblioteca-artemisa.netlify.app/assets/pdfs/Arrays.pdf");
	}

	public StreamedContent getBinarySearchTree() {
		return buildFile("Binary Search Tree.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Binary-Search-Tree.pdf");
	}

	public StreamedContent getPilasColasListasÁrbolesGrafos() {
		return buildFile("Pilas, Colas, Listas, Árboles y Grafos.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/estructuras%20de%20datos.pdf");
	}

	public StreamedContent getLinkedList() {
		return buildFile("Pilas, Colas, Listas, Árboles y Grafos.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Linked-List.pdf");
	}

	public StreamedContent getPrinciplesAlgorithmicProblemSolving() {
		return buildFile("Principles of Algorithmic Problem Solving.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Matrix.pdf");
	}

	public StreamedContent getCompetitiveProgrammingHandbook() {
		return buildFile("Competitive programming handbook.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/(Espa%C3%B1ol)%20Competitive%20Programmer%E2%80%99s%20Handbook.pdf");
	}

	public StreamedContent getGeometricAlgorithms() {
		return buildFile("Geometric Algorithms.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Geometric-Algorithms.pdf");
	}

	public StreamedContent getGraphs() {
		return buildFile("Graphs.pdf", "https://biblioteca-artemisa.netlify.app/assets/pdfs/Graphs.pdf");
	}

	public StreamedContent getGuiaprogramaciónJava() {
		return buildFile("Guía de programación en Java.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Intro%20java.pdf");
	}

	public StreamedContent getGuiaprogramaciónC() {
		return buildFile("Guia de programación en C++.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/intro%20cpp.pdf");
	}

	public StreamedContent getGuiaProgramaciónPython() {
		return buildFile("Guia de programación en Python.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/intro%20py.pdf");
	}

	public StreamedContent getC() {
		return buildFile("C++.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/CPlusPlusNotesForProfessionals.pdf");
	}

	public StreamedContent getJava() {
		return buildFile("Java.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/JavaNotesForProfessionals.pdf");
	}

	public StreamedContent getPython() {
		return buildFile("Python.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/PythonNotesForProfessionals.pdf");
	}

	public StreamedContent getRuby() {
		return buildFile("Ruby.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/RubyNotesForProfessionals.pdf");
	}

	public StreamedContent getDiscreteStructures() {
		return buildFile("Discrete Structures.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Matematica%20discreta.pdf");
	}

	public StreamedContent getMatemáticaDiscreta() {
		return buildFile("Matemática discreta.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/matemtica_discreta_para_informticos.pdf");
	}

	public StreamedContent getPrinciplesAlgorithmicProblemSolvingIngles() {
		return buildFile("Principles of Algorithmic Problem Solving (ingles).pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Principles%20of%20Algorithmic%20Problem%20Solving.pdf");
	}

	public StreamedContent getCombinatorial() {
		return buildFile("Combinatorial.pdf", "https://biblioteca-artemisa.netlify.app/assets/pdfs/Combinatorial.pdf");
	}

	public StreamedContent getDiscreteStructuresIngles() {
		return buildFile("Discrete Structures.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/Matematica%20discreta.pdf");
	}

	public StreamedContent getMatemáticaDiscreta2() {
		return buildFile("Matemática discreta.pdf",
				"https://biblioteca-artemisa.netlify.app/assets/pdfs/matemtica_discreta_para_informticos.pdf");
	}

	private StreamedContent buildFile(String name, String url) {
		return DefaultStreamedContent.builder().name(name).contentType("application/pdf").stream(() -> {
			try {
				return new URL(url).openStream();
			} catch (Exception e) {
				
				return null;
			}
		}).build();
	}
}
