package net.fashiongo.webadmin.dao.photostudio;

import net.fashiongo.webadmin.model.photostudio.LogPhotoAction;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface LogPhotoActionCustom {

    List<LogPhotoAction> getLogPhotoActions(Integer orderID);

}
