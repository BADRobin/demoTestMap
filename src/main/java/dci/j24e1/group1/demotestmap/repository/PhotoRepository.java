package dci.j24e1.group1.demotestmap.repository;


import dci.j24e1.group1.demotestmap.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
