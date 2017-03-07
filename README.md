# graphql-client

Hiccup-like syntax for [GraphQL](http://graphql.org/) in Clojure(Script).

## Usage

Takes a GraphQL query in Hiccup syntax like this.

```clj
(def plan-query [[:plan {:fromPlace "Kamppi, Helsinki"
                         :from {:lat 60.168992 :lon 24.92366}
                         :toPlace "Pisa, Espoo"
                         :to {:lat 60.175294 :lon 24.684855}
                         :numItineraries 5
                         :modes "BICYCLE_RENT,BUS,WALK"
                         :walkReluctance 2.1
                         :walkBoardCost 600
                         :minTransferTime 180
                         :walkSpeed 1.2
                         :maxWalkDistance 10000}
                  [:itineraries
                   :walkDistance
                   :duration
                   [:legs :mode :startTime :endTime
                    [:from :lat :lon :name [:stop :code :name]]
                    [:to :lat :lon :name]
                    [:agency :id]
                    :distance
                    [:legGeometry :length :points]]]]])
```

And produces a GraphQL query string like this (without any pretty printing).

```clj
{plan(fromPlace:"Kamppi, Helsinki",from:{lat:60.168992,lon:24.92366},toPlace:"Pisa, Espoo",to:{lat:60.175294,lon:24.684855},numItineraries:5,modes:"BICYCLE_RENT,BUS,WALK",walkReluctance:2.1,walkBoardCost:600,minTransferTime:180,walkSpeed:1.2,maxWalkDistance:10000){itineraries{walkDistance,duration,legs{mode startTime endTime from{lat lon name stop{code name}},to{lat lon name},agency{id},distance legGeometry{length points}}}}}
```

You can execute this query in the [Digitransit GraphiQL console](http://dev.hsl.fi/graphql/console/?query={plan%28fromPlace%3A%22Kamppi%2C%20Helsinki%22%2Cfrom%3A{lat%3A60.168992%2Clon%3A24.92366}%2CtoPlace%3A%22Pisa%2C%20Espoo%22%2Cto%3A{lat%3A60.175294%2Clon%3A24.684855}%2CnumItineraries%3A5%2Cmodes%3A%22BICYCLE_RENT%2CBUS%2CWALK%22%2CwalkReluctance%3A2.1%2CwalkBoardCost%3A600%2CminTransferTime%3A180%2CwalkSpeed%3A1.2%2CmaxWalkDistance%3A10000%29{itineraries{walkDistance%2Cduration%2Clegs{mode%20startTime%20endTime%20from{lat%20lon%20name%20stop{code%20name}}%2Cto{lat%20lon%20name}%2Cagency{id}%2Cdistance%20legGeometry{length%20points}}}}}%0A).

## TODO

- Creating queries based on the query string
- Creating introspection queries
- Metadata validation generation using e.g. Schema
- Useful transformations of the results
- Comprehensive support for all kinds of things
- CLJC

## License

Copyright © 2016 Markku Rontu

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
