package com.example.wsbp.service;

import com.example.wsbp.data.Unit;
import com.example.wsbp.repository.IUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitService implements IUnitService {
    private IUnitRepository unitRepository;

    @Autowired
    public UnitService(IUnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @Override
    public List<Unit> findUnits() {
        var units = unitRepository.find();
        System.out.println("データ件数:" + units.size());
        return units;
    }
}
