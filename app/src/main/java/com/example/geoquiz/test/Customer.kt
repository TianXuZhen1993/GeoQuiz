package com.example.geoquiz.test


/**
 *
 *
 * @author TXZ
 * @version 1.0
 * created by 2024/6/7 20:54
 */

data class Customer(
    var belongSale: String?,

    var belongSaleDept: String?,

    var customerName: String = ""
) : ExpandDate()
