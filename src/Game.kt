fun main(args: Array<String>) {

    val name = "Madrigal"
    var healthPoints = 89
    val isBlessed = true
    val isImmortal = false

    //Aura
    val auraColor = auraColor(isBlessed, healthPoints, isImmortal)

    val healthStatus = formateHealthStatus(healthPoints, isBlessed)
    //Player status
    printPlayerStatus(auraColor, isBlessed, name, healthStatus)
    castFirlball()
}

private fun formateHealthStatus(healthPoints: Int, isBlessed: Boolean)= when (healthPoints) {
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

private fun printPlayerStatus(
    auraColor: String,
    isBlessed: Boolean,
    name: String,
    healthStatus: String
) {
    println("(Aura: $auraColor)" + "(Blessed: ${if (isBlessed) "YES" else "NO"})")
    println("$name $healthStatus")
}

private fun auraColor(isBlessed: Boolean, healthPoints: Int, isImmortal: Boolean): String {
    val auraVisible = isBlessed && healthPoints > 50 || isImmortal
    val auraColor = if (auraVisible) "Green" else "NONE"
    return auraColor
}

private fun castFirlball(numFireballs:Int = 2) =
    println("a glass of Fireball spring into existence. (x$numFireballs)")
