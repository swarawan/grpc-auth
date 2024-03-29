package id.swarawan.repository

import id.swarawan.entity.Auth
import org.springframework.stereotype.Repository

@Repository
class AuthRepository {

	private val auths: List<Auth> by lazy {
		initData()
	}

	fun findCredentialByUsername(username: String?): Auth? =
		auths.find { it.username == username }

	private fun initData(): List<Auth> {
		return arrayListOf(
			Auth(1, "ii@investree.id", "123456"),
			Auth(2, "eka@investree.id", "123456"),
			Auth(3, "feri@investree.id", "123456")
		)
	}
}