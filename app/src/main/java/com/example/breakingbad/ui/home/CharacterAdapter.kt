package com.example.breakingbad.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.breakingbad.databinding.ItemCharacterBinding
import com.example.breakingbad.domain.model.Character

class CharacterAdapter(
    private val characters: List<Character>,

): RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    class CharacterViewHolder (val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val currentCharacter = characters[position]
        holder.binding.apply {
            tvCharacterName.text = currentCharacter.name
            tvCharacterNickname.text = currentCharacter.nickname
            tvCharacterOccupation.text = currentCharacter.occupation[0]
            tvCharacterRealName.text = currentCharacter.portrayed
            Glide.with(root.context)
                .load(currentCharacter.img)
                .into(ivCharacter)

        }
    }

    override fun getItemCount(): Int {
        return characters.size
    }

}