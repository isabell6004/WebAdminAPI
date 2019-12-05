<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [REST-API](#rest-api)
  - [Commons](#commons)
    - [Ad-API](#ad-api)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

# REST-API
- WebAdminAPI 와 AdAPI

## Commons
### Ad-API  
- https://github.com/FashionGo/fashiongo-ad-api/blob/develop/doc/api/ad-api.md
- WebAdminAPI 와 AD-API 는 REST-API 형태로 데이터를 통합힌다.
    - Case1. AD-API의 API를 reverse proxy 하는 경우
    - Case2. AD-API의 API를 call 한 뒤, 추가 데이터를 merge 해야하는 경우
- WebAdminAPI 광고 영역 API는 다음과 같은 Prefix 를 갖는다.
    - /api/adapi/
    - example 1. AD-API 의 POST /v2/pages reverse proxy 하는 경우
        - POST /api/adapi/pages -> /v2/pages
        - 해당 API는  AD-API 의 ad-api.md 문서로 갈음한다.
    - example 2. AD-API 의 GET /v2/receipts/{receipt-id} 를 merge 하는 경우
        - GET /api/adapi/receipts/{receipt-id} -> /v2/receipts/{receipt-id}
        - 해당 API 의 응답은 직접 기술 한다.
        - 보통은 HTTP Body > data > references 에 merge 된 객체를 참고한다. 

#### Common Headers
- AD-API의 REST-API를 call 할때 반드시 다음의 HEADER 를 포함한다.
```javascript
FG-Vendor-Id : 12312                                // 선택한 Vendor의 VendorId 값
FG-Channel : admin-api                             // 상수값
FG-User-Id : 321                                    // User의 ID 값
FG-User-Name : developer                            // WebAdmin 에 로그인한 user name 값 
X-Request-ID : 1X12312-123123-1231231-2312313       
```
