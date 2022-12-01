package com.designingtypes

import java.util.Date

// Credits:
// Scott Wlaschin - The "Designing With Types" series - https://fsharpforfunandprofit.com/series/designing-with-types/

// ---- PROBLEM ----
// REPRESENT THE STATE MACHINE OF PACKAGE DELIVERY
// THE STATES ARE
//    -> UNDELIVERED
//    -> OUT FOR DELIVERY
//    -> DELIVERED

case class Id[T](value: String)
abstract final class PackageId

sealed trait Package
case class Undelivered(packageId: Id[PackageId]) extends Package
case class OutForDelivery(packageId: Id[PackageId]) extends Package
case class Delivered(packageId: Id[PackageId], deliveryDate: Date, deliveryComments: String) extends Package


object Package {
  def putOnTruck(p: Package): Package = {
    p match {
      case Undelivered(packageId) => OutForDelivery(packageId = packageId)
      case OutForDelivery(_) => p
      case Delivered(_, _, _) => p
    }
  }

  def deliver(p: Package): Package = {
    p match {
      case Undelivered(packageId) => ???
      case OutForDelivery(packageId) => ???
      case Delivered(packageId, deliveryDate, deliveryComments) => ???
    }
  }
}