package tn.esprit.spring.kaddem.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class DepartementServiceImpl implements IDepartementService{

	private  final	DepartementRepository departementRepository;
	public List<Departement> retrieveAllDepartements(){
		return (List<Departement>) departementRepository.findAll();
	}

	public Departement addDepartement (Departement d){
		return departementRepository.save(d);
	}

	public   Departement updateDepartement (Departement d){
		return departementRepository.save(d);
	}

	public Departement retrieveDepartement(Integer idDepart) {
		Optional<Departement> departement = departementRepository.findById(idDepart);
        return departement.orElse(null);
	}

	public  void deleteDepartement(Integer idDepartement){
		Departement d=retrieveDepartement(idDepartement);
		departementRepository.delete(d);
	}



}
