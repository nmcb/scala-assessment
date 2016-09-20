# Assessment

This assessment is used to provide a topic for interviewing a candidate for
a Scala role.

## Case:

In Januari 2015 our team started developing an IoT application using the
libraries Spray and [Akka](http://www.akka.io).  
On year later, because of changed requirements we have to make some major changes
to the application. We use this opportunity to also change the application
because of changed insights and having learned to use these libraries.

In particular, we want to replace Spray with another HTTP framework.
After looking at several alternatives, we choose [Finatra](https://twitter.github.io/finatra/), a Twitter framework based on [Finagle](http://twitter.github.io/finagle/) and [TwitterServer](http://twitter.github.io/twitter-server/).

Even though we selected a new http framework, we would like to reuse as much code of the original [Akka](http://www.akka.io) application as possible.

## Question

* How would you integrate Finatra and Akka ?
* Implement an endpoint which uses an Akka actor.
* Write an integration test for the endpoint using ScalaTest.

## Feedback: [2016-09-19, nmcb]

Some "historical" questions that pop-up when reading the assessment based on quotes in the original text:

*  "Because of changed requirements we have to make some major changes
to the application".
Q: Which requirements changed ?
Q: What was the change (for each of them) ?

*  "We use this opportunity to also change the application
because of changed insights and having learned to use [the spray and akka] libraries".
Q: Is this really an opportunity, wouldn't it be better to target functionality and tech-debt (i.e. the continuous stream of insight into the "best" architecture) in separate time-slots ?
Q: Which non-functional requirements / architectural constraints lead to the replacement of spray by Finatra ?
Q: How was the interaction between the spray and akka parts of the original application implemented ?

*  "After looking at several alternatives, we choose Finatra".
Q: What were the alternatives ?
Q: Does something like a knock-down-decision matrix exist ?
Q: Against which criteria where the alternatives assessed ?

*  "A Twitter framework based on Finagle and TwitterServer".
Q: Any notable technical features that Finagle or TwitterServer provide us ?

## Assumptions: [2016-09-19, nmcb]

1.  All information processed by Finagle is transient, i.e. does not survive a system power down.
2.  Code that depends on Finagle depends on Akka via one or more (possibly remote) actorrefs.
3.  Akka dependend code is concidered more stable than code that depends on Finagle.

