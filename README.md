
# 原生mybatis（xml）多数据源


# pom.xml依赖
``` 
<!--mybatis-->
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>1.3.2</version>
</dependency>
<!--mysql-->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
<!--lombak-->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```
# 创建数据库

创建数据库表一：

```
create database mydb;

use mydb;

DROP TABLE IF EXISTS `book`;
SET character_set_client = utf8mb4 ;
CREATE TABLE `book` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `author` varchar(20) DEFAULT NULL,
  `publish` varchar(20) DEFAULT NULL,
  `pages` int(10) DEFAULT NULL,
  `price` float(10,2) DEFAULT NULL,
  `bookcaseid` int(10) DEFAULT NULL,
  `abled` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

INSERT INTO `book` VALUES
(1,'解忧杂货店','东野圭吾','南海出版公司',102,27.30,2,0),
(2,'追风筝的人','卡勒德·胡赛尼','上海人民出版社',230,33.50,3,1),
(3,'人间失格','太宰治','作家出版社',150,17.30,1,1),
(4,'这就是二十四节气','高春香','海豚出版社',220,59.00,3,1),
(5,'白夜行','东野圭吾','南海出版公司',300,27.30,4,1),
(6,'摆渡人','克莱儿·麦克福尔','百花洲文艺出版社',225,22.80,1,1),
(7,'暖暖心绘本','米拦弗特毕','湖南少儿出版社',168,131.60,5,1),
(8,'天才在左疯子在右','高铭','北京联合出版公司',330,27.50,6,1),
(9,'我们仨','杨绛','生活.读书.新知三联书店',89,17.20,7,1),
(10,'活着','余华','作家出版社',100,13.00,1,1),
(11,'水浒传','施耐庵','三联出版社',300,50.00,1,1),
(12,'三国演义','罗贯中','三联出版社',300,50.00,2,1),
(13,'红楼梦','曹雪芹','三联出版社',300,50.00,5,1),
(14,'西游记','吴承恩','三联出版社',300,60.00,3,1);

```
创建数据库表2：
```
create database mydb1;

use mydb1;

DROP TABLE IF EXISTS `book`;
SET character_set_client = utf8mb4 ;
CREATE TABLE `book` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `author` varchar(20) DEFAULT NULL,
  `publish` varchar(20) DEFAULT NULL,
  `pages` int(10) DEFAULT NULL,
  `price` float(10,2) DEFAULT NULL,
  `bookcaseid` int(10) DEFAULT NULL,
  `abled` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

INSERT INTO `book` VALUES
(1,'解忧杂货店11','东野圭吾','南海出版公司',102,27.30,2,0),
(2,'追风筝的人11','卡勒德·胡赛尼','上海人民出版社',230,33.50,3,1),
(3,'人间失格111','太宰治','作家出版社',150,17.30,1,1),
(4,'这就是二十四节气111','高春香','海豚出版社',220,59.00,3,1),
(5,'白夜行111','东野圭吾','南海出版公司',300,27.30,4,1),
(6,'摆渡人111','克莱儿·麦克福尔','百花洲文艺出版社',225,22.80,1,1),
(7,'暖暖心绘本111','米拦弗特毕','湖南少儿出版社',168,131.60,5,1),
(8,'天才在左疯子在右111','高铭','北京联合出版公司',330,27.50,6,1),
(9,'我们仨111','杨绛','生活.读书.新知三联书店',89,17.20,7,1),
(10,'活着111','余华','作家出版社',100,13.00,1,1),
(11,'水浒传111','施耐庵','三联出版社',300,50.00,1,1),
(12,'三国演义111','罗贯中','三联出版社',300,50.00,2,1),
(13,'红楼梦111','曹雪芹','三联出版社',300,50.00,5,1),
(14,'西游记1111','吴承恩','三联出版社',300,60.00,3,1);
```

# 配置数据库
``` 
#########################
### dababase1
#########################
#spring.datasource.test1.driverClassName = com.mysql.cj.jdbc.Driver
spring.datasource.test1.driver-class-name=com.mysql.cj.jdbc.Driver
#spring.datasource.test1.url = jdbc:mysql://127.0.0.1:3306/mydb?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT
spring.datasource.test1.jdbc-url=jdbc:mysql://127.0.0.1:3306/mydb?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT
spring.datasource.test1.username=root
spring.datasource.test1.password=admin
#########################
### dababase2
#########################
#spring.datasource.test2.driverClassName = com.mysql.cj.jdbc.Driver
spring.datasource.test2.driver-class-name=com.mysql.cj.jdbc.Driver
#spring.datasource.test2.url = jdbc:mysql://127.0.0.1:3306/mydb1?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT
spring.datasource.test2.jdbc-url=jdbc:mysql://127.0.0.1:3306/mydb1?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT
spring.datasource.test2.username=root
spring.datasource.test2.password=admin
#########################
### mybatis
#########################
mybatis.mapper-locations=classpath:mapper/*.xml

```
# 建立entity实体类
``` 
@Data
@ToString
public class Book {
    private int id;
    private String name;
    private String author;
    private String publish;
    private int pages;
    private double price;
}
```
# 创建mapper类
``` 
@Mapper
public interface Book1Mapper {
    List<Book> getAll();
}
```
``` 
@Mapper
public interface Book2Mapper {
    List<Book> getAll();
}
```
特别注意：mapper类要分开包。


# 创建mapperxml

Book1Mapper.xml
``` 
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.example.demo.mapper.test1.Book1Mapper">

    <select id="getAll" resultType="com.example.demo.entity.Book">
		select * from book
	</select>
</mapper>
```
Book2Mapper.xml:
``` 
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.example.demo.mapper.test2.Book2Mapper">

    <select id="getAll" resultType="com.example.demo.entity.Book">
		select * from book
	</select>
</mapper>
```
# 创建数据库配置类

``` 
@Configuration
//修改为数据库1的mapper包
@MapperScan(basePackages = "com.example.demo.mapper.test1", sqlSessionTemplateRef = "test1SqlSessionTemplate")
public class DataSource1Config {

    /*创建数据源*/
    @Bean(name = "test1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test1")//修改为application.properties中db的前缀
    @Primary
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    /*创建工厂*/
    @Bean(name = "test1SqlSessionFactory")
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("test1DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //需要添加mapper.xml
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/Book1Mapper.xml"));
        return bean.getObject();
    }

    /*创建事务*/
    @Bean(name = "test1TransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("test1DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /*创建模板*/
    @Bean(name = "test1SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("test1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
```
``` 
@Configuration
//修改为数据库2的mapper包
@MapperScan(basePackages = "com.example.demo.mapper.test2", sqlSessionTemplateRef = "test2SqlSessionTemplate")
public class DataSource2Config {

    @Bean(name = "test2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test2")//修改为application.properties中db的前缀
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "test2SqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("test2DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //需要添加mapper.xml
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/Book2Mapper.xml"));
        return bean.getObject();
    }

    @Bean(name = "test2TransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("test2DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "test2SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("test2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
```
特别注意: 需要添加mapper.xml


# 测试

``` 
@Autowired
Book1Mapper book1Mapper;

@Test
public void test1() {
    List<Book> all = book1Mapper.getAll();
    for (int i = 0; i < all.size(); i++) {
        System.out.println(i + "======" + all.get(i).toString());
    }
}

@Autowired
Book2Mapper book2Mapper;

@Test
public void test2() {
    List<Book> all = book2Mapper.getAll();
    for (int i = 0; i < all.size(); i++) {
        System.out.println(i + "======" + all.get(i).toString());
    }
}
``` 

# 出错一：

``` 

org.apache.ibatis.binding.BindingException: Invalid bound statement (not found): 
com.example.demo.mapper.test1.Book1Mapper.getAll
```
注意：

1.检查mapper类不同数据库是否分包

2.数据库配置类DataSourceConfig是否配置mapper.xml,如下:
``` 
@Bean(name = "test2SqlSessionFactory")
public SqlSessionFactory testSqlSessionFactory(@Qualifier("test2DataSource") DataSource dataSource) throws Exception {
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    bean.setDataSource(dataSource);
    //需要添加mapper.xml
    bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/Book2Mapper.xml"));
    return bean.getObject();
}
```

