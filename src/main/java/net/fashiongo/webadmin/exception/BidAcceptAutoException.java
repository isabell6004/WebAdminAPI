package net.fashiongo.webadmin.exception;

public class BidAcceptAutoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public BidAcceptAutoException(Throwable e, int bidSettingId) {
		super(e);
		this.bidSettingId = bidSettingId;
	}
	
	private Integer bidSettingId;

	public Integer getBidSettingId() {
		return bidSettingId;
	}

	public void setBidSettingId(Integer bidSettingId) {
		this.bidSettingId = bidSettingId;
	}
	
}
