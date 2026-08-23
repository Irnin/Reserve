package site.komuna.reserve

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import site.komuna.reserve.security.token.TokenProperties

@EnableConfigurationProperties(TokenProperties::class)@SpringBootApplication
@ConfigurationPropertiesScan
@EnableScheduling
@EnableAsync
class ReserveApplication

fun main(args: Array<String>) {
	runApplication<ReserveApplication>(*args)
}
