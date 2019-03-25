package net.fashiongo.webadmin.model.photostudio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.common.conversion.LocalDateTimeConverter;
import net.fashiongo.common.dal.IPersistent;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class LogPhotoActionDto {

	private Integer actionID;

	private Integer actionType;

	private String droppedBy;

	private LocalDateTime createdOnDate;

	public String getCreatedOn() {
		return createdOnDate != null ? createdOnDate.toString() : null;
	}

    public static List<LogPhotoActionDto> build(List<LogPhotoAction> logPhotoActions) {


        if(CollectionUtils.isEmpty(logPhotoActions)) {
            return new ArrayList<>();
        }

        List<LogPhotoActionDto> results = new ArrayList<>();
        for(LogPhotoAction logPhotoAction : logPhotoActions) {
            results.add(
                    builder().actionID(logPhotoAction.getActionID())
                            .actionType(logPhotoAction.getActionType())
                            .droppedBy(logPhotoAction.getDroppedBy())
                            .createdOnDate(logPhotoAction.getCreatedOnDate())
                            .build()
            );
        }

        return results;

    }
}
