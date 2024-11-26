package dci.j24e1.group1.demotestmap.service;

import dci.j24e1.group1.demotestmap.model.VacationPoint;
import dci.j24e1.group1.demotestmap.repository.VacationPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private VacationPointRepository vacationPointRepository;


    public List<VacationPoint> getAllVacationPoints() {
        return vacationPointRepository.findAll();
    }


    public void deleteVacationPoint(Long id) {
        vacationPointRepository.deleteById(id);
    }
}
