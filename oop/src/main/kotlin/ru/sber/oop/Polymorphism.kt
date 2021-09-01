package main.kotlin.ru.sber.oop

import kotlin.random.Random

interface Fightable {
    val powerType: String
    var healthPoints: Int
    val damageRoll: Int
        get() = Random.nextInt()

    fun attack(opponent: Fightable): Int
}

class Player(val name: String,
             val isBlessed: Boolean,
             override val powerType: String,
             override var healthPoints: Int): Fightable {

    override fun attack(opponent: Fightable): Int {
        if(isBlessed) {
            opponent.healthPoints = opponent.healthPoints - damageRoll * 2
            return damageRoll * 2
        } else {
            opponent.healthPoints = opponent.healthPoints - damageRoll
            return damageRoll
        }
    }
}

abstract class Monster(val name: String,
                       val description: String,
                       override val powerType: String,
                       override var healthPoints: Int): Fightable {

    override fun attack(opponent: Fightable): Int {
        opponent.healthPoints - damageRoll
        return damageRoll
    }
}

class Goblin(name: String, description: String, powerType: String, healthPoints: Int) :
    Monster(name, description, powerType, healthPoints) {

    override val damageRoll: Int
        get() = super.damageRoll / 2

}



