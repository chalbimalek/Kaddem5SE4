package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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



    @AfterEach
    void cleanup() {
        etudiantRepository.deleteAll();
        contratRepository.deleteAll();
    }

    @Test
    void testAddContrat() {
        Contrat contrat = new Contrat();
        contrat.setMontantContrat(1000);
        contrat.setIdContrat(1);

        when(contratRepository.save(contrat)).thenReturn(contrat);

        Contrat addedContrat = contratService.addContrat(contrat);

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

        contrat1.setMontantContrat(1200);
        Contrat modifcontrat1 = contratService.updateContrat(contrat1);

        assertEquals(1200, modifcontrat1.getMontantContrat());

        System.err.println("Étape 2 : Modification de contrat 1");
        System.err.println(modifcontrat1);
        System.out.println("Test updateContrat passed!");

    }


    @Test
    void testRetrieveContrat() {
        Contrat contrat = new Contrat();
        contrat.setMontantContrat(1000);
        contrat.setIdContrat(1);

        when(contratRepository.save(contrat)).thenReturn(contrat);
        Contrat addedContrat = contratService.addContrat(contrat);

        when(contratRepository.findById(addedContrat.getIdContrat())).thenReturn(java.util.Optional.of(addedContrat));
        Contrat retrievedContrat = contratService.retrieveContrat(addedContrat.getIdContrat());

        assertNotNull(retrievedContrat);
        assertEquals(addedContrat, retrievedContrat);
        verify(contratRepository, times(1)).findById(addedContrat.getIdContrat());
        System.out.println(addedContrat);
        System.out.println("Test retrieveContrat passed!");

    }


}