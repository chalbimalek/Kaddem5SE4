package tn.esprit.spring.kaddem.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.services.IUniversiteService;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/universite")
public class UniversiteRestController {
private  final 	IUniversiteService universiteService;
	@GetMapping("/retrieve-all-universites")
	public List<Universite> getUniversites() {
		return universiteService.retrieveAllUniversites();
	}
	@GetMapping("/retrieve-universite/{universite-id}")
	public Universite retrieveUniversite(@PathVariable("universite-id") Integer universiteId) {
		return universiteService.retrieveUniversite(universiteId);
	}

	@PostMapping("/add-universite")
	public Universite addUniversite(@RequestBody Universite u) {
		return universiteService.addUniversite(u);
	}

	// http://localhost:8089/Kaddem/universite/remove-universite/1
	@DeleteMapping("/remove-universite/{universite-id}")
	public void removeUniversite(@PathVariable("universite-id") Integer universiteId) {
		universiteService.deleteUniversite(universiteId);
	}

	// http://localhost:8089/Kaddem/universite/update-universite
	@PutMapping("/update-universite")
	public Universite updateUniversite(@RequestBody Universite u) {
		return universiteService.updateUniversite(u);
	}

	//@PutMapping("/affecter-etudiant-departement")
	@PutMapping(value="/affecter-universite-departement/{universiteId}/{departementId}")
	public void affectertUniversiteToDepartement(@PathVariable("universiteId") Integer universiteId, @PathVariable("departementId")Integer departementId){
		universiteService.assignUniversiteToDepartement(universiteId, departementId);
	}

	@GetMapping(value = "/listerDepartementsUniversite/{idUniversite}")
	public Set<Departement> listerDepartementsUniversite(@PathVariable("idUniversite") Integer idUniversite) {

		return universiteService.retrieveDepartementsByUniversite(idUniversite);
	}

}


