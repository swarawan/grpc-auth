package id.swarawan.configuration

import id.swarawan.ProfileGrpc
import io.grpc.ManagedChannelBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component

@Component
class AppStub @Autowired constructor(
	private val environment: Environment
) {

	@Bean
	fun profileStub(): ProfileGrpc.ProfileBlockingStub {
		val profileHost = environment.getProperty("profileHost", "localhost")
		val channel = ManagedChannelBuilder.forAddress(profileHost, 60001)
			.usePlaintext()
			.build()
		return ProfileGrpc.newBlockingStub(channel)
	}
}