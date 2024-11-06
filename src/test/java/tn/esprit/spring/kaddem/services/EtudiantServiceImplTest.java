package tn.esprit.spring.kaddem.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

@ExtendWith(MockitoExtension.class)
class EtudiantServiceImplTest {

    @InjectMocks
    EtudiantServiceImpl etudiantService;

    @Mock
    EtudiantRepository etudiantRepository;

    @Mock
    ContratRepository contratRepository;

    @Mock
    EquipeRepository equipeRepository;

    @Mock
    DepartementRepository departementRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllEtudiants() {
        // Arrange
        Etudiant etudiant1 = new Etudiant();
        Etudiant etudiant2 = new Etudiant();
        when(etudiantRepository.findAll()).thenReturn(Arrays.asList(etudiant1, etudiant2));

        // Act
        List<Etudiant> result = etudiantService.retrieveAllEtudiants();

        // Assert
        assertEquals(2, result.size());
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    void testAddEtudiant() {
        // Arrange
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        // Act
        Etudiant result = etudiantService.addEtudiant(etudiant);

        // Assert
        assertNotNull(result);
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void testUpdateEtudiant() {
        // Arrange
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        // Act
        Etudiant result = etudiantService.updateEtudiant(etudiant);

        // Assert
        assertNotNull(result);
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void testRetrieveEtudiant() {
        // Arrange
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.findById(anyInt())).thenReturn(Optional.of(etudiant));

        // Act
        Etudiant result = etudiantService.retrieveEtudiant(1);

        // Assert
        assertNotNull(result);
        verify(etudiantRepository, times(1)).findById(1);
    }




    @Test
    void testRemoveEtudiant() {
        // Arrange
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.findById(anyInt())).thenReturn(Optional.of(etudiant));
        doNothing().when(etudiantRepository).delete(etudiant);

        // Act
        etudiantService.removeEtudiant(1);

        // Assert
        verify(etudiantRepository, times(1)).delete(etudiant);
    }



    @Test
    void testAssignEtudiantToDepartement() {
        // Arrange
        Etudiant etudiant = new Etudiant();
        Departement departement = new Departement();
        when(etudiantRepository.findById(anyInt())).thenReturn(Optional.of(etudiant));
        when(departementRepository.findById(anyInt())).thenReturn(Optional.of(departement));
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        // Act
        etudiantService.assignEtudiantToDepartement(1, 1);

        // Assert
        assertEquals(departement, etudiant.getDepartement());
        verify(etudiantRepository, times(1)).save(etudiant);
    }







    @Test
    void testGetEtudiantsByDepartement() {
        // Arrange
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.findEtudiantsByDepartement_IdDepart(anyInt())).thenReturn(Arrays.asList(etudiant));

        // Act
        List<Etudiant> result = etudiantService.getEtudiantsByDepartement(1);

        // Assert
        assertEquals(1, result.size());
        verify(etudiantRepository, times(1)).findEtudiantsByDepartement_IdDepart(1);
    }
}
