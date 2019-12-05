package net.fashiongo.webadmin.model.ads;

public enum ErrorCode {


    /**
     * 10 XX XX XX
     * |  |  |  +-- detail error Code
     * |  |  +----- category error code
     * |  +-------- api error code
     * +----------- system error code
     * <p>
     * <p>
     * * system error code
     * 10   AD [advertising system]
     * <p>
     * * API code
     * 00   COMMONS
     * 01   PAGES
     * 02   SPOTS
     * 03   SLOTS
     * 05   PLANS
     * 06   PRICES
     * 07   SLOT_ITEMS
     * 08   SUBMIT_LOGS
     * <p>
     * * category error code
     * 00   COMMON      [common error] - unexpected system internal error
     * 10   SYSTEM      [internal error] - System internal error due to bug or system failure
     * 20   USER        [user invalid error] - parameter validation error caused by client
     * 30   COMPONENT   [server component error] - system component error while processing
     * <p>
     */


    COMMON_SUCCESS(0, true),
    COMMON_ERROR(-10_00_00_00, false);

    private boolean isSuccess;
    private int code;

    ErrorCode(int errorCode, boolean isSuccess) {
        this.code = errorCode;
        this.isSuccess = isSuccess;
    }

    public int getCode() {
        return this.code;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }
}
