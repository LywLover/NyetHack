package com.bignerdranch.nyethack

import java.io.File
import com.bignerdranch.nyethack.extensions.random as randomizer
class Player (
    _name: String,
    override var healthPoints: Int = 100,
    var isBlessed: Boolean = true,
    private val isImmortal: Boolean = false
) : Fightable{
    override val diceCount: Int = 3
    override val diceSides: Int = 6
    override fun attack(opponent: Fightable): Int {
      val damageDealt = if(isBlessed){
          damageRoll * 2
      }else{
          damageRoll
      }
        opponent.healthPoints -= damageDealt
        return damageDealt
    }

    init {
        require(healthPoints > 0) { "healthPoints must be greater than zero" }
        require(_name.isNotBlank()) { "Player must have a names" }
        if(_name.toLowerCase() == "kar")
            healthPoints = 40
    }

    var name = _name
        get() = "${field.capitalize()} of $hometown"
        private set(value) {
            field = value.trim()
        }
    constructor(name: String):this(name,
        healthPoints=100,
        isBlessed = true,
        isImmortal = false){
        if(name.toLowerCase()=="kar" ) healthPoints = 40
    }

    val hometown by lazy{selectHomwtown()}
    var currentPosition = Coordinate(0,0)

    fun auraColor(): String {
        val auraVisible = isBlessed && healthPoints > 50 || isImmortal
        val auraColor = if (auraVisible) "Green" else "NONE"
        return auraColor
    }

    fun formateHealthStatus()= when (healthPoints) {
        100 -> "is in excellent condition!"
        in 90..99 -> "has a few scratches"
        in 75..89 -> if (isBlessed) {
            "has some minor wounds but is healing quite quickly!"
        } else {
            "has some minor wounds"
        }
        in 15..74 -> "looks pretty hurt"
        else -> "is in awful condition!"
    }

    fun castFireball(numFileballs:Int =2)=
        println("A glass of Fireball springs into existence. (x$numFileballs)")

    private fun selectHomwtown()=File("data/towns.txt")
        .readText()
        .split("\n")
        .randomizer()
}