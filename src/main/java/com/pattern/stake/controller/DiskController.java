package com.pattern.stake.controller;

import com.pattern.stake.service.DiskService;
import org.springframework.web.bind.annotation.PostMapping;
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
	public String getCurrentState() {
		return diskService.getCurrentStateName();
	}

	@PostMapping("/read")
	public String read() {
		return diskService.read();
	}

	@PostMapping("/write")
	public String write() {
		return diskService.write();
	}

	@PostMapping("/reset")
	public String reset() {
		return diskService.reset();
	}
}

