# Vista, technical analysis

[![][travis img]][travis]
[![][codecov img]][codecov]
[![][maven img]][maven]
[![][license img]][license]

> Vista is currently under heavy development 🛠

Vista is a technical analysis library for Kotlin, inspired in [Pine Script][ps] from TradingView.

```kotlin
val data = dataOf("https://pallocchi.github.io/vista/amzn.csv")

val fast = data.sma(9)
val slow = data.sma(30)

when {
    fast crossOver slow -> print("I'm going long!")
    fast crossUnder slow -> print("I'm going short!")
}
```

Full documentation is available [here](https://pallocchi.github.io/vista).

## Gradle

First you need to add the Bulltimate repository:

```kts
repositories {
    maven {
        setUrl("https://dl.bintray.com/bulltimate/maven/")
    }
}
```

After that the library can be added as a usual dependency:

```kts
dependencies {
    implementation('com.bulltimate:vista:0.2.0')
}
```
 
## LICENSE

MIT License

Copyright (c) 2020 Pablo Pallocchi

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

[ps]: https://www.tradingview.com/pine-script-docs/en/v4/Introduction.html

[travis]:https://travis-ci.org/bulltimate/vista-kt
[travis img]:https://travis-ci.org/bulltimate/vista-kt.svg?branch=master

[license]:LICENSE.txt
[license img]:https://img.shields.io/github/license/mashape/apistatus.svg

[maven]:https://bintray.com/bulltimate/maven/vista/_latestVersion
[maven img]:https://api.bintray.com/packages/bulltimate/maven/vista/images/download.svg

[codecov]:https://codecov.io/gh/bulltimate/vista-kt
[codecov img]:https://codecov.io/gh/bulltimate/vista-kt/branch/master/graph/badge.svg
