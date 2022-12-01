package com.designingtypes

// Credits:
// Scott Wlaschin - The "Designing With Types" series - https://fsharpforfunandprofit.com/series/designing-with-types/

// Step 1: Group attributes which will change together in a transaction
case class Name(firstName: String, middleName: String, lastName: String)
case class EmailContact(emailAddress: String, isEmailVerified: Boolean)
case class PostalAddress(address: String, city: String, postcode: String, isAddressValid: Boolean)

case class Contact(name: Name, email: EmailContact, postalAddress: PostalAddress)






















// Step 2: Use refined types to validate email address, postcodes (Sophie) https://github.com/fthomas/refined
import eu.timepit.refined.api.Refined
import eu.timepit.refined.string._





















// Step 3: New requirement comes in...
// -> a Contact must have an email or postal address for us to communicate
// but at the moment, our Contact requires both postal address AND email
// we can make both of them optional and try...
// but we want to make it impossible to represent a Contact which has no email or postal address

sealed trait ContactInfo
// sealed abstract class vs sealed trait (pointer to ask)
case class EmailOnly(email: EmailContact) extends ContactInfo // <- lookee, we now can easily compose types
case class PostalOnly(postalAddress: PostalAddress) extends ContactInfo
case class EmailAndPost(emailContact: EmailContact, postalAddress: PostalAddress) extends ContactInfo
// OR
// case class EmailAndPost(emailOnly: EmailOnly, postalOnly: PostalOnly) extends ContactInfo

case class ContactWithNewRule(name: Name, contactInfo: ContactInfo)

object Contact {
  def updatePostalAddress(postalAddress: PostalAddress, ci: ContactInfo): ContactInfo = {
    ci match {
      case EmailOnly(email) => EmailAndPost(emailContact = email, postalAddress = postalAddress)
      case PostalOnly(_) => PostalOnly(postalAddress = postalAddress) // <- update address
      case EmailAndPost(emailContact, _) => EmailAndPost(emailContact = emailContact, postalAddress = postalAddress) // <- do not include this case
    }
  }
}





























// Step 4: How this helps when requirements change
// Business now needs to support phone number as one more contact point
// extending our contact point trait will lead to  4C2 + 4C3 + 4 case classes
// even when you add all of the case classes, think of how massively long exhaust-ive pattern matching is going to get!
// and the next time you add one more set of case classes, then you need to go back and change all of the pattern matching
// EXHAUST-ING!





























// thinking closely, the real domain requirement is this ->
// “To contact a customer, there will be a list of contact methods.
// Each contact method could be an email OR a postal address OR a phone number”.
// ... DDD - "refactoring towards deeper insight"
case class PhoneNumber(callingCode: Integer, number: Long)
sealed trait ContactMethod
case class Email(emailContact: EmailContact) extends ContactMethod
case class Postal(postalAddress: PostalAddress) extends ContactMethod
case class HomePhone(phoneNumber: PhoneNumber) extends ContactMethod
case class WorkPhone(phoneNumber: PhoneNumber) extends ContactMethod

case class ContactWithListOfContactMethods(name: Name, contactMethods: List[ContactMethod])

// but this list could be empty!
// so we will go ahead and reflect what REALLY business needs
case class ContactBusinessReallyNeeds(name: Name, primaryContactMethod: ContactMethod, secondaryContactMethods: List[ContactMethod])





























// see those boolean flags for email verification? we can change that to a sum type too!
sealed trait EmailContactV2
case class VerifiedEmail(emailAddress: String) extends EmailContactV2
case class UnverifiedEmail(emailAddress: String) extends EmailContactV2

// an event handler
// "Guideline: Event handling functions should always accept and return the entire state machine"
object EmailContactV2 {
  def verifyEmail(email: EmailContactV2): EmailContactV2 = email match {
    case VerifiedEmail(_) => email
    case UnverifiedEmail(emailAddress) => VerifiedEmail(emailAddress = emailAddress)
  }
}







// todo

// see this order thing, this is small too
// https://fsharpforfunandprofit.com/posts/designing-with-types-representing-states/#using-explicit-cases-to-replace-implicit-conditional-code
















