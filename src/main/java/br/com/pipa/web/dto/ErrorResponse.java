package br.com.pipa.web.dto;

import lombok.Builder;
import lombok.ToString;

/**
 * @author romeh
 */
@Builder
@ToString
public class ErrorResponse {

	private String message;
}
