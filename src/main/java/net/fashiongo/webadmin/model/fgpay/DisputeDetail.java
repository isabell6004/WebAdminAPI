/**
 * 
 */
package net.fashiongo.webadmin.model.fgpay;

import java.util.List;

/**
 * @author Brian
 *
 */
public class DisputeDetail {
	private DisputeHeaderInfo header;

	public DisputeHeaderInfo getHeader() {
		return header;
	}

	public void setHeader(DisputeHeaderInfo header) {
		this.header = header;
	}
	
	private List<DisputeMergeOrderInfo> mergeOrders;

	public List<DisputeMergeOrderInfo> getMergeOrders() {
		return mergeOrders;
	}

	public void setMergeOrders(List<DisputeMergeOrderInfo> mergeOrders) {
		this.mergeOrders = mergeOrders;
	}
	
	private DisputeDetailInfo detailInfo;

	public DisputeDetailInfo getDetailInfo() {
		return detailInfo;
	}

	public void setDetailInfo(DisputeDetailInfo detailInfo) {
		this.detailInfo = detailInfo;
	}
	
	private List<DisputeDocumentInfo> documentInfo;

	public List<DisputeDocumentInfo> getDocumentInfo() {
		return documentInfo;
	}

	public void setDocumentInfo(List<DisputeDocumentInfo> documentInfo) {
		this.documentInfo = documentInfo;
	}
}
