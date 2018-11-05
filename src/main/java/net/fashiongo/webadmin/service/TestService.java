package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.fashiongo.webadmin.dao.fgem.EmApplicationRepository;
import net.fashiongo.webadmin.dao.primary.TrendReportRepository;
import net.fashiongo.webadmin.model.pojo.Message;
import net.fashiongo.webadmin.model.pojo.Total;
import net.fashiongo.webadmin.model.pojo.WebAdminLoginUser;
import net.fashiongo.webadmin.model.pojo.parameter.GetMessageParameter;
import net.fashiongo.webadmin.model.pojo.response.GetMessageResponse;
import net.fashiongo.webadmin.model.primary.TrendReport;
import net.fashiongo.webadmin.utility.HttpClient;
import net.fashiongo.webadmin.utility.JsonResponse;
import net.fashiongo.webadmin.utility.Utility;

/**
 * 
 * @author Incheol Jung
 */
@Service
public class TestService extends ApiService{
	
	@Autowired
	@Qualifier("webAdminJsonClient")
	private HttpClient httpClient;
	
	@Autowired
	EmApplicationRepository emApplicationRepository;
	
	@Autowired
	TrendReportRepository trendReportRepository;
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 9. 19.
	 * @author Incheol Jung
	 * @return
	 */
	public String simpleTest() {
		return "testService -> simpleTest";
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 9. 19.
	 * @author Incheol Jung
	 * @return
	 * @throws Exception
	 */
	public String executeException() throws Exception {
		try {
			List<String> arr = new ArrayList<String>();
			arr.add("test");
			arr.get(10);
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
			throw new Exception();
		}
		return "testService -> executeException";
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 9. 19.
	 * @author Incheol Jung
	 * @param message
	 * @return
	 */
	public String callhttpNetwork(String message) {
		JsonResponse result = httpClient.get("Account/Test");
		System.out.println("result.getMessage()" + result.getMessage());
		
		return "testService -> callhttpNetwork";
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 9. 19.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 */
	public String callProc(GetMessageParameter parameters) {
		GetMessageResponse result = new GetMessageResponse();
		String spName = "up_wa_GetAdminMessage";
        List<Object> params = new ArrayList<Object>();
        
        params.add(parameters.getPagenum());
        params.add(parameters.getPagesize());
        params.add(parameters.getParent());
        params.add(parameters.getSendertypeid());
        params.add(parameters.getRecipienttypeid());
        params.add(parameters.getSender());
        params.add(parameters.getTopic());
        params.add(parameters.getSubject());
        params.add(parameters.getPeriod());
        params.add(parameters.getFromdate());
        params.add(parameters.getTodate());
        params.add(parameters.getStatus());
        
        List<Object> _result = jdbcHelper.executeSP(spName, params, Total.class, Message.class);
        
        result.setTotal((List<Total>)_result.get(0));
		result.setMessagelist((List<Message>) _result.get(1));
		
		return "testService -> callProc";
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 9. 19.
	 * @author Incheol Jung
	 * @return
	 */
	public String getSession() {
		WebAdminLoginUser user =  Utility.getUserInfo();
		System.out.println("user.getFullname() == " + user.getFullname());
		return "testService -> getSession";
	}
	
	@Transactional("primaryTransactionManager")
	public TrendReport TransactionTest(List<Integer> ids) {
//		TrendReport tr = this.trendReportRepository.findOneByTrendReportID(24);
//		tr.setTitle("incheol");
//		this.trendReportRepository.save(tr);
//		tr = this.trendReportRepository.findOneByTrendReportID(24);
		
		List<TrendReport> list = this.trendReportRepository.findByTrendReportIDIn(ids);
		for(TrendReport tr:list) {
			tr.setTitle("incheol!!!!!");
		}
		TrendReport tr = this.trendReportRepository.findOneByTrendReportID(ids.get(0));
		
		this.trendReportRepository.deleteByTrendReportIDIn(ids);
//		this.trendReportRepository.saveAll(list);
		
		
		return tr;
	}
}
