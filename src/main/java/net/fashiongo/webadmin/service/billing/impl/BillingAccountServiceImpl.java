package net.fashiongo.webadmin.service.billing.impl;

import net.fashiongo.common.dal.JdbcHelper;
import net.fashiongo.webadmin.service.billing.BillingAccountService;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillingAccountServiceImpl implements BillingAccountService {

    private JdbcHelper jdbcHelperFgBilling;

    public BillingAccountServiceImpl(JdbcHelper jdbcHelperFgBilling) {
        this.jdbcHelperFgBilling =  jdbcHelperFgBilling;
    }

    public void updateAccount(Integer wholeSalerID) {

        final String spname = "up_Setting_Account";
        List<Object> params = new ArrayList<>();
        params.add(wholeSalerID);
        params.add(Utility.getUsername());
        jdbcHelperFgBilling.executeSP(spname, params);
    }

}
