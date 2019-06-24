package net.fashiongo.webadmin.model.primary;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsRecipient {
    private NewsEntity news;

    private String recipient;
}
