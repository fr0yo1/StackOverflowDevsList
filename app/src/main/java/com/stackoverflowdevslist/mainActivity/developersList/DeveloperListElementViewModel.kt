package com.stackoverflowdevslist.mainActivity.developersList

import com.stackoverflowdevslist.developersRepository.DeveloperModel

class DeveloperListElementViewModel {
    var profileImage: String
    var displayName: String

    constructor(developerModel: DeveloperModel) {
        profileImage = developerModel.profile_image
        displayName = developerModel.display_name
    }
}