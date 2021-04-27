
val BPL = object : Rates {
    override val ENERGY_0_TO_50: Double = 0.0
    val ENERGY_0_TO_30: Double = 1.5
    val ENERGY_31_TO_50: Double = 2.65
    override val ENERGY_51_TO_100: Double = 3.10
    override val ENERGY_101_TO_250: Double = 3.75
    override val ENERGY_ABOVE_250: Double = 4.9

}