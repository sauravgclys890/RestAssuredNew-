package com.restAssured.testing;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

public class TestSplunk {

	public static void main(String[] args) {
		
       RestAssured.baseURI="https://splunk-ss-b.t-mobile.com:8089/services/search/jobs/export?";
		
		
		ValidatableResponse response= RestAssured.given().relaxedHTTPSValidation().auth().basic("Authorization", "Basic U1ZDX1RTVF9TcGx1bmtBdXRvOjlEYV89MllmZCU4WitXeDROMyp6c1M2JC01Yko3alQvP0MycG59TTUhTDl0N3dCe3JGXzZLNGMrMyFQZ1hl").param("search", "search index=genesis sourcetype=genesis_body_header_txt AuditEvent_Event=GENESIS_TRANSACTION 0a07881a-test-4a06-8333-1c3ae7315894 | sort AuditKeys_STARTTIME AuditKeys_ENDTIME AuditKeys_TRANSACTIONID AuditEvent_ApplicationId AuditEvent_Event AuditKeys_PROCESSNAME AuditKeys_STATUS AuditKeys_MSISDN AuditKeys_PARTNERID AuditKeys_ERRORCODE AuditKeys_ERRORMESSAGE AuditKeys_TIMEDURATION event_id").param("output_mode", "raw").param("decod", "urldecode(_raw)").when().get().then();
        
        System.out.println(response.toString());
        
        System.out.println(response.statusCode(200));

	}

}
