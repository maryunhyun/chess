# ♕ BYU CS 240 Chess

This project demonstrates mastery of proper software design, client/server architecture, networking using HTTP and WebSocket, database persistence, unit testing, serialization, and security.

## 10k Architecture Overview

The application implements a multiplayer chess server and a command line chess client.

[![Sequence Diagram](10k-architecture.png)](https://sequencediagram.org/index.html#initialData=C4S2BsFMAIGEAtIGckCh0AcCGAnUBjEbAO2DnBElIEZVs8RCSzYKrgAmO3AorU6AGVIOAG4jUAEyzAsAIyxIYAERnzFkdKgrFIuaKlaUa0ALQA+ISPE4AXNABWAexDFoAcywBbTcLEizS1VZBSVbbVc9HGgnADNYiN19QzZSDkCrfztHFzdPH1Q-Gwzg9TDEqJj4iuSjdmoMopF7LywAaxgvJ3FC6wCLaFLQyHCdSriEseSm6NMBurT7AFcMaWAYOSdcSRTjTka+7NaO6C6emZK1YdHI-Qma6N6ss3nU4Gpl1ZkNrZwdhfeByy9hwyBA7mIT2KAyGGhuSWi9wuc0sAI49nyMG6ElQQA)

## IntelliJ Support

Open the project directory in IntelliJ in order to develop, run, and debug your code using an IDE.

## Maven Support

You can use the following commands to build, test, package, and run your code.

| Command                    | Description                                     |
| -------------------------- | ----------------------------------------------- |
| `mvn compile`              | Builds the code                                 |
| `mvn package`              | Run the tests and build an Uber jar file        |
| `mvn package -DskipTests`  | Build an Uber jar file                          |
| `mvn install`              | Installs the packages into the local repository |
| `mvn test`                 | Run all the tests                               |
| `mvn -pl shared test`     | Run all the shared tests                        |
| `mvn -pl client exec:java` | Build and run the client `Main`                 |
| `mvn -pl server exec:java` | Build and run the server `Main`                 |

These commands are configured by the `pom.xml` (Project Object Model) files. There is a POM file in the root of the project, and one in each of the modules. The root POM defines any global dependencies and references the module POM files.

### Running the program using Java

Once you have compiled your project into an uber jar, you can execute it with the following command.

```sh
java -jar client/target/client-jar-with-dependencies.jar

♕ 240 Chess Client: chess.ChessPiece@7852e922
```
https://sequencediagram.org/index.html?presentationMode=readOnly#initialData=IYYwLg9gTgBAwgGwJYFMB2YBQAHYUxIhK4YwDKKUAbpTngUSWDAEooDmSAzmFMARDQVqhFHXyFiwUgBF+wAIIgQKLl0wATeQCNgXFDA3bMmdlAgBXbDADEaYFQCerDt178kg2wHcAFkjAxRFRSAFoAPnJKGigALhgAbQAFAHkyABUAXRgAegt9KAAdNABvfMp7AFsUABoYXDVvaA06lErgJAQAX0xhGJgIl04ePgEhaNF4qFceSgAKcqgq2vq9LiaoFpg2joQASkw2YfcxvtEByLkwRWVVLnj2FDAAVQKFguWDq5uVNQvDbTxMgAUQAMsC4OkYItljAAGbmSrQgqYb5KX5cAaDI5uUaecYiFTxNAWBAIQ4zE74s4qf5o25qeIgab8FCveYw4DVOoNdbNL7ydF3f5GeIASQAciCWFDOdzVo1mq12p0YJL0ilkbQcSMPIIaQZBvSMUyWYEFBYwL53hUuSgBdchX9BqK1VLgTKtUs7XVgJbfOkIABrdBujUwP1W1GChmY0LYyl4-UTIkR-2BkNoCnHJMEqjneORPqUeKRgPB9C9aKULGRYLoMDxABMAAYW8USmWM+geugNCYzJZrDZoNJHjBQRBOGgfP5Aph62Ei9W4olUhlsjl9Gp8R25SteRsND1i7AEzm9XnJjAEFOkGgbd75Yf+dncZeDXSYyaYI8Xm99wdH5hRdQFyDBCFZQ+O14URL1o0dWNayGd9ThTFBiVJckdSpZNCUNS5vzuU0UFZC0rUfT4EOA51IldSVpSg215S7CsZ3VTUy2op043PVDqXQ0t0zYt9dTQ-DkNPISrW7LNT2QxdGxgVt21KVjM17NB+1McwrFsMwUFDSd2EsZgbD8AIgmQBt-ikxIZAg9JgU3bcuF3exhMzKtqBrPixIE-D4lvEzLTmdT0AOHDc0-CJjWIwwUAQJ4UHI61wrQICeJFMCHPBJy0ytWCICRdLuKQwsUP8vD81TEkyVE3Cr1pCq7Lq8l5IqxTmzbEw+wHXTh2mDQJzcGAAHE7Uxcy5yskJmEGOyEjG4Eslydg7WKUqOsiKKP0Em83Am6ouDCzyIoa6L0IuOLGV-J5UtOmS2My8q6LAkFwUhAry0zIqSv9MqMWQ3bxJqjCYDai69ok2KiNuv8jtUSi7ReoHQKBCCvv3P7f0mwGQL8xqDQePGQYCsHJJXEnju8-pOusjButUkp1uOnpMD6nSh1sbALCgbAkvgM0DER2dLIXBn5uXHzV2SNJVpyVmUE2s60A7JWJTtE8V3+MnquvZlSMCRG5g1n1vtkyLE2himjTh+4YEN1kTbNljVdRkC3vFd1PSVsUZDqCzAnZJ8Vm0BBQCDEPljqV2DA43Hqnx51CcuwLE5Qf2odBgsFqpjOs46wYuuUtt1btf3NO0wc9JsRxEtvbwYAAKQge9xpgmxw8jiW5ts-PkmeBWlZVp7Mw7RS4AgW8oFjiuZG1mWLj1prwYAKzbtATcn6foDn6p-d9d3s-JgtCMQn8EbtU35492iAQxz6oT9mQcaV5O4y98Cn-gSWp5nt+MEABkbpGKJBALvKAmRsYJ3fjdXiO1rY51qlhE++tmp5xlpheqRc6ySyZhzLS-VuY2DMMAZwiBSKwGANgAWhBLxi3nIpfuWD7KOWcrkIwtNfLhBXsTR2SU8BzCtheZBBEYDwKZIIqAwiP7ZXiLlFawIYAKFBKCL06h4HyIkewlRaiM6aPttoxR+VVHqLLIYi+BNEGiNPig+qfCrotXzpDXBv85oEL6kAA
