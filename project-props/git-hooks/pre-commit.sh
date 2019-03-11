#This pre-hook checks against java code violations, failing builds and failing tests. It ensures these pass before a successful commit

  #!/bin/bash

  # Returns 0 if none of the gradle tests fail.

  # Quietly perform tests. On fail, this will print info about failed tests.
          #./gradlew test --info
          #./gradlew checkstyle
          #./gradlew findbugs
          #./gradlew pmd
          #./gradlew lint
  # check will run all gradlew routines above. To single out a check execute respective command
          ./gradlew check

  # If ./gradlew returned anything other than 0, the tests failed...
  if [ $? -ne 0 ] ; then
      echo "!Aborted commit due to violations or fails"
      exit 1
  fi