package org.springframework.samples.petclinic.owner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.samples.petclinic.owner.OwnerRepository;
import org.springframework.samples.petclinic.owner.PetType;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PetControllerPopulatePetTypesTest {

    @Mock
    private OwnerRepository owners;

    private PetController petController;

    @Before
    public void setup() {
        petController = new PetController(owners);
    }

    @Test
    public void testSuccessfulRetrievalOfPetTypes() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType cat = new PetType();
        cat.setName("Cat");
        when(owners.findPetTypes()).thenReturn(Arrays.asList(dog, cat));

        Collection<PetType> petTypes = petController.populatePetTypes();

        assertEquals(2, petTypes.size());
        assertTrue(petTypes.containsAll(Arrays.asList(dog, cat)));
    }

    @Test
    public void testEmptyPetTypesCollection() {
        when(owners.findPetTypes()).thenReturn(Collections.emptyList());

        Collection<PetType> petTypes = petController.populatePetTypes();

        assertTrue(petTypes.isEmpty());
    }

    @Test(expected = RuntimeException.class)
    public void testFindPetTypesThrowsException() {
        when(owners.findPetTypes()).thenThrow(new RuntimeException());

        petController.populatePetTypes();
    }
}
