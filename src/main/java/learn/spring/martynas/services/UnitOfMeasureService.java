package learn.spring.martynas.services;

import learn.spring.martynas.commands.UnitOfMeasureCommand;
import learn.spring.martynas.domain.UnitOfMeasure;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUom();
}
