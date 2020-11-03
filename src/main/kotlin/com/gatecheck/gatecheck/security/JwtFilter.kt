package com.gatecheck.gatecheck.security

import com.gatecheck.gatecheck.utils.Routes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtFilter @Autowired constructor(
        private val jwtHelper: JwtHelper,
        private val userDetailsService: GateCheckUserDetailsService
) : OncePerRequestFilter() {

    override fun doFilterInternal(req: HttpServletRequest, res: HttpServletResponse, filter: FilterChain) {
        val authHeader = req.getHeader("Authorization")
        var username: String? = null
        var token: String? = null


        if (req.requestURI.equals(Routes.BASE + Routes.Auth.BASE + Routes.Auth.LOGIN, ignoreCase = true)
                || req.requestURI.equals(Routes.BASE + Routes.Auth.BASE + Routes.Auth.REGISTER, ignoreCase = true)) return filter.doFilter(req, res)
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7)
            try {
                username = jwtHelper.extractUsername(token)
            } catch (e: Exception) {
                try {
                    res.sendError(HttpStatus.UNAUTHORIZED.value(), e.message)
                    return filter.doFilter(req, res)
                } catch (e: Exception) {
                    return
                }
            }
        }

        if (username != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails = userDetailsService.loadUserByUsername(username) ?: return filter.doFilter(req, res)

            if (jwtHelper.validateToken(token!!, userDetails)) {
                val authToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                authToken.details = WebAuthenticationDetailsSource().buildDetails(req)
                SecurityContextHolder.getContext().authentication = authToken
            }
        }
        filter.doFilter(req, res)
    }
}