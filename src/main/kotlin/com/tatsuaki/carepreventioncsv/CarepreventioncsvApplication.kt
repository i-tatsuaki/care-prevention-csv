package com.tatsuaki.carepreventioncsv

import com.tatsuaki.carepreventioncsv.config.LoginUserConfig
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class CarepreventioncsvApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(CarepreventioncsvApplication::class.java, *args)
        }
    }
}
