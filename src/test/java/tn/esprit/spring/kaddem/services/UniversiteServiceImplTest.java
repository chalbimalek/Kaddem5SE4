package tn.esprit.spring.kaddem.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;

class UniversiteServiceImplTest {

    @InjectMocks
    UniversiteServiceImpl universiteService;

    @Mock
    UniversiteRepository universiteRepository;

    @Mock
    DepartementRepository departementRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllUniversites() {
        // Arrange
        Universite universite1 = new Universite();
        Universite universite2 = new Universite();
        when(universiteRepository.findAll()).thenReturn(Arrays.asList(universite1, universite2));

        // Act
        var result = universiteService.retrieveAllUniversites();

        // Assert
        assertEquals(2, result.size());
        verify(universiteRepository, times(1)).findAll();
    }

    @Test
    void testAddUniversite() {
        // Arrange
        Universite universite = new Universite();
        when(universiteRepository.save(any(Universite.class))).thenReturn(universite);

        // Act
        Universite result = universiteService.addUniversite(universite);

        // Assert
        assertNotNull(result);
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testUpdateUniversite() {
        // Arrange
        Universite universite = new Universite();
        when(universiteRepository.save(any(Universite.class))).thenReturn(universite);

        // Act
        Universite result = universiteService.updateUniversite(universite);

        // Assert
        assertNotNull(result);
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testRetrieveUniversite() {
        // Arrange
        Universite universite = new Universite();
        when(universiteRepository.findById(anyInt())).thenReturn(Optional.of(universite));

        // Act
        Universite result = universiteService.retrieveUniversite(1);

        // Assert
        assertNotNull(result);
        verify(universiteRepository, times(1)).findById(1);
    }

    @Test
    void testAssignUniversiteToDepartement() {
        // Arrange
        Universite universite = new Universite();
        universite.setDepartements(new HashSet<>()); // Initialize departements
        Departement departement = new Departement();
        when(universiteRepository.findById(anyInt())).thenReturn(Optional.of(universite));
        when(departementRepository.findById(anyInt())).thenReturn(Optional.of(departement));

        // Act
        universiteService.assignUniversiteToDepartement(1, 1);

        // Assert
        assertTrue(universite.getDepartements().contains(departement));
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testRetrieveDepartementsByUniversite() {
        // Arrange
        Departement departement = new Departement();
        Universite universite = new Universite();
        universite.setDepartements(new HashSet<>(Set.of(departement)));
        when(universiteRepository.findById(anyInt())).thenReturn(Optional.of(universite));

        // Act
        Set<Departement> result = universiteService.retrieveDepartementsByUniversite(1);

        // Assert
        assertEquals(1, result.size());
        verify(universiteRepository, times(1)).findById(1);
    }

    // New test to handle the case where the universite is not found
    @Test
    void testRetrieveUniversiteNotFound() {
        // Arrange
        when(universiteRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> {
            universiteService.retrieveUniversite(1); // Should throw NoSuchElementException
        });
    }
}
