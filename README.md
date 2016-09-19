## Appium_MTBF
基于JUnit，使用appium编写的安卓自动化测试用例

###用到的第三方库
log4j-1.2.7
java-client-2.2.0
selenium-java-2.44.0
selenium-server-standalone-2.44.0
mysql-connector-java-5.1.39

###主要逻辑
1. base中编写基础测试用例
2. base中编写基础用例的执行顺序
3. 预期结果进行截图，自动化过程中自动截图，与预期结果的截图对比（RGB），相似度99%以上判定为通过
4. 结果保存到mysql数据库中进行统计