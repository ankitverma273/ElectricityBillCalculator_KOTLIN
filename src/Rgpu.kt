
val RGPU = object : Rates {
    override val ENERGY_0_TO_50: Double = 3.05
    override val ENERGY_51_TO_100: Double = 3.5
    override val ENERGY_101_TO_250: Double = 4.15
    override val ENERGY_ABOVE_250: Double = 5.2
}