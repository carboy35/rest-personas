package cl.onemarketer.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.onemarketer.entities.PerPersonas;
import cl.onemarketer.enumerated.MensajesRespuestaRest;
import cl.onemarketer.model.Personas;
import cl.onemarketer.model.RestResponse;
import cl.onemarketer.repository.PerPersonasRepository;

@RestController
public class CrudPersonas {
	private static final Log LOG = LogFactory.getLog(CrudPersonas.class);
	
	@Autowired
	private PerPersonasRepository personasRepository;
//	@Qualifier("datasource1")
	@Autowired
	private DataSource primaryDataSource;
	
	@Autowired
	private DataSource secondaryDataSource;


	@Autowired
	private ModelMapper modelMapper;
//	@CrossOrigin(origins = "http://localhost:4200")
	@SuppressWarnings("rawtypes")
	@GetMapping("/personas")
	public ResponseEntity<RestResponse> getAll(){
		List<Personas> personaModelList= new ArrayList<>();
		try {
			LOG.info("Se consulta todas las personas");
			List<PerPersonas> personaEntityList=(List<PerPersonas>)personasRepository.findAll();
			if (personaEntityList!=null) { 
				if (personaEntityList.size()>0) {
					personaModelList=personaEntityList.stream().map(e -> modelMapper.map(e, Personas.class)).collect(Collectors.toList());
				}
			}
		}catch(Exception e) {
			LOG.error("Ocurrió un error: ",e);
			return ResponseEntity.ok(new RestResponse(false, "Ocurrió un error al obtener las personas", MensajesRespuestaRest.OK.name(),null));
		}
		return ResponseEntity.ok(new RestResponse(true, "se obtiene exitosamente lista personas", MensajesRespuestaRest.OK.name(),personaModelList));
	}


//	@CrossOrigin(origins = "http://localhost:4200")
	@SuppressWarnings("rawtypes")
	@GetMapping("/personas/{id}")
	public ResponseEntity<RestResponse> getById(@PathVariable Integer id){
		Personas personaModel= new Personas();
		try {
			Optional<PerPersonas> personaEntity=personasRepository.findById(id);
			if (personaEntity.isPresent()) { 
				personaModel=modelMapper.map(personaEntity.get(), Personas.class);
			}
		}catch(Exception e) {
			LOG.error("Ocurrió un error: ",e);
			return ResponseEntity.ok(new RestResponse(false, "Ocurrió un error al obtener la persona", MensajesRespuestaRest.OK.name(),null));
		}
		return ResponseEntity.ok(new RestResponse(true, "se obtiene exitosamente la persona", MensajesRespuestaRest.OK.name(),personaModel));
	}
	

//	@CrossOrigin(origins = "http://localhost:4200")
	@SuppressWarnings("rawtypes")
	@PostMapping("/personas")
	public ResponseEntity<RestResponse> savePersonas(@RequestBody Personas persona){
		try {
			
			PerPersonas perEntity=modelMapper.map(persona, PerPersonas.class);
			personasRepository.save(modelMapper.map(persona, PerPersonas.class));
		}catch(Exception e) {
			LOG.error("Ocurrió un error: ",e);
			return ResponseEntity.ok(new RestResponse(false, "Ocurrió un error al guardar la persona", MensajesRespuestaRest.OK.name(),false));
		}

		return ResponseEntity.ok(new RestResponse(true, "se guarda exitosamente la persona", MensajesRespuestaRest.OK.name(),true));
	}

//	@CrossOrigin(origins = "http://localhost:4200")
	@SuppressWarnings("rawtypes")
	@PutMapping("/personas")
	public ResponseEntity<RestResponse> updatePersonas(@RequestBody Personas persona){
		try {
			personasRepository.save(modelMapper.map(persona, PerPersonas.class));
		}catch(Exception e) {
			LOG.error("Ocurrió un error: ",e);
			return ResponseEntity.ok(new RestResponse(false, "Ocurrió un error al actualizar la persona", MensajesRespuestaRest.OK.name(),false));
		}

		return ResponseEntity.ok(new RestResponse(true, "se actualiza exitosamente la persona", MensajesRespuestaRest.OK.name(),true));
	}
	
	
//	@CrossOrigin(origins = "http://localhost:4200")
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/personas/{id}")
	public ResponseEntity<RestResponse> deletePersonas(@PathVariable Integer id){
		try {
			personasRepository.deleteById(id);
		}catch(Exception e) {
			LOG.error("Ocurrió un error: ",e);
			return ResponseEntity.ok(new RestResponse(false, "Ocurrió un error al eliminar la persona", MensajesRespuestaRest.OK.name(),false));
		}

		return ResponseEntity.ok(new RestResponse(true, "se elimina exitosamente la persona", MensajesRespuestaRest.OK.name(),true));
	}
	
//	@CrossOrigin(origins = "http://localhost:4200")
	@SuppressWarnings("rawtypes")
	@GetMapping("/personas/nombre/{name}")
	public ResponseEntity<RestResponse> getByName(@PathVariable String name){
		List<Personas> personaModelList= new ArrayList<>();
		try {
			List<PerPersonas> personaEntityList=(List<PerPersonas>)personasRepository.findByName(name);
			if (personaEntityList!=null) { 
				if (personaEntityList.size()>0) {
					personaModelList=personaEntityList.stream().map(e -> modelMapper.map(e, Personas.class)).collect(Collectors.toList());
				}
			}

		}catch(Exception e) {
			LOG.error("Ocurrió un error: ",e);
			return ResponseEntity.ok(new RestResponse(false, "Ocurrió un error al obtener la persona", MensajesRespuestaRest.OK.name(),null));
		}
		return ResponseEntity.ok(new RestResponse(true, "se obtiene exitosamente la persona", MensajesRespuestaRest.OK.name(),personaModelList));
	}
}
