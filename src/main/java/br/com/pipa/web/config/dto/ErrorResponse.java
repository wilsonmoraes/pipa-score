package br.com.pipa.web.config.dto;

import lombok.Builder;
import lombok.ToString;


/**
 * @author Wilson M.
 */
@Builder
@ToString
public class ErrorResponse {

    private String message;
}
