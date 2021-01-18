package br.com.rotaractsorocabaleste.rotasales.core.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "\"user\"")
data class User(
        @Id
        val id: UUID = UUID.randomUUID(),
        val username: String? = null,
        val password: String? = null,
        val fullName: String? = null
)

class UserDetailsImpl(
        private val user: User
) : UserDetails {
        override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
                return mutableListOf()
        }

        override fun isEnabled(): Boolean {
                return true
        }
        override fun getUsername(): String {
                return user.username.toString()
        }

        override fun isCredentialsNonExpired(): Boolean {
                return true
        }

        override fun getPassword(): String {
                return user.password.toString()
        }

        override fun isAccountNonExpired(): Boolean {
                return true
        }

        override fun isAccountNonLocked(): Boolean {
                return true
        }
}

data class Credentials(
        val username: String? = null,
        val password: String? = null
)