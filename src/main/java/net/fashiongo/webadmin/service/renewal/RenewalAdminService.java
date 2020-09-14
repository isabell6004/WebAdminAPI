package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.webadmin.data.model.admin.*;
import net.fashiongo.webadmin.data.model.admin.response.*;
import net.fashiongo.webadmin.data.repository.primary.SecurityAccessCodeEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.SecurityListIPEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.SecurityLoginLogEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.SecurityResourceEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.procedure.GetUserLoginTrackingProcedure;
import net.fashiongo.webadmin.data.repository.primary.procedure.PrimaryProcedureRepository;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetSecurityAccessCodesParameters;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetSecurityLogsParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetSecurityMenus2Parameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetSecurityResourcesParameter;
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

    private final SecurityResourceEntityRepository securityResourceEntityRepository;

    private final PrimaryProcedureRepository primaryProcedureRepository;

    private final GetUserLoginTrackingProcedure userLoginTrackingProcedure;

    @Autowired
    public RenewalAdminService(SecurityAccessCodeEntityRepository securityAccessCodeEntityRepository, SecurityLoginLogEntityRepository securityLoginLogEntityRepository, SecurityListIPEntityRepository securityListIPEntityRepository, SecurityResourceEntityRepository securityResourceEntityRepository, PrimaryProcedureRepository primaryProcedureRepository, GetUserLoginTrackingProcedure userLoginTrackingProcedure) {

        this.securityAccessCodeEntityRepository = securityAccessCodeEntityRepository;
        this.securityLoginLogEntityRepository = securityLoginLogEntityRepository;
        this.securityListIPEntityRepository = securityListIPEntityRepository;
        this.securityResourceEntityRepository = securityResourceEntityRepository;
        this.primaryProcedureRepository = primaryProcedureRepository;
        this.userLoginTrackingProcedure = userLoginTrackingProcedure;
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

    public GetSecurityResourcesResponse getSecurityResources(GetSecurityResourcesParameter parameters) {

        String application = parameters.getApplication();
        String resourceName = parameters.getResourceName();
        String resourceParent = parameters.getResourceParent();
        String resourceType = parameters.getResourceType();

        List<Resource> resourceList = securityResourceEntityRepository.up_wa_Security_GetResource(application, resourceName, resourceParent, resourceType);

        return GetSecurityResourcesResponse.builder()
                .resource(resourceList)
                .build();
    }

    @Transactional(transactionManager = "primaryTransactionManager")
    public GetSecurityMenus2Response GetSecurityMenus2(GetSecurityMenus2Parameter parameters) {

        String menuname = parameters.getMenuname();
        Integer parentmenuid = Integer.valueOf(parameters.getParentmenuid());
        Integer applicationid = Integer.valueOf(parameters.getApplicationid());
        Integer active = parameters.getActive();

        List<SecurityMenus2> securityMenus2s = primaryProcedureRepository.up_wa_GetSecurityMenus2(menuname, parentmenuid, applicationid, active);

        return GetSecurityMenus2Response.builder()
                .securityMenus2(securityMenus2s)
                .build();
    }

    public GetUserLoginTrackingResponse getUserLoginTracking(int pageNum, int pageSize, String sortField, String sortDir, String userType, String userName, String companyName, String ip, LocalDateTime sDate, LocalDateTime eDate) {
        return userLoginTrackingProcedure.up_wa_GetUserLoginTracking(pageNum,pageSize,sortField,sortDir,userType,userName,companyName,ip,sDate,eDate);
    }
}
