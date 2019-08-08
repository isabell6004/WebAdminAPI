package net.fashiongo.webadmin.controller;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id="l7check")
//{contextRoot}/{management.endpoints.web.base-path}/l7check
public class MonitorController {

	private boolean isUp = true;

	@ReadOperation
	public ResponseEntity<String> check() {
		if(isUp == false) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}

		return new ResponseEntity<>("", HttpStatus.OK);
	}

	@WriteOperation // POST
	public void healthUp() {
		isUp = true;
	}

	@DeleteOperation // DELETE
	public void healthDown() {
		isUp = false;
	}
}
