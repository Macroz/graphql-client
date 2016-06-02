# graphql-client

Hiccup-like syntax for GraphQL in Clojure(Script).

## Usage

Takes a GraphQL query in Hiccup syntax like this.

```
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

```
{plan(fromPlace:\"Kamppi, Helsinki\",from:{lat:60.168992,lon:24.92366},toPlace:\"Pisa, Espoo\",to:{lat:60.175294,lon:24.684855},numItineraries:5,modes:\"BICYCLE_RENT,BUS,WALK\",walkReluctance:2.1,walkBoardCost:600,minTransferTime:180,walkSpeed:1.2,maxWalkDistance:10000){itineraries{walkDistance,duration,legs{mode startTime endTime from{lat lon name stop{code name}},to{lat lon name},agency{id},distance legGeometry{length points}}}}}
```

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
