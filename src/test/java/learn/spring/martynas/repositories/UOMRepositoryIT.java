package learn.spring.martynas.repositories;

import learn.spring.martynas.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UOMRepositoryIT {

    @Autowired
    UOMRepository uomRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByDescription() {

        Optional<UnitOfMeasure> unitOfMeasure = uomRepository.findByDescription("Teaspoon");

        assertEquals("Teaspoon", unitOfMeasure.get().getUom());
    }
}