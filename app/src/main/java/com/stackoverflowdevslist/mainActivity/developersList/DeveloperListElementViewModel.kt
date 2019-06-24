package com.stackoverflowdevslist.mainActivity.developersList

import com.stackoverflowdevslist.developersRepository.DeveloperModel

class DeveloperListElementViewModel {
    var profileImage: String
    var displayName: String
    var userId: String

    constructor(developerModel: DeveloperModel) {
        userId = developerModel.user_id
        profileImage = developerModel.profile_image
        displayName = developerModel.display_name
    }
}