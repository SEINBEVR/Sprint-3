package main.kotlin.ru.sber.oop

open class Room(val name: String, val size: Int) {

    constructor(name: String) : this(name, 100)

    protected open val dangerLevel = 5

    val goblin: Monster = Goblin("Goblin",
        "Little green monster",
        "Damage decreased twice",
        100)

    fun description() = "Room: $name"

    fun Monster.getSalutation(name: String): String {
        return("${this.name} salutes you")
    }


    open fun load() =
        goblin.getSalutation(this.name) + "\n" +
                "Nothing much to see here..."
}

class TownSquare: Room("Town Square", 1000) {

    final override fun load() = "It's kinda big {$name}..."

    override val dangerLevel = super.dangerLevel - 3
}

