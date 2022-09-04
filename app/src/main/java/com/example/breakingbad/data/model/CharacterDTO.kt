package com.example.breakingbad.data.model

import com.example.breakingbad.domain.model.Character
import com.google.gson.annotations.SerializedName

class CharacterListDTO : ArrayList<CharacterDTO>()

data class CharacterDTO(
    @SerializedName("char_id")
    val id: Int,
    val name: String,
    val birthday: String,
    val occupation: List<String>,
    val img: String,
    val nickname: String,
    val portrayed: String

)

fun CharacterDTO.toCharacter(): Character {
    return Character(
        id = this.id,
        name = this.name,
        birthday = this.birthday,
        occupation = this.occupation,
        img = this.img,
        portrayed = this.portrayed,
        nickname = this.nickname
    )
}

