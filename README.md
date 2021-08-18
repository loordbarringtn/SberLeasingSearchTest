
### Hey!

### This project contains automation test of Website: https://www.sberleasing.ru

### Technology Stack that i used:
Java | Intellij Idea | Selenide | Selenoid | Gradle |
:---------: | :---------: | :---------: | :---------: | :---------: 
![image](https://user-images.githubusercontent.com/86851419/129460968-afa39d7b-2b6c-4cfe-a4d7-a832b2241964.png) | ![image](https://user-images.githubusercontent.com/86851419/129460290-f0bb0b77-ced9-41d0-96e3-3b51e6e9c1c4.png) | ![image](https://user-images.githubusercontent.com/86851419/129460419-c463163c-0c76-46d6-9416-0ee4ec26b879.png) |![image](https://user-images.githubusercontent.com/86851419/129460507-e47ea71f-e8e0-4dfa-a5be-fa88bbd79522.png) | ![image](https://user-images.githubusercontent.com/86851419/129461122-84b505ee-c082-44da-a100-226d47f6a665.png)
**JUnit5** | **Jenkins** | **Allure** |**GitHub** | **Telegram**
![image](https://user-images.githubusercontent.com/86851419/129460565-68f2b13e-0f71-4510-9266-21c1bf95d55b.png)| ![image](https://user-images.githubusercontent.com/86851419/129460671-00864a0c-a1e9-415b-9df3-25062b4ee5ca.png) | ![image](https://user-images.githubusercontent.com/86851419/129460019-82755ea3-50b7-4d91-a5d0-f98430544079.png) |![image](https://user-images.githubusercontent.com/86851419/129461200-cc199a3e-8a0b-4879-883b-ca0c7b8e7d9c.png) |![image](https://user-images.githubusercontent.com/86851419/129460847-d77a8a9d-0022-440f-b0f4-96dde9d22e58.png)

### :bar_chart: Results of test run in Allure report:
<img src="https://github.com/loordbarringtn/SberLeasingSearchTest/blob/master/AllureOverview.png?raw=true" >

### :clipboard: Allure report with steps and attachments:
<img src="https://github.com/loordbarringtn/SberLeasingSearchTest/blob/master/AllureSteps.png?raw=true" >

### Telegram channel for report with results [(Telegram channel)](https://t.me/joinchat/fWDcQmj4ysBiMmQy)
*telegram integration made through 3d-parties library, settings passed through Jenkins job

### For run remote tests need fill remote.properties or to pass value:

* browser (default chrome)
* browserVersion (default 89.0)
* browserSize (default 1920x1080)
* browserMobileView (mobile device name, for example iPhone X)
* remoteDriverUrl (url address from selenoid or grid)
* videoStorage (url address where you should get video)
* threads (number of threads)


Run tests with filled remote.properties:
```bash
gradle clean test
```

Run tests with not filled remote.properties:
```bash
gradle clean -DremoteDriverUrl=https://user1:1234@selenoid.autotests.cloud/wd/hub/ -DvideoStorage=https://selenoid.autotests.cloud/video/ -Dthreads=1 test
```

Serve report:
```bash
allure serve build/allure-results
```


For further development there are some example tests in src/test/java/cloud.autotests/tests/demowebshop
* remove @Disabled("...") annotation to run tests
```bash
gradle clean demowebshop
```

:heart: <a target="_blank" href="https://qa.guru">qa.guru</a><br/>
:blue_heart: <a target="_blank" href="https://t.me/qa_automation">t.me/qa_automation</a>


