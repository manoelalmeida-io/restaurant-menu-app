package com.example.backend.exception

import java.lang.RuntimeException

class ResourceNotFoundException(message: String): RuntimeException(message)