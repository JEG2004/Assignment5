**Part 1:** Specification and Structural testing for Barnes and Noble

Barnes and Nobles has two methods and one constructor

Constructor takes in book database and process 

**Specification Testing:** 5 different tests: 

    1: Valid test: runs fully(retrieve books0
    2: Stock too low(retrieve books) 
    3: Null order summary(Get price for Cart)

There are some question as to could there be a test for some other things, but then
it gets into testing things outside the Barnes and Noble class. 



**Structual-based Testing:** 

    For this test, I really did the same things just
    more intentional about branch and line coverage

*note to self: I can create the book and use the same book in the setup.

**Part 2:** 
Create a repo and push to repo 
**WorkFlow:** I did not do this part so there is no link.


**Part 3:**

**Specification:** 
I decided these three tests because all 
the other ones I thought of were just going to test the 
mocks which seemed dumb.

**Structural:** 
When doing this test, I think it made more sense to test 
the mock especially to get 100% branch coverage.


**Integration Testing:** Now I am really testing the connections 
between all the classes in the Amazon package.

**Unit Testing:** For unit Testing I know we have to test everything! However, 
I don't know how to test the Connection/Database. Otherwise, I tested 
elements of the Shopping Cart adapter and Amazon. However, I deleted my 
test for ShoppingCartAdapter numberOfItems because I added an Item and it 
said that there were 0 Items. Overall, I add a few Items, make sure they are
there, make sure the rules work, and make sure that Items are only 
added once.




