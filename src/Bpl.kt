
val BPL = object : Rates {
    override val ENERGY_0_TO_30 = 1.5
    override val ENERGY_30_TO_50 = 2.65
    override val ENERGY_0_TO_50 = 0.00
    override val ENERGY_51_TO_100 = 3.10
    override val ENERGY_101_TO_250 = 3.75
    override val ENERGY_ABOVE_250 = 4.9
}