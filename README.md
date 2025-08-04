<p align="center">  
    <img src="https://tools.etdon.com/placeholder-image/generate?width=830&height=207&background-color=FAEB92,9929EA,CC66DA&text=jbinder&text-color=000000" width=830 height=207>    
</p>

<div align="center">

![Universal](https://img.shields.io/badge/Universal-white?style=for-the-badge&logo=github&label=Platform&color=%230173b3)
![Java](https://img.shields.io/badge/Java-white?style=for-the-badge&logo=github&label=Language&color=%23b07219)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-white?style=for-the-badge&logo=apache-maven&label=Building&color=%23C71A36)

</div>

## 🚀 Getting Started

> [!IMPORTANT]
> Requirements:
> - Java 22

🪶 Maven:
```xml
<repository>
    <id>etdon-repo</id>
    <url>https://repo.etdon.com/repository/maven-releases/</url>
</repository>
```

```xml
<dependency>
    <groupId>com.etdon</groupId>
    <artifactId>jbinder</artifactId>
    <version>1.0.0</version>
</dependency>
```

🐘 Gradle:
```groovy
maven {         
    url = uri("https://repo.etdon.com/repository/maven-releases/")
}
```

```groovy
dependencies {
    implementation 'com.etdon:jbinder:1.0.0'
}
```

## 📦 Building
The build management tool used for this project is [Apache Maven][build_tool]. Executing the following command will install the compiled artifact into your local repository if no critical issues occur during any of the lifecycle phases.
```
mvn clean install
```
[build_tool]: https://maven.apache.org/
