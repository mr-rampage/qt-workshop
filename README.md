# qt-workshop

The purpose of this workshop is to introduce Property Based Testing with [Quick Theories](https://github.com/quicktheories/QuickTheories). The workshop is based off of the Kata on Chapter 6 of [Property-Based Testing with PropEr, Erlang, and Elixir](https://propertesting.com/) by [Fred Hebert](https://ferd.ca/). I highly recommend this book.

# The Kata

Given the following table:

| Item | Unit Price | Special Price |
|------|------------|---------------|
| A    | 50         | 3 for 130     |
| B    | 30         | 2 for 45      |
| C    | 20         |               |
| D    | 15         |               |

Given a item price catalog, when given a list of items to purchase, then calculate the total. This project provides a sample skeleton, but feel free to adjust the code as needed.

## Vague Requirements:

- [ ] Item names are unique
- [ ] List of items have no set length
- [ ] List of items are unordered
- [ ] Unit prices are whole numbers
- [ ] All items have a unit price
- [ ] Special prices can be applied multiple times
- [ ] Special prices are optional

# Step 1: Identify the properties (5 minutes, +10 minute discussion)

> Property based testing frameworks check the truthfulness of properties. A property is a statement like: for all (x, y, ...) such as precondition(x, y, ...) holds property(x, y, ...) is true. - `fast-check` docs

As a group, let's identify the properties of this function:

## Things to look for

Here are some patterns from the [Quick Theories](https://github.com/quicktheories/QuickTheories) documentation.

- Invariant Pattern "Some things never change"
- The inverse function pattern "There and back again"
- Idempotence "The more things change, the more they stay the same"
- Analogous function pattern "Different paths same destination"

# Step 2: Creating generators (15 minutes, +10 minute discussion)

Working with the [quick theories basic generators](https://github.com/quicktheories/QuickTheories), complete the generator 
`CartDSL.purchaseList` to generate a list of items to purchase.

# Step 3: Test the property & implement (15 minutes, +10 minute discussion)

Test the property where the cart total is the sum of the purchase list given all the products have no special prices.

- Try configuring `quick theories` to run through more or fewer examples.

# Step 4: Refactor generator to include expectation (5 minutes, +10 minute discussion)

Refactor the carts generator to produce a random cart and the appropriate expectation.

# Discussion: Adding in special prices (15 minutes)

# Extra: Negative Testing (15 minutes, +10 minute discussion)

- Identify very, very broad properties
- Apply very, very broad inputs to verify that the system doesn't crash
