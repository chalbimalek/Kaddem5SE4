package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import java.util.*;

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
    private List<Contrat> contratList;
    private  Contrat contrat;
    @BeforeEach
    public void setUp() {
        Contrat contrat1 = new Contrat();
        contrat1.setIdContrat(1);
        contrat1.setMontantContrat(5);

        Contrat contrat2 = new Contrat();
        contrat2.setIdContrat(2);
        contrat2.setMontantContrat(22);

        contratList = Arrays.asList(contrat1, contrat2);
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
    public void testRetrieveAllContrats() {
        when(contratRepository.findAll()).thenReturn(contratList);

        List<Contrat> result = contratService.retrieveAllContrats();

        assertEquals(2, result.size());
        assertEquals(5, result.get(0).getMontantContrat());
        assertEquals(22, result.get(1).getMontantContrat());
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

    @Test
    void testRemoveContrat() {
        when(contratRepository.findById(1)).thenReturn(java.util.Optional.of(contrat));

        contratService.removeContrat(1);

        verify(contratRepository, times(1)).delete(contrat);
        System.out.println("Test removeContrat passed!");
    }

    @Test
    void testAffectContratToEtudiant() {
        String nomE = "Doe";
        String prenomE = "John";

        Etudiant etudiant = new Etudiant();
        etudiant.setNomE(nomE);
        etudiant.setPrenomE(prenomE);
        etudiant.setContrats(new HashSet<>());

        when(etudiantRepository.findByNomEAndPrenomE(nomE, prenomE)).thenReturn(etudiant);
        when(contratRepository.findByIdContrat(1)).thenReturn(contrat);

        Contrat updatedContrat = contratService.affectContratToEtudiant(1, nomE, prenomE);

        assertNotNull(updatedContrat);
        assertEquals(etudiant, updatedContrat.getEtudiant());
        System.out.println("Test affectContratToEtudiant passed!");
    }

    @Test
    void testNbContratsValides() {
        Date startDate = new Date();
        Date endDate = new Date();

        when(contratRepository.getnbContratsValides(startDate, endDate)).thenReturn(5);

        Integer result = contratService.nbContratsValides(startDate, endDate);

        assertEquals(5, result);
        verify(contratRepository, times(1)).getnbContratsValides(startDate, endDate);
        System.out.println("Test nbContratsValides passed!");
    }


}