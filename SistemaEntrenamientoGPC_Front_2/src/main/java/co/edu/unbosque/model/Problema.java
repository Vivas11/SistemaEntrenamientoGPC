	package co.edu.unbosque.model;

	import java.util.Objects;

	import jakarta.persistence.Entity;

	/**
	 * Entidad que representa un problema de programación con sus atributos
	 * principales: dificultad, tema y juez (plataforma) asociado.
	 */
	@Entity
	public class Problema {

		/** Identificador único del problema. */
		private Long id;
		/** Nombre descriptivo del problema. */
		private String nombre;
		/** Dificultad (por ejemplo: Facil, Medio, Dificil). */
		private String dificultad;
		/** Tema o categoría principal (ej. Grafos, DP). */
		private String tema;
		/** Juez o plataforma de origen (ej. UVA, Codeforces). */
		private String juez;

		/** Constructor vacío requerido por frameworks/JPA. */
		public Problema() { }

		/**
		 * Constructor completo.
		 * @param id Identificador.
		 * @param nombre Nombre del problema.
		 * @param dificultad Dificultad textual.
		 * @param tema Tema asociado.
		 * @param juez Juez/plataforma.
		 */
		public Problema(Long id, String nombre, String dificultad, String tema, String juez) {
			this.id = id;
			this.nombre = nombre;
			this.dificultad = dificultad;
			this.tema = tema;
			this.juez = juez;
		}

		@Override
		public int hashCode() {
			return Objects.hash(dificultad, id, juez, nombre, tema);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Problema other = (Problema) obj;
			return Objects.equals(dificultad, other.dificultad) && Objects.equals(id, other.id)
					&& Objects.equals(juez, other.juez) && Objects.equals(nombre, other.nombre)
					&& Objects.equals(tema, other.tema);
		}

		/**
		 * @return Nombre del problema.
		 */
		public String getNombre() { return nombre; }

		/**
		 * @param nombre Nombre del problema.
		 */
		public void setNombre(String nombre) { this.nombre = nombre; }

		/**
		 * @return Dificultad textual.
		 */
		public String getDificultad() { return dificultad; }

		/**
		 * @param dificultad Dificultad textual.
		 */
		public void setDificultad(String dificultad) { this.dificultad = dificultad; }

		/**
		 * @return Tema asociado.
		 */
		public String getTema() { return tema; }

		/**
		 * @param tema Tema asociado.
		 */
		public void setTema(String tema) { this.tema = tema; }

		/**
		 * @return Juez/plataforma de origen.
		 */
		public String getJuez() { return juez; }

		/**
		 * @param juez Juez/plataforma de origen.
		 */
		public void setJuez(String juez) { this.juez = juez; }

		/**
		 * @return Identificador.
		 */
		public Long getId() { return id; }

		/**
		 * @param id Identificador.
		 */
		public void setId(Long id) { this.id = id; }

		@Override
		public String toString() {
			return "Problema [nombre=" + nombre + ", dificultad=" + dificultad + ", tema=" + tema + ", juez=" + juez + ", id=" + id + "]";
		}
	}
