package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.webadmin.data.entity.primary.SecurityListIPEntity;
import net.fashiongo.webadmin.data.model.admin.SecurityListIP;
import net.fashiongo.webadmin.data.model.admin.SecurityLoginLogs;
import net.fashiongo.webadmin.data.model.admin.SecurityLogsColumn;
import net.fashiongo.webadmin.data.model.admin.response.GetSecurityAccessCodesResponse;
import net.fashiongo.webadmin.data.model.admin.response.GetSecurityAccessIpsResponse;
import net.fashiongo.webadmin.data.model.admin.response.GetSecurityLogsResponse;
import net.fashiongo.webadmin.data.repository.primary.SecurityAccessCodeEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.SecurityListIPEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.SecurityLoginLogEntityRepository;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetSecurityAccessCodesParameters;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetSecurityLogsParameter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RenewalAdminService {
    private final SecurityAccessCodeEntityRepository securityAccessCodeEntityRepository;

    private final SecurityLoginLogEntityRepository securityLoginLogEntityRepository;

    private final SecurityListIPEntityRepository securityListIPEntityRepository;

    @Autowired
    public RenewalAdminService(SecurityAccessCodeEntityRepository securityAccessCodeEntityRepository, SecurityLoginLogEntityRepository securityLoginLogEntityRepository, SecurityListIPEntityRepository securityListIPEntityRepository) {
        this.securityAccessCodeEntityRepository = securityAccessCodeEntityRepository;
        this.securityLoginLogEntityRepository = securityLoginLogEntityRepository;
        this.securityListIPEntityRepository = securityListIPEntityRepository;
    }

    @Transactional(transactionManager = "primaryTransactionManager")
    public GetSecurityAccessCodesResponse getSecurityAccessCodes(GetSecurityAccessCodesParameters parameters) {
        String accessCode = parameters.getAccessCode();
        LocalDateTime startDate = LocalDate.parse(parameters.getsDate()).atTime(0,0,0,0);
        LocalDateTime endDate = LocalDate.parse(parameters.geteDate()).atTime(0,0,0,0);

        return GetSecurityAccessCodesResponse.builder()
                .securityAccessCodes(securityAccessCodeEntityRepository.findAllAccessCode(accessCode, startDate, endDate))
                .build();
    }

    @Transactional(transactionManager = "primaryTransactionManager")
    public GetSecurityLogsResponse getSecurityLoginLogs(GetSecurityLogsParameter parameters) {

        String startDateValue = parameters.getSdate();
        String endDateValue = parameters.getEdate();

        LocalDateTime startDate = StringUtils.isEmpty(startDateValue) ? null : LocalDate.parse(startDateValue, DateTimeFormatter.ofPattern("M/dd/yyyy")).atTime(0,0,0,0);
        LocalDateTime endDate = StringUtils.isEmpty(endDateValue) ? null : LocalDate.parse(endDateValue, DateTimeFormatter.ofPattern("M/dd/yyyy")).atTime(0,0,0,0);

        long recCnt = securityLoginLogEntityRepository.findAllCount(startDate,endDate,parameters.getUsrid(), parameters.getIp());

        List<SecurityLogsColumn> result2 = Collections.singletonList(recCnt).stream().map(q -> new SecurityLogsColumn(q.intValue())).collect(Collectors.toList());

        List<SecurityLoginLogs> result = securityLoginLogEntityRepository.
                findAllLimitOffset(startDate, endDate, parameters.getUsrid(), parameters.getIp(), parameters.getPagenum(), parameters.getPagesize(), recCnt);

        return GetSecurityLogsResponse.builder()
                .securityLoginLogs(result)
                .securityLogsColumn(result2)
                .build();
    }

    @Transactional(transactionManager = "primaryTransactionManager")
    public GetSecurityAccessIpsResponse getSecurityAccessIps() {
        List<SecurityListIP> securityListIP = securityListIPEntityRepository.findAllOrderByIPID();

        return GetSecurityAccessIpsResponse.builder()
                .securityListIP(securityListIP)
                .build();
    }
}
