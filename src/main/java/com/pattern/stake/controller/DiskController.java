package com.pattern.stake.controller;

import com.pattern.stake.service.DiskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/disk")
public class DiskController {

	private final DiskService diskService;

	public DiskController(DiskService diskService) {
		this.diskService = diskService;
	}

	@GetMapping("/state")
	public ResponseEntity<String> getCurrentState() {
		return ResponseEntity.ok(diskService.getCurrentStateName());
	}
}

