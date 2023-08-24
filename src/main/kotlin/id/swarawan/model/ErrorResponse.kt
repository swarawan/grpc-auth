package id.swarawan.model

data class ErrorResponse(
	val code: Int,
	val timestamp: Long,
	val message: String? = null
)