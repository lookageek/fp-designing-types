package com.designingtypes

import java.util.Date


// ---- PROBLEM ----
// Represent all of the states of an order with relevant data for that state!
// the states are these
// -> UNPAID
// -> PAID
// -> SHIPPED
// -> DELIVERED
// -> RETURNED

final abstract class OrderId
final case class Id[T](value: String)

sealed trait Order
case class Unpaid(id: Id[OrderId])
case class Paid(id: Id[OrderId], paidDate: Date, amountPaid: Float)
case class Shipped(paidOrder: Paid, shippedDate: Date, shipingMethod: String) // <- Method can be again, a trait and a set of enums
case class Delivered(shippedOrder: Shipped, deliveredDate: Date, deliveryNotes: String)
case class Returned(deliveredOrder: Delivered, returnedDate: Date, returnReason: String)


