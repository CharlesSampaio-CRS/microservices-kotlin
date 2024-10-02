package com.kt.microservices.controller

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class MathController {

    @RequestMapping(value = ["/sum/{a}/{b}"])
    fun sum(
        @PathVariable(value = "a") a: String?,
        @PathVariable(value = "b") b: String?
    )
            : Double {
        if (!isNumeric(a) || !isNumeric(b)) throw UnsupportedOperationException("Please set a numeric value.")
        return convertToDouble(a) + convertToDouble(b)
    }

    @RequestMapping(value = ["/subtration/{a}/{b}"])
    fun subtraction(
        @PathVariable(value = "a") a: String?,
        @PathVariable(value = "b") b: String?
    )
            : Double {
        if (!isNumeric(a) || !isNumeric(b)) throw UnsupportedOperationException("Please set a numeric value.")
        return convertToDouble(a) - convertToDouble(b)
    }

    @RequestMapping(value = ["/multi/{a}/{b}"])
    fun mult(
        @PathVariable(value = "a") a: String?,
        @PathVariable(value = "b") b: String?
    )
            : Double {
        if (!isNumeric(a) || !isNumeric(b)) throw UnsupportedOperationException("Please set a numeric value.")
        return convertToDouble(a) * convertToDouble(b)
    }

    @RequestMapping(value = ["/mean/{a}/{b}"])
    fun mean(
        @PathVariable(value = "a") a: String?,
        @PathVariable(value = "b") b: String?
    )
            : Double {
        if (!isNumeric(a) || !isNumeric(b)) throw UnsupportedOperationException("Please set a numeric value.")
        return convertToDouble(a) + convertToDouble(b) / 2
    }

    @RequestMapping(value = ["/square/{a}"])
    fun square(
        @PathVariable(value = "a") a: String?
    )
            : Double {
        if (!isNumeric(a)) throw UnsupportedOperationException("Please set a numeric value.")
        return Math.sqrt(convertToDouble(a))
    }

    @RequestMapping(value = ["/divison/{a}/{b}"])
    fun divison(
        @PathVariable(value = "a") a: String?,
        @PathVariable(value = "b") b: String?
    )
            : Double {
        if (!isNumeric(a) || !isNumeric(b)) throw UnsupportedOperationException("Please set a numeric value.")
        return convertToDouble(a) / convertToDouble(b);
    }


    private fun convertToDouble(strNumber: String?): Double {
        if(strNumber.isNullOrBlank()) return 0.0
        val number = strNumber.replace(",".toRegex(),".")
        return if (isNumeric(number)) number.toDouble() else 0.0
    }

    private fun isNumeric(strNumber: String?): Boolean {
        if(strNumber.isNullOrBlank()) return false
        val number = strNumber.replace(",".toRegex(),".")
            return number.matches("""[-+]?[0-9]*\.?[0-9]+""".toRegex())
    }

}