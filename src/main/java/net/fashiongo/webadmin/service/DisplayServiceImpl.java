package net.fashiongo.webadmin.service;

import net.fashiongo.webadmin.dao.primary.GnbMenuCollectionRepository;
import net.fashiongo.webadmin.dao.primary.GnbVendorGroupMapRepository;
import net.fashiongo.webadmin.dao.primary.GnbVendorGroupRepository;
import net.fashiongo.webadmin.dao.primary.SiteSettingRepository;
import net.fashiongo.webadmin.data.entity.primary.*;
import net.fashiongo.webadmin.exception.NotFoundGnbVendorGroup;
import net.fashiongo.webadmin.exception.NotFoundSiteSetting;
import net.fashiongo.webadmin.model.pojo.request.GnbCollectionSaveRequest;
import net.fashiongo.webadmin.model.pojo.request.GnbVendorGroupSaveRequest;
import net.fashiongo.webadmin.model.pojo.response.GnbCollectionInfoResponse;
import net.fashiongo.webadmin.model.pojo.response.GnbVendorGroupDetailResponse;
import net.fashiongo.webadmin.model.pojo.response.GnbVendorGroupInfoResponse;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DisplayServiceImpl implements DisplayService {


	
}
