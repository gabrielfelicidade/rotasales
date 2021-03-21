package br.com.rotaractsorocabaleste.rotasales.core.exception

import br.com.rotaractsorocabaleste.rotasales.core.vo.ErrorDetailsVO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class RestExceptionHandler(
    private val logger: Logger = LoggerFactory.getLogger(RestExceptionHandler::class.java)
) {

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(e: EntityNotFoundException, request: WebRequest): ResponseEntity<Any> {
        logger.error(e.message)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorDetailsVO(e))
    }

    @ExceptionHandler(UserUnauthorizedException::class)
    fun handleUserUnauthorizedException(e: UserUnauthorizedException, request: WebRequest): ResponseEntity<Any> {
        logger.error(e.message)
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorDetailsVO(e))
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(e: Exception, request: WebRequest): ResponseEntity<Any> {
        logger.error(e.message)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
    }

}