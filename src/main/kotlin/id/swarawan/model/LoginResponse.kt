package id.swarawan.model

data class LoginResponse(
	val accessToken: String? = null,
	var name: String? = null,
	var email: String? = null,
	var address: String? = null,
	var image: String? = null
)