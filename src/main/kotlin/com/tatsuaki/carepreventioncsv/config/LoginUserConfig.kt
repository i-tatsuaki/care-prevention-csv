package com.tatsuaki.carepreventioncsv.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix="security.login")
open class LoginUserConfig {
        lateinit var id: String
        lateinit var password: String
}

