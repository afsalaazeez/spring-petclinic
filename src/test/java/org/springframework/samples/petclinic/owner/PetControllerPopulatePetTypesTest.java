package org.springframework.samples.petclinic.owner;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class PetControllerPopulatePetTypesTest {

    @InjectMocks
    PetController petController;

    @Mock
    OwnerRepository owners;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSuccessfulRetrievalOfPetTypes() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType cat = new PetType();
        cat.setName("Cat");
        when(owners.findPetTypes()).thenReturn(Arrays.asList(dog, cat));
        Collection<PetType> petTypes = petController.populatePetTypes();
        assertNotNull(petTypes);
        assertEquals(2, petTypes.size());
        assertTrue(petTypes.containsAll(Arrays.asList(dog, cat)));
    }

    @Test
    public void testEmptyCollectionOfPetTypes() {
        when(owners.findPetTypes()).thenReturn(Collections.emptyList());
        Collection<PetType> petTypes = petController.populatePetTypes();
        assertNotNull(petTypes);
        assertTrue(petTypes.isEmpty());
    }

    @Test
    public void testNullCollectionOfPetTypes() {
        when(owners.findPetTypes()).thenReturn(null);
        Collection<PetType> petTypes = petController.populatePetTypes();
        assertNull(petTypes);
    }
}
