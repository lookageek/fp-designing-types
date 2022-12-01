package com.designingtypes

// Credits:
// Scott Wlaschin - The "Designing With Types" series - https://fsharpforfunandprofit.com/series/designing-with-types/

// Step 1: Group attributes which will change together in a transaction

































// Step 2: Use refined types to validate email address, postcodes (Sophie) https://github.com/fthomas/refined
import eu.timepit.refined.api.Refined
import eu.timepit.refined.string._



































// Step 3: New requirement comes in...
// -> a Contact must have an email or postal address for us to communicate
// but at the moment, our Contact requires both postal address AND email
// we can make both of them optional and try...
// but we want to make it impossible to represent a Contact which has no email or postal address





































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











































// see those boolean flags for email verification? we can change that to a sum type too!

















