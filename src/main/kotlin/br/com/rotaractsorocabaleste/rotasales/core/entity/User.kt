package br.com.rotaractsorocabaleste.rotasales.core.entity

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "\"user\"")
data class User(
    @Id
    val id: UUID = UUID.randomUUID(),
    @Column(unique = true, nullable = false)
    val username: String? = null,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    val password: String? = null,
    @Column(nullable = false)
    val fullName: String? = null,
    @OneToMany
    val roles: List<UserRole> = listOf()
)

class UserDetailsImpl(
    private val user: User
) : UserDetails {
    override fun getAuthorities(): List<SimpleGrantedAuthority> {
        return user.roles.map { SimpleGrantedAuthority(it.role?.description) }
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