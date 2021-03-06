package com.tatsuaki.carepreventioncsv.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException


@Configuration
@EnableWebSecurity
open class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var loginUserConfig: LoginUserConfig

    override fun configure(web: WebSecurity) {
    }

    override fun configure(http: HttpSecurity) {
        http.antMatcher("/**") // Basic認証の対象となるパス
        http.httpBasic() // Basic認証を指定
        http.authorizeRequests().anyRequest().authenticated() // 対象のすべてのパスに対して認証を有効にする
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // すべてのリクエストをステートレスとして設定
        http.logout().deleteCookies("JSESSIONID")
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(BasicAuthenticationProvider())
    }

    // 認証処理の実装クラス
    inner class BasicAuthenticationProvider : AuthenticationProvider {

        // 認証チェック
        @Throws(AuthenticationException::class)
        override fun authenticate(authentication: Authentication): Authentication {

            val name = authentication.name
            val password = authentication.credentials.toString()

            if (name == loginUserConfig.id && password == loginUserConfig.password) {
                return UsernamePasswordAuthenticationToken(name, password, authentication.getAuthorities())
            }

            throw AuthenticationCredentialsNotFoundException("basic auth error")
        }

        // 処理すべきAuthenticationクラスのチェック
        override fun supports(authentication: Class<*>): Boolean {
            return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
        }
    }
}