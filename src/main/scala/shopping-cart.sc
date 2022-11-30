// Credits:
// Scott Wlaschin - "Designing for Correctness" - https://fsharpforfunandprofit.com/posts/designing-for-correctness/

// ---- PROBLEM STATEMENT ----
// You are designing an e-commerce site which has a shopping cart and
// you are given the following requirements.
//   -> You can only pay for a cart once.
//   -> Once a cart is paid for, you cannot change the items in it.
//   -> Empty carts cannot be paid for.




















// Let us step back and think about the behaviour a shopping cart
// -> A Shopping Cart can be Empty, Active or PaidFor
// -> When you add an item to an Empty cart, it becomes Active
// -> When you remove the last item from an Active cart, it becomes Empty
// -> When you pay for an Active cart, it becomes PaidFor
// -> a PaidFor cart cannot become Empty or Active again
// -> you can add items to Empty and Active carts ONLY
// -> you can remove items from Active carts ONLY
// -> you can pay for carts which are ONLY Active
// so this is a state machine which looks like https://fsharpforfunandprofit.com/posts/designing-for-correctness/ShoppingCart.png

final abstract class Sku
case class Id[T](value: String)

case class Item(sku: Id[Sku], quantity: Int)

sealed trait ShoppingCart
case object Empty extends ShoppingCart
case class Active(unpaidItems: Set[Item]) extends ShoppingCart
case class PaidFor(paidItems: Set[Item], amount: Float) extends ShoppingCart

object ShoppingCart {
  def provisionCart(): ShoppingCart = {
    Empty
  }

  def addItem(item: Item, sc: ShoppingCart): ShoppingCart = {
    sc match {
      case Active(items) => Active(items + item)
      case Empty => Active(Set(item))
      case PaidFor(_, _) =>
        println("cart is already paid for!")
        sc
    }
  }

  def removeItem(item: Item, sc: ShoppingCart): ShoppingCart = {
    sc match {
      case Active(items) => Active(items - item)
      case Empty =>
        print("cart is empty!")
        sc
      case PaidFor(_, _) =>
        print("cart is already paid for!")
        sc
    }
  }

  def payForCart(sc: ShoppingCart, amount: Float): ShoppingCart = {
    sc match {
      case Active(items) => PaidFor(items, amount)
      case Empty =>
        print("cannot pay for an empty cart!")
        sc
      case PaidFor(_, _) =>
        print("cart already paid for!")
        sc
    }
  }
}

// this is okay, when you call the methods at the invalid states, nothing happens, but it is not enforced at
// compile time! So lets solve for it!
// exercise for scala pros to show us!


