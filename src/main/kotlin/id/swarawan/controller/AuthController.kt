package id.swarawan.controller

import id.swarawan.model.LoginRequest
import id.swarawan.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["auth"])
class AuthController @Autowired constructor(
	private val authService: AuthService
) {

	@GetMapping(value = ["login"])
	fun login(@RequestBody request: LoginRequest): ResponseEntity<Any> {
		val auth = authService.login(request)
		return ResponseEntity.ok(auth)
	}
}