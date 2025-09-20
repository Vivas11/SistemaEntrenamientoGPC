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

@Service
public class DocPDFService {

	@Autowired
	private DocPDFRepository docPDFRepo;

	@Autowired
	private ModelMapper modelMapper;

	public int create(String nombre, String descripcion, MultipartFile imagen, MultipartFile archivoPdf)
			throws IOException {
		DocPDF entity = new DocPDF(nombre, descripcion, imagen.getBytes(), archivoPdf.getBytes());
		docPDFRepo.save(entity);
		return 1;
	}

	public List<DocPDFDTO> getAll() {
		List<DocPDF> entityList = docPDFRepo.findAll();
		List<DocPDFDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			DocPDFDTO libroPDFDTO = modelMapper.map(entity, DocPDFDTO.class);
			dtoList.add(libroPDFDTO);
		});
		return dtoList;
	}

	public byte[] getImagenById(Long id) {
		return docPDFRepo.findById(id).orElseThrow().getImagen();
	}

	public byte[] getPdfContentById(Long id) {
		return docPDFRepo.findById(id).orElseThrow().getContenidoPdf();
	}

	public int deleteById(Long id) {
		if (docPDFRepo.existsById(id)) {
			docPDFRepo.deleteById(id);
			return 1;
		}
		return 0;
	}

	public Long count() {
		return docPDFRepo.count();
	}

	public boolean exist(Long id) {
		return docPDFRepo.existsById(id);
	}
}