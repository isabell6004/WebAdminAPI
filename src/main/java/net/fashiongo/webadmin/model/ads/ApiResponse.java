package net.fashiongo.webadmin.model.ads;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

@Getter
public class ApiResponse<T> {

    public static final ApiResponse<Void> SIMPLE_SUCCESS = new ApiResponse<>(ErrorCode.COMMON_SUCCESS, "", null);
    public static final ApiResponse<Void> SIMPLE_FAILURE = new ApiResponse<>(ErrorCode.COMMON_ERROR, "", null);

    private ApiHeader header;
    private T data;

    private ApiResponse() {
        // create private default constructor because object mapper needs the default constructor to deserialize.
    }

    public ApiResponse(ErrorCode errorcode, String message) {
        this(errorcode, message, null);
    }

    public ApiResponse(ErrorCode errorCode, String message, T data) {
        Assert.notNull(errorCode, "errorCode must not be null");
        Assert.notNull(message, "message must not be null");

        this.header = new ApiHeader(errorCode, message);
        this.data = data;
    }

    // Single ObjectResponse without ReferencesMap
    public static <T> ApiResponse<T> contentOf(T t) {
        Content<T> content = new Content<>(t);
        return new ApiResponse(ErrorCode.COMMON_SUCCESS, "", content);
    }

    // Single ObjectResponse with ReferencesMap
    public static <T> ApiResponse<T> contentOf(T t, Map references) {
        Content<T> content = new Content<>(t, references);
        return new ApiResponse(ErrorCode.COMMON_SUCCESS, "", content);
    }

    @Getter
    private static class Content<T> {
        private T content;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private Map references;

        public Content(T content) {
            this.content = content;
            this.references = null;
        }

        public Content(T content, Map references) {
            this.content = content;
            this.references = references;
        }
    }

    @Getter
    public static class Contents<T> {
        @JsonSerialize(using = ToStringSerializer.class)
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private Long totalCount;

        private List<T> contents;

        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private Map references;

        public Contents(List<T> contents, Long totalCount) {
            this.totalCount = totalCount;
            this.contents = contents;
            this.references = null;
        }

        public Contents(List<T> contents, Long totalCount, Map references) {
            this.totalCount = totalCount;
            this.contents = contents;
            this.references = references;
        }

        public Contents(List<T> contents, Map references) {
            this.contents = contents;
            this.references = references;
            this.totalCount = null;
        }

        public Contents(List<T> contents) {
            this.contents = contents;
            this.references = null;
            this.totalCount = null;
        }

        private Contents() {
            // create private default constructor because object mapper needs the default constructor to deserialize.
        }
    }
}
