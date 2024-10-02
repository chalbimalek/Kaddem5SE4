package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class ContratServiceImplTest {




    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private ContratServiceImpl contratService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);

    }

    @AfterEach
    void cleanup() {
        etudiantRepository.deleteAll();
        contratRepository.deleteAll();
    }

    @Test
    void testAddContrat() {
        // Given
        Contrat contrat = new Contrat();
        contrat.setMontantContrat(1000);
        contrat.setIdContrat(1);

        // Mock the behavior of contratRepository.save
        when(contratRepository.save(contrat)).thenReturn(contrat);

        // When
        Contrat addedContrat = contratService.addContrat(contrat);

        // Then
        assertNotNull(addedContrat);
        assertEquals(contrat, addedContrat);
        verify(contratRepository, times(1)).save(contrat);
        System.out.println("Test addContrat passed!");
    }

    @Test
    void testUpdateContrat() {
        Contrat contrat = new Contrat();
        contrat.setMontantContrat(1500);
        contrat.setIdContrat(1);
        when(contratRepository.save(contrat)).thenReturn(contrat);
        Contrat contrat1 = contratService.addContrat(contrat);

        // Étape 2 : Modifier l'étudiant 1
        contrat1.setMontantContrat(1200);
        Contrat modifcontrat1 = contratService.updateContrat(contrat1);

        // Assertion to verify that the montantContrat has been modified
        assertEquals(1200, modifcontrat1.getMontantContrat());

        System.err.println("Étape 2 : Modification de contrat 1");
        System.err.println(modifcontrat1);
        System.out.println("Test updateContrat passed!");

    }


    @Test
    void testRetrieveContrat() {
        // Given
        Contrat contrat = new Contrat();
        contrat.setMontantContrat(1000);
        contrat.setIdContrat(1);

        // When
        when(contratRepository.save(contrat)).thenReturn(contrat);
        Contrat addedContrat = contratService.addContrat(contrat);

        // When
        when(contratRepository.findById(addedContrat.getIdContrat())).thenReturn(java.util.Optional.of(addedContrat));
        Contrat retrievedContrat = contratService.retrieveContrat(addedContrat.getIdContrat());

        // Then
        assertNotNull(retrievedContrat);
        assertEquals(addedContrat, retrievedContrat);
        verify(contratRepository, times(1)).findById(addedContrat.getIdContrat());
        System.out.println(addedContrat);
        System.out.println("Test retrieveContrat passed!");

    }

    @Test
    void testRemoveContrat() {
        // Given
        Contrat contrat = new Contrat();
        contrat.setMontantContrat(1000);
        contrat.setIdContrat(1);

        // When
        when(contratRepository.save(contrat)).thenReturn(contrat);
        Contrat addedContrat = contratService.addContrat(contrat);

        // When
        contratService.removeContrat(addedContrat.getIdContrat());

        // Then
        verify(contratRepository, times(1)).deleteById(addedContrat.getIdContrat());
        System.out.println("Test removeContrat passed!");

    }

    @Test
    void testAffectContratToEtudiant() {
        // Arrange
        Etudiant etudiant = new Etudiant();
        Contrat contrat = new Contrat();
        when(etudiantRepository.findByNomEAndPrenomE("John", "Doe")).thenReturn(etudiant);
        when(contratRepository.findByIdContrat(1)).thenReturn(contrat);
        when(contratRepository.save(contrat)).thenReturn(contrat);

        // Act
        Contrat result = contratService.affectContratToEtudiant(1, "John", "Doe");

        // Assert
        assertNotNull(result);
        verify(etudiantRepository, times(1)).findByNomEAndPrenomE("John", "Doe");
        verify(contratRepository, times(1)).findByIdContrat(1);
        verify(contratRepository, times(1)).save(contrat);
    }

    // Ajoutez d'autres tests unitaires pour les autres méthodes...


}