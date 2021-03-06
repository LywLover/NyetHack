package com.bignerdranch.nyethack

import java.lang.Exception
import java.lang.IllegalStateException
import kotlin.system.exitProcess

fun main(args: Array<String>) {


    Game.play()

}


object Game{
    private val player = Player("Madrigal")
    private var currentRoom:Room = TownSquare()
    private var run =true


    private var worldMap = listOf(listOf(currentRoom,Room("Tavern"),Room("Back Room")),
                                listOf(Room("Long Corridor"),Room("Generic Room")))

    init{
        println("Welcome, adventurer.")
        player.castFireball()
    }

    fun play(){
        while(true){
            println(currentRoom.description())
            println(currentRoom.load())

            //Player status
            printPlayerStatus(player)

            print("> Enter your command: ")
            println(GameInput(readLine()).processCommand())
            if(!run){
                break
            }
        }
        currentRoom.configurePitGoblin {goblin ->
            goblin.healthPoints = dangerLevel *3
            goblin
        }
    }

    private fun printPlayerStatus(player: Player) {
        println("(Aura: ${player.auraColor()})" + "(Blessed: ${if (player.isBlessed) "YES" else "NO"})")
        println("${player.name} ${player.formateHealthStatus()}")
    }

    private class GameInput(arg:String?){
        private val input:String = arg?:""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1,{""})

        fun processCommand() = when (command.toLowerCase()){
            "fight" -> fight()
            "quit" -> run=false
            "move" -> move(argument)
            else -> commandNotFound()
        }

        private fun commandNotFound() = "I'm not quite sure what you're tring to do!"

    }

    private fun move(directionInput:String) =
        try{
            val direction = Direction.valueOf(directionInput.toUpperCase())
            val newPositon = direction.updateCoordinate(player.currentPosition)
            if(!newPositon.isInBounds){
                throw IllegalStateException("$direction is out of bound")
            }

            val newRoom = worldMap[newPositon.y][newPositon.x]
            player.currentPosition=newPositon
            currentRoom = newRoom
            "OK, you move $direction to the ${newRoom.name}.\n${newRoom.load()}"

        }catch (e:Exception){
            "Invalid direction: $directionInput"
        }

    private fun fight() = currentRoom.monster?.let{
        while (player.healthPoints > 0 && it.healthPoints > 0){
            slay(it)
            Thread.sleep(1000)
        }

        "Combat complete."
    } ?: "There's nothing here to fight"

    private fun slay(monster : Monster){
        println("${monster.name} did ${monster.attack(player)} damage!")
        println("${player.name} did ${player.attack(monster)} damage!}")

        if(player.healthPoints <= 0){
            println(">>>> You have been defeated! Thanks for playing. <<<<")
            exitProcess(0)
        }

        if(monster.healthPoints <= 0){
            println(">>>> ${monster.name} has beehn defeated! <<<<")
            currentRoom.monster = null
        }
    }

}



