package com.gloal.todo.error;

import java.time.LocalDateTime;

public record ApiErrorResponse(String path, String message, int statusCode, LocalDateTime localDateTime){}