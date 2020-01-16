/**
 *
 */
package net.fashiongo.webadmin.data.entity.primary.sitemgmt;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import net.fashiongo.webadmin.data.model.sitemgmt.SocialMediaParameter;

/**
 * @author roy
 *
 */
@Entity
@Table(name = "List_SocialMedia")
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SocialMedia implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "SocialMediaID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("SocialMediaID")
    @ApiModelProperty(example = "0")
    private Integer socialMediaId;

    @NotNull
    @Column(name = "SocialMedia")
    @JsonProperty("SocialMedia")
    @ApiModelProperty(example = "SNS Name")
    private String socialMedia;

    @Column(name = "ListOrder")
    @JsonProperty("ListOrder")
    @ApiModelProperty(hidden = true)
    private Integer listOrder;

    @Column(name = "Icon")
    @JsonProperty("Icon")
    @ApiModelProperty(example = "fb.png")
    private String icon;

    public static SocialMedia create(SocialMediaParameter request) {

        return builder()
                .socialMediaId(request.getSocialMediaId())
                .socialMedia(request.getSocialMedia())
                .icon(request.getIcon())
                .build();
    }

    public void update(SocialMediaParameter request) {
        this.socialMediaId = request.getSocialMediaId();
        this.socialMedia = request.getSocialMedia();
        this.icon = request.getIcon();
    }
}
