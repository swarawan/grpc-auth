package id.swarawan.service

import id.swarawan.ProfileGrpc.ProfileBlockingStub
import id.swarawan.UserDetailsRequest
import id.swarawan.UserDetailsResponse
import id.swarawan.configuration.exception.AppException
import id.swarawan.configuration.exception.DataNotFoundException
import id.swarawan.model.LoginRequest
import id.swarawan.model.LoginResponse
import id.swarawan.repository.AuthRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthService @Autowired constructor(
	private val authRepository: AuthRepository,
	private val profileStub: ProfileBlockingStub
) {

	fun login(request: LoginRequest): LoginResponse {
		validateRequest(request)

		val auth = authRepository.findCredentialByUsername(request.username)
			?: throw DataNotFoundException("Data not found")

		val loginResponse = LoginResponse(accessToken = UUID.randomUUID().toString())
		findUserDetails(auth.id)?.let {
			with(loginResponse) {
				name = it.name
				email = it.email
				address = it.address
				image = it.image
			}
		}
		return loginResponse
	}

	private fun validateRequest(loginRequest: LoginRequest) {
		when {
			loginRequest.username.isNullOrEmpty() ->
				throw AppException("Username cannot be empty")

			loginRequest.password.isNullOrEmpty() ->
				throw AppException("Password cannot be empty")
		}
	}

	private fun findUserDetails(userId: Int): UserDetailsResponse? {
		return try {
			val profileRequest = UserDetailsRequest.newBuilder()
				.setId(userId)
				.build()
			profileStub.getUserDetails(profileRequest)
		} catch (ex: Exception) {
			ex.printStackTrace()
			null
		}
	}
}