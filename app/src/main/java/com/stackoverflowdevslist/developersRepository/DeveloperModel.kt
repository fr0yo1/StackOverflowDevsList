package com.stackoverflowdevslist.developersRepository

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class DeveloperModel {
    @PrimaryKey
    lateinit var user_id: String
    lateinit var account_id: String
    lateinit var profile_image: String
    lateinit var display_name: String
    lateinit var link: String
    lateinit var reputation: Integer
}