package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.webadmin.data.model.ad.response.GetSecurityAccessCodesResponse;
import net.fashiongo.webadmin.data.repository.primary.SecurityAccessCodeEntityRepository;
import net.fashiongo.webadmin.model.pojo.admin.SecurityAccessCodes;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetSecurityAccessCodesParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RenewalAdminService {
    private final SecurityAccessCodeEntityRepository securityAccessCodeEntityRepository;

    @Autowired
    public RenewalAdminService(SecurityAccessCodeEntityRepository securityAccessCodeEntityRepository) {
        this.securityAccessCodeEntityRepository = securityAccessCodeEntityRepository;
    }

    public GetSecurityAccessCodesResponse getSecurityAccessCodes(GetSecurityAccessCodesParameters parameters) {
        String accessCode = parameters.getAccessCode();
        LocalDateTime startDate = LocalDate.parse(parameters.getsDate()).atTime(0,0,0,0);
        LocalDateTime endDate = LocalDate.parse(parameters.geteDate()).atTime(0,0,0,0);

        return GetSecurityAccessCodesResponse.builder()
                .securityAccessCodes(securityAccessCodeEntityRepository.findAllAccessCode(accessCode, startDate, endDate))
                .build();
    }
}
