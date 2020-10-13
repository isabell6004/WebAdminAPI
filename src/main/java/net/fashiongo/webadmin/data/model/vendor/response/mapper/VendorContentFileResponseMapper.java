package net.fashiongo.webadmin.data.model.vendor.response.mapper;

import net.fashiongo.webadmin.data.model.vendor.response.VendorContentFileResponse;
import net.fashiongo.webadmin.model.primary.VendorContentFile;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class VendorContentFileResponseMapper {
    public static VendorContentFileResponse create(VendorContentFile file) {
        return VendorContentFileResponse.builder()
                .vendorContentFileId(file.getVendorContentFileId())
                .vendorContentId(file.getVendorContentId())
                .fileType(file.getFileType())
                .fileName(file.getFileName())
                .listOrder(file.getListOrder())
                .build();
    }

    public static List<VendorContentFileResponse> create(List<VendorContentFile> files) {
        if (CollectionUtils.isEmpty(files))
            return Collections.emptyList();

        return files.stream().map(VendorContentFileResponseMapper::create).collect(Collectors.toList());
    }
}
