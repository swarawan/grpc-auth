package id.swarawan.configuration.exception

import id.swarawan.model.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.Instant

@ControllerAdvice
class AppExceptionHandler : ResponseEntityExceptionHandler() {

	@ExceptionHandler(DataNotFoundException::class)
	fun dataNotFoundException(ex: DataNotFoundException): ResponseEntity<Any> {
		val errorResponse = ErrorResponse(
			code = HttpStatus.NOT_FOUND.value(),
			message = ex.localizedMessage,
			timestamp = Instant.now().epochSecond
		)
		return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
	}

	@ExceptionHandler(AppException::class)
	fun appException(ex: AppException): ResponseEntity<Any> {
		val errorResponse = ErrorResponse(
			code = HttpStatus.BAD_REQUEST.value(),
			message = ex.localizedMessage,
			timestamp = Instant.now().epochSecond
		)
		return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
	}
}