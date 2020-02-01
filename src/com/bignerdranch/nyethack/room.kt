package com.bignerdranch.nyethack

open class Room(val name:String){
    open val dangerLevel = 5
    var monster : Monster? = Goblin()

    fun description() = "Room: $name\n" +
            "Danger level: $dangerLevel" +
            "Creature: ${monster?.description ?: "none."}"

    open fun load() = "Noting much to see there..."
}

open class TownSquare : Room("Town Square"){
    override val dangerLevel = super.dangerLevel-3
    private var bellSound = "GWONG"

    final override fun load()=
        "The villagers rally and cheer as you enter!"

    private fun ringBell()="The bell tower announces your arrival. $bellSound"
}
