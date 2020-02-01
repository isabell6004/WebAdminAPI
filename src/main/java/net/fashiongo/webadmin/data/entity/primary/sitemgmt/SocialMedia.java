/**
 *
 */
package net.fashiongo.webadmin.data.entity.primary.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import net.fashiongo.webadmin.data.model.sitemgmt.SocialMediaParameter;
import net.fashiongo.webadmin.data.model.sitemgmt.SocialMediaType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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

    @Column(name = "URL")
    private String url;

    @Column(name = "Active")
    private Boolean active;

    private static String getUrl(String socialMedia) {
        String url = null;
        try {
            SocialMediaType type = SocialMediaType.valueOf(socialMedia);
            url = type.getUrl();
        } catch (IllegalArgumentException e) {
        }
        return url;
    }

    public static SocialMedia create(SocialMediaParameter request) {
        return builder()
                .socialMedia(request.getSocialMedia())
                .icon(request.getIcon())
                .active(true)
                .url(getUrl(request.getSocialMedia()))
                .build();
    }

    public void update(SocialMediaParameter request) {
        this.socialMediaId = request.getSocialMediaId();
        this.socialMedia = request.getSocialMedia();
        this.active = true;
        this.url = getUrl(request.getSocialMedia());
        this.icon = request.getIcon();
    }
}
