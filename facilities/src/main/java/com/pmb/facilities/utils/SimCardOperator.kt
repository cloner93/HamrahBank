package com.pmb.facilities.utils

enum class SimCardOperator(val id: Int, val operatorName: String, val prefixes: List<String>) {
    HAMRAH_AVAL(0, "همراه اول", listOf(
        "0910", "0911", "0912", "0913", "0914", "0915", "0916", "0917", "0918", "0919",
        "0990", "0991", "0992", "0993", "0994"
    )),
    IRCELL(1, "ایرانسل", listOf(
        "0930", "0933", "0935", "0936", "0937", "0938", "0939",
        "0901", "0902", "0903", "0904", "0941"
    )),
    RIGHTEL(2, "رایتل", listOf(
        "0920", "0921", "0922"
    ));


    companion object {
        // This method will return the operator based on the prefix
        fun getOperatorByPrefix(prefix: String): SimCardOperator? {
            return SimCardOperator.entries.firstOrNull { it.prefixes.contains(prefix) }
        }
    }
}
