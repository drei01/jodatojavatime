# Converting Joda to Java Time

This repo shows step-by-step how to convert from Joda time to the Jave Time code.

Check out the following branches to see each stage

| Branch | Description |
|--------|-------------|
| master | A class `Cycle` with two dates and some functionality implemented in Joda time (with tests) |
| 1-convert-tests-to-java-time | The tests are converted to Java Time using equivalent methods (they still pass) |
| 2-convert-cycle-to-java-time | The `Cycle` is converted to Java Time (check the tests, there's a problem somewhere) |
| 3-fix-broken-code | The problem found in the previous step is fixed (all the tests pass again) |
