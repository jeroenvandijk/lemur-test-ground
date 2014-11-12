# Lemur Test environment

A test environment for Lemur's new lib feature https://github.com/TheClimateCorporation/lemur/issues/23

## Usage

Via command line:
    
    make launch-local
    
Via repl:
    LEMUR_AWS_ACCESS_KEY=foo LEMUR_AWS_SECRET_KEY=bar lein repl
    user=> (launch-local)
    
Running via the REPL only works once at moment. Needs fixing

## License

Copyright © 2014 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
