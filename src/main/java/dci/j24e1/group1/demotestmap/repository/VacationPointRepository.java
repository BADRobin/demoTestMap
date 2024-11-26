package dci.j24e1.group1.demotestmap.repository;


import dci.j24e1.group1.demotestmap.model.VacationPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationPointRepository extends JpaRepository<VacationPoint, Long> {
}