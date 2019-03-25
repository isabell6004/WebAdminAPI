/**
 * 
 */
package net.fashiongo.webadmin.model.photostudio;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.webadmin.model.primary.SecurityUser;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XiaoChuan.Gao
 *  PhotoActionUser: Photo Action User
 */
@Getter
@Setter
@Builder
public class PhotoActionUser {
	
	private Integer userID;

	private String userName;

    public static List<PhotoActionUser> build(List<SecurityUser> securityUsers) {

        if(CollectionUtils.isEmpty(securityUsers)){
           return new ArrayList<>();
        }

        List<PhotoActionUser> photoActionUsers = new ArrayList<>();
        for(SecurityUser user : securityUsers) {
            PhotoActionUser photoActionUser = builder().userID(user.getUserID()).userName(user.getUserName()).build();
            photoActionUsers.add(photoActionUser);
        }

        return photoActionUsers;
    }
}
