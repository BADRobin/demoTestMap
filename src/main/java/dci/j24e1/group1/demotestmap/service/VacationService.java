package dci.j24e1.group1.demotestmap.service;



import dci.j24e1.group1.demotestmap.model.Photo;
import dci.j24e1.group1.demotestmap.model.Route;
import dci.j24e1.group1.demotestmap.model.VacationPoint;
import dci.j24e1.group1.demotestmap.repository.PhotoRepository;
import dci.j24e1.group1.demotestmap.repository.RouteRepository;
import dci.j24e1.group1.demotestmap.repository.VacationPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class VacationService {

    @Autowired
    private VacationPointRepository vacationPointRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private RouteRepository routeRepository;

    public List<VacationPoint> getAllVacationPoints() {
        return vacationPointRepository.findAll();
    }

    public void saveVacationPoint(String title, String description, double latitude, double longitude, MultipartFile[] photos, String routeGeoJson) {
        VacationPoint vacationPoint = new VacationPoint();
        vacationPoint.setTitle(title);
        vacationPoint.setDescription(description);
        vacationPoint.setLatitude(latitude);
        vacationPoint.setLongitude(longitude);

        VacationPoint savedPoint = vacationPointRepository.save(vacationPoint);

        if (photos != null) {
            List<Photo> photoList = new ArrayList<>();
            for (MultipartFile photo : photos) {
                try {
                    String filePath = "uploads/" + photo.getOriginalFilename();
                    photo.transferTo(new File(filePath));

                    Photo photoEntity = new Photo();
                    photoEntity.setPhotoPath(filePath);
                    photoEntity.setVacationPoint(savedPoint);
                    photoList.add(photoEntity);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            photoRepository.saveAll(photoList);
        }

        if (routeGeoJson != null && !routeGeoJson.isEmpty()) {
            Route route = new Route();
            route.setGeoJson(routeGeoJson);
            route.setVacationPoint(savedPoint);
            routeRepository.save(route);
        }
    }
    public void deleteVacationPoint(Long id) {
        vacationPointRepository.deleteById(id);
    }

}
