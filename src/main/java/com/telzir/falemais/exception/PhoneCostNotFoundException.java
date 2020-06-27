package com.telzir.falemais.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Tarifa entre os DDDs não encontrada")
public class PhoneCostNotFoundException extends RuntimeException {
}
