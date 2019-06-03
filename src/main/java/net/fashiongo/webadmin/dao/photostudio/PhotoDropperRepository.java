package net.fashiongo.webadmin.dao.photostudio;

import net.fashiongo.webadmin.model.photostudio.PhotoDropper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoDropperRepository extends JpaRepository<PhotoDropper, Integer> {
    List<PhotoDropper> findAllByWholeSalerId(int wholeSalerId);
}
