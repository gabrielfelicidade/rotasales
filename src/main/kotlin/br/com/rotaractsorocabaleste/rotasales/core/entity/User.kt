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
    @Column(name = "user_id")
    val id: UUID = UUID.randomUUID(),
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    val username: String? = null,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    val password: String? = null,
    val fullName: String? = null,
    @ManyToMany
    @JoinTable(
        name = "user_role",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    val roles: List<Role> = listOf()
)

class UserDetailsImpl(
    private val user: User
) : UserDetails {

    init {
        user.roles.size
    }

    override fun getAuthorities(): List<SimpleGrantedAuthority> {
        return user.roles.map { SimpleGrantedAuthority(it.description) }
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