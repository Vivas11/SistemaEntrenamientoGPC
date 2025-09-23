package co.edu.unbosque.sistemaentrenamientoGPC_back.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import co.edu.unbosque.sistemaentrenamientoGPC_back.dto.DocPDFDTO;
import co.edu.unbosque.sistemaentrenamientoGPC_back.entity.DocPDF;
import co.edu.unbosque.sistemaentrenamientoGPC_back.repository.DocPDFRepository;

/**
 * Servicio encargado de gestionar operaciones relacionadas con documentos PDF.
 * Utiliza {@link DocPDFRepository} para acceder a la base de datos y {@link ModelMapper} para convertir entre entidades y DTOs.
 */
@Service
public class DocPDFService {

	/** Repositorio JPA para la entidad {@code DocPDF}. */
	@Autowired
	private DocPDFRepository docPDFRepo;

	/** Utilidad para mapear entre entidades y DTOs. */
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Crea un nuevo documento PDF con imagen y contenido binario.
	 *
	 * @param nombre      nombre del documento
	 * @param descripcion descripción del documento
	 * @param imagen      archivo de imagen en formato {@code MultipartFile}
	 * @param archivoPdf  archivo PDF en formato {@code MultipartFile}
	 * @return 1 si la operación fue exitosa
	 * @throws IOException si ocurre un error al leer los archivos
	 */
	public int create(String nombre, String descripcion, MultipartFile imagen, MultipartFile archivoPdf)
			throws IOException {
		DocPDF entity = new DocPDF(nombre, descripcion, imagen.getBytes(), archivoPdf.getBytes());
		docPDFRepo.save(entity);
		return 1;
	}

	/**
	 * Obtiene todos los documentos PDF registrados en forma de DTO.
	 *
	 * @return lista de {@code DocPDFDTO}
	 */
	public List<DocPDFDTO> getAll() {
		List<DocPDF> entityList = docPDFRepo.findAll();
		List<DocPDFDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			DocPDFDTO libroPDFDTO = modelMapper.map(entity, DocPDFDTO.class);
			dtoList.add(libroPDFDTO);
		});
		return dtoList;
	}

	/**
	 * Obtiene la imagen asociada a un documento PDF por su ID.
	 *
	 * @param id identificador del documento
	 * @return arreglo de bytes que representa la imagen
	 */
	public byte[] getImagenById(Long id) {
		return docPDFRepo.findById(id).orElseThrow().getImagen();
	}

	/**
	 * Obtiene el contenido PDF binario de un documento por su ID.
	 *
	 * @param id identificador del documento
	 * @return arreglo de bytes que representa el archivo PDF
	 */
	public byte[] getPdfContentById(Long id) {
		return docPDFRepo.findById(id).orElseThrow().getContenidoPdf();
	}

	/**
	 * Elimina un documento PDF por su ID.
	 *
	 * @param id identificador del documento
	 * @return 1 si se eliminó correctamente, 0 si no existe
	 */
	public int deleteById(Long id) {
		if (docPDFRepo.existsById(id)) {
			docPDFRepo.deleteById(id);
			return 1;
		}
		return 0;
	}

	/**
	 * Cuenta la cantidad total de documentos PDF registrados.
	 *
	 * @return número total de documentos
	 */
	public Long count() {
		return docPDFRepo.count();
	}

	/**
	 * Verifica si existe un documento PDF con el identificador dado.
	 *
	 * @param id identificador del documento
	 * @return {@code true} si existe, {@code false} en caso contrario
	 */
	public boolean exist(Long id) {
		return docPDFRepo.existsById(id);
	}
}