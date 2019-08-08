package net.fashiongo.webadmin.utility;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StringUtils;

import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.assertTrue;

@Slf4j
public class UtilityTest {

    @Test
    public void getFirstIpAddress() {
        String ipAddresses;
        String firstIp;

        ipAddresses = "10.77.253.151";
        firstIp = Utility.getFirstIpAddress(ipAddresses);
        log.debug("firstIp: {}", firstIp);
        assertTrue("10.77.253.151".equalsIgnoreCase(firstIp));

        ipAddresses = "10.77.253.151, 10.200.0.250, 10.200.0.41";
        firstIp = Utility.getFirstIpAddress(ipAddresses);
        log.debug("firstIp: {}", firstIp);
        assertTrue("10.77.253.151".equalsIgnoreCase(firstIp));

        ipAddresses = "   ";
        firstIp = Utility.getFirstIpAddress(ipAddresses);
        log.debug("firstIp: {}", firstIp);
        assertTrue(firstIp == null);
    }

}
