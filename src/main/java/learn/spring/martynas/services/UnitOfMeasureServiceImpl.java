package learn.spring.martynas.services;

import learn.spring.martynas.commands.UnitOfMeasureCommand;
import learn.spring.martynas.converters.UnitOfMeasureToUnitOfMeasureCommand;
import learn.spring.martynas.repositories.UOMRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
    private final UOMRepository uomRepository;

    public UnitOfMeasureServiceImpl(UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand, UOMRepository uomRepository) {
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
        this.uomRepository = uomRepository;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUom() {
        return StreamSupport.stream(uomRepository.findAll().spliterator(), false).map(unitOfMeasureToUnitOfMeasureCommand::convert).
        collect(Collectors.toSet());
    }
}
