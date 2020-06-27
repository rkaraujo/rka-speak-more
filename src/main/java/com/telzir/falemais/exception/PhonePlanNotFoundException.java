package com.telzir.falemais.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Plano não encontrado")
public class PhonePlanNotFoundException extends RuntimeException {
}
