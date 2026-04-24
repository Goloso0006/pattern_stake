package com.pattern.stake.controller;

import java.util.List;

public record DiskResponse(String state, String message, String content, List<String> history) {
}
