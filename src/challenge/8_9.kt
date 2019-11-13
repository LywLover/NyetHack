package challenge
import kotlin.math.roundToInt
const val TAVERN_NAME="Taernyl's Folly"

var playerGold = 0
var playerSilver =0
var dragonGold = 5

fun main(args: Array<String>) {
    placeorder("shandy,Dragon's Breath,5.91")
}
fun performPurchase(price:Double){
    displayBalance()
    val totalPurse = playerGold + (playerSilver / 100.0) + (dragonGold * 1.43)
    println("Total purse : $totalPurse")
    println("Purchasing item for $price")
    val remainingBalance = totalPurse - price
    if(remainingBalance<0) {
        println("You do not have enough money!!")
        return
    }
    println("Remaining balance: ${"%.4f".format(remainingBalance/1.43)} dragonGold")

    val remainingGold = remainingBalance.toInt()
    val remainingSilver = (remainingBalance%1*100).roundToInt()
    playerGold=remainingGold
    playerSilver=remainingSilver
    displayBalance()
}
private  fun displayBalance(){
    println("Player's purse balance: Gold: $playerGold , Silver: $playerSilver")
}
private fun toDragonSpeak(phrase:String)=
    phrase.replace(Regex("[aeiou]")){
        when(it.value){
            "a"->"4"
            "e"->"3"
            "i"->"1"
            "o"->"|_|"
            else->it.value
        }

    }

private fun placeorder(menuData:String){
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("Madrigal speaks with $tavernMaster about their order")

    val(type,name,price)= menuData.split(',')
    val message  = "Madrigal buys a $name ($type) for $price."
    println(message)

    performPurchase(price.toDouble())

    val phrase = if(name == "Dragon's Breath"){
        "Madrigal exclaims: ${toDragonSpeak("Ah,delicious $name")}"
    }else{
        "Madrigal says: Thanks for the $name"
    }
    println(phrase)
}


