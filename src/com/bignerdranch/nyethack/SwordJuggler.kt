package com.bignerdranch.nyethack

fun main(args: Array<String>) {
    var swordJuggling :Int? = null
    val isJugglingProficient = (1..3).shuffled().last() ==3
    if(isJugglingProficient){
        swordJuggling = 2
    }
    try {
        proficiencyCheck(swordJuggling)
        swordJuggling = swordJuggling!!.plus(1)
    }catch(e:Exception){
        println(e)
    }
    println("You juggle $swordJuggling swords!")

}

fun proficiencyCheck(swordsJuggling:Int?){
    checkNotNull(swordsJuggling,{"com.bignerdranch.nyethack.Player cannot juggle swords"})
}

class UnskilledSwordJugglerException():
    IllegalArgumentException("com.bignerdranch.nyethack.Player cannot juggle sword")