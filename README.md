# RetroPreference
学习retrofit以后的demo，用retrofit的方式来使用sharedpreferences。

** 用法
1. 定义一个interface
2.编写相应的存和取的方法

```java
public interface Setting {

    @Preference(file = "setting", key = "name")
    @Get
    String name(@DefaultValue String def);

    @Preference(file = "setting", key = "name")
    @Set
    boolean saveName(@Value String name);

}
```
`@Preference` 声明配置文件的文件名，该配置项的key
`@Get`或`@Set` 声明这个方法是用来取或者存
如果是set：@Value代表将要存入的数据，类型以参数类型为准，且返回值必须为boolean
如果是get：@DefaultValue代表取不到的默认值，类型以该方法返回值为准