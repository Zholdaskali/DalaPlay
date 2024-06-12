package com.example.dalaPlay.encoder

import org.mindrot.jbcrypt.BCrypt

class BCryptPasswordEncoder(private val saltRounds: Int) : PasswordEncoder {

    override fun hash(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt(saltRounds))
    }

    override fun check(password: String, hash: String): Boolean {
        return BCrypt.checkpw(password, hash)
    }
}
