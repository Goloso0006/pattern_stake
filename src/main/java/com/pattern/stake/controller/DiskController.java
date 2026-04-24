package com.pattern.stake.controller;

import com.pattern.stake.service.DiskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/disk")
public class DiskController {

	private final DiskService diskService;

	public DiskController(DiskService diskService) {
		this.diskService = diskService;
	}

	@GetMapping("/state")
	public DiskResponse getCurrentState() {
		return diskService.getCurrentStateResponse();
	}

	@GetMapping("/read")
	public DiskResponse read() {
		return diskService.read();
	}

	@PostMapping("/write")
	public DiskResponse write(@RequestBody WriteRequest request) {
		return diskService.write(request.getData());
	}

	@PostMapping("/reset")
	public DiskResponse reset() {
		return diskService.reset();
	}

	@PostMapping("/clear")
	public DiskResponse clear() {
		return diskService.clear();
	}

	@GetMapping("/history")
	public DiskResponse history() {
		return diskService.getHistory();
	}
}

