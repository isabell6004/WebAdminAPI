package net.fashiongo.webadmin.dao.photostudio;

import net.fashiongo.webadmin.model.photostudio.LogPhotoAction;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LogPhotoActionRepository extends CrudRepository<LogPhotoAction, Integer>, LogPhotoActionQuerydslCustom {


}
