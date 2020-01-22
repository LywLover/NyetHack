package com.bignerdranch.nyethack

fun main(args: Array<String>) {



    val player = Player("madrigal")
    player.castFireball()

    var currentRoom:Room = TownSquare()
    println(currentRoom.description())
    println(currentRoom.load())

    //Aura
    player.auraColor()


    //com.bignerdranch.nyethack.Player status
    printPlayerStatus(player)

}



private fun printPlayerStatus(player: Player) {
    println("(Aura: ${player.auraColor()})" + "(Blessed: ${if (player.isBlessed) "YES" else "NO"})")
    println("${player.name} ${player.formateHealthStatus()}")
}



