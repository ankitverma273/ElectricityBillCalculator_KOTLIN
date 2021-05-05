
class BillCalculator(private var unit: Int,
                     private var fuelCharge: Float,
                     private var fixedCharge: Int,
                     private var energyCharges: Double,
                     private var tax: Float,
                     private var area: Int,
                     private var isBpl: Boolean,
                     private var load: Float) {

    fun passCharges() {
        if(isBpl){
            calculateEnergyCharges(BPL,unit)
        }
        else if (area == 0) {
            calculateEnergyCharges(RGPU, unit)
        } else {
            calculateEnergyCharges(RGPR, unit)
        }
    }

    private fun calculateEnergyCharges(rates: Rates, unit: Int) {
        energyCharges = if (unit <= 30){
            (unit * rates.ENERGY_0_TO_30)
        }else if(unit in 31..50){
            (unit * rates.ENERGY_30_TO_50)
        } else if (unit <= 50) {
            (unit * rates.ENERGY_0_TO_50)
        } else if (unit in 51..100) {
            (50 * rates.ENERGY_0_TO_50 +
                    (unit - 50) * rates.ENERGY_51_TO_100)
        } else if (unit in 101..250) {
            (50 * rates.ENERGY_0_TO_50 +
                    50 * rates.ENERGY_51_TO_100 +
                    (unit - 100) * rates.ENERGY_101_TO_250)
        } else {
            (50 * rates.ENERGY_0_TO_50 +
                    50 * rates.ENERGY_51_TO_100 +
                    150 * rates.ENERGY_101_TO_250 +
                    (unit - 250) * rates.ENERGY_ABOVE_250)
        }
    }

    fun calculateFuelCharges() {
        fuelCharge *= unit
    }

    fun calculateFixedCharge() {
        if (isBpl){
            fixedCharge = 5
        } else if (area == 0) {
            if (load <= 2) {
                fixedCharge = 15
            } else if (load > 2 && load <= 4) {
                fixedCharge = 25
            } else if (load > 4 && load <= 6) {
                fixedCharge = 45
            }
        } else {
            if (load <= 2) {
                fixedCharge = 15
            } else if (load > 2 && load <= 4) {
                fixedCharge = 25
            } else if (load > 4 && load <= 6) {
                fixedCharge = 45
            }
        }
    }

    fun calculateTax() {
        val totalForTax = fixedCharge + energyCharges + fuelCharge
        tax = if (isBpl){
            (0 * 0).toFloat()
        } else if (area == 0) {
            (0.15 * totalForTax).toFloat()
        } else {
            (0.075 * totalForTax).toFloat()
        }
    }

    fun calculateBill(): Double {
        return energyCharges + fixedCharge + fuelCharge + tax
    }
}
fun main(){
    println("Enter the units consumed")
    val unit = Integer.valueOf(readLine())
    println("Enter the Load connected")
    val load = readLine()!!.toFloat()
    println("Enter the area in which you reside\n0 for urban\n1 for rural")
    val area = Integer.valueOf(readLine())
    println("Is the consumer a BPL type true if yes else type false")
    val isBpl = readLine()!!.toBoolean()
    println("Enter the fuel charge")
    val fuelCharge = readLine()!!.toFloat()

    val calc = BillCalculator(unit,fuelCharge,0,0.0,0f,area,isBpl,load)
    calc.passCharges()
    calc.calculateFuelCharges()
    calc.calculateFixedCharge()
    calc.calculateTax()
    val bill = calc.calculateBill()
    println("Bill Amount: $bill")
}