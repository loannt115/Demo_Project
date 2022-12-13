## Command to start Hub
```
java -jar selenium-server-4.1.2.jar hub
```

## Command to start Node
This node will register:
* Chrome
* Firefox
* Safari

Note: IF you are on Windows OS, please remove part related to safari and add for Edge
```
java -jar -Dwebdriver.<type>.<name>s path/to/selenium/server.jar node --config /path/to/nodeConfig.json
java -jar -Dwebdriver.gecko.driver=geckodriver -Dwebdriver.chrome.driver=chromedriver selenium-server-4.1.2.jar node --config node_config.json

```

NOTE: on Windows need to specify extension like gecko.exe, chrome.exe

## Next
In second stage, we will learn about how to set up using Docker