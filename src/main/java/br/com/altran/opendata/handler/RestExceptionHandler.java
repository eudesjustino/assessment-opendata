package br.com.altran.opendata.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.altran.opendata.consumer.exception.AvailableLinkException;
import br.com.altran.opendata.error.ErrorDetails;
import br.com.altran.opendata.exception.LanguageInvalidException;
import br.com.altran.opendata.service.exception.ErrorJsonProcessingException;

/**
 * @author eudes.justino
 *
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ErrorJsonProcessingException.class)
	public ResponseEntity<?> hadleErrorJsonProcessingException(ErrorJsonProcessingException exception){
		ErrorDetails build = ErrorDetails.Builder
			.newBuilder()
			.timestamp(new Date().getTime())
			.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
			.title("")
			.detail(exception.getMessage())
			.developerMessage(exception.getClass().getName())
			.build();
		return new ResponseEntity<>(build,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(LanguageInvalidException.class)
	public ResponseEntity<?> hadleLanguageInvalidException(LanguageInvalidException exception){
		ErrorDetails build = ErrorDetails.Builder
			.newBuilder()
			.timestamp(new Date().getTime())
			.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
			.title("Error Language not acceptable.")
			.detail(exception.getMessage())
			.developerMessage(exception.getClass().getName())
			.build();
		return new ResponseEntity<>(build,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(AvailableLinkException.class)
	public ResponseEntity<?> hadleAvailableLinkExceptionn(AvailableLinkException exception){
		ErrorDetails build = ErrorDetails.Builder
			.newBuilder()
			.timestamp(new Date().getTime())
			.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
			.title("Error with Link unavailable")
			.detail(exception.getMessage())
			.developerMessage(exception.getClass().getName())
			.build();
		return new ResponseEntity<>(build,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
			org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails errorDetails = ErrorDetails.Builder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(status.value())
                .title("Internal Exception")
                .detail(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .build();
        return new ResponseEntity<>(errorDetails, headers, status);
	}
	
}
