# PixelTool

图片像素化，调色工具。(image to sprite, less colors etc.)

图示：

![image](https://github.com/neverever1533/PixelTool/blob/main/pt_p.png)

![image](https://github.com/neverever1533/PixelTool/blob/main/pt_d.png)


## 说明（about）：

当前实现：
* 图片像素化
* 调整2-256色数


### 项目进度：
- [x] to pixel
- [x] update colors
    * depth
    * color size


## 用法（Usage）：
* ### PixelTool:

* #### toPixel():

```java
toPixel(File inFile, File outFile, int pixelSize){}
```

##### or:

```java
toPixel(File inFile, String formatName, File outFile, int pixelSize){}
```

将读取的图片文件（inFile）,像素化（pixelSize设为2以上生效，若结果不理想可缓增。），然后保存为图片（outFile）。（默认导出png，图片格式formatName可选"jpg"，"png"等。）


* #### updateColors():

```java
updateColors(File inFile, File outFile, int colorSize){}
```

##### or:

```java
updateColors(File inFile, String formatName, File outFile, int colorSize){}
```

读取图片文件（inFile），color size为1-256的整数（自适应区间），降低色数（将红，绿和蓝各色限制为2，4，8，16，32，64，128和256色其中之一），然后保存为图片（outFile）。（32色基本已达到256色效果。）


* #### getImage():

```java
getImage(File inFile, String formatName, File outFile, int depth){}
```

##### or:

```java
getImage(File inFile, String formatName, File outFile, int depth){}
```

效果如上updateColors（），depth为1-8的整数，降低色数（将红，绿和蓝各色限制为2¹-2⁸色，depth为8则对应着256色）。


---

### 使用程序：

#### 像素化：

```java
String inFilePath = "in.png";
String outFilePath = "out.png";
int pixelSize=4;

File inFile = new File(inFilePath);
File outFile = new File(outFilePath);

PixelTool pixelTool = new PixelTool();
pixelTool.toPixel(inFile, outFile, pixelSize);
```

#### 调色：

```java
int colorSize=32;

pixelTool.updateColors(inFile, outFile, colorSize);
```

or:

```java
String outFilePathJpg = "out.jpg";
String formatName = "jpg";
int depth = 5;

File outFileJpg = new File(outFilePathJpg);

pixelTool.getImage(inFile, formatName, outFileJpg, depth);
```

调色有两种方法：updateColors(inFile, outFile, colorSize)，getImage(inFile, outFile, depth)，最终效果相同可任选其一使用。


---

## 声明：

示例图来源：
https://www.pexels.com/zh-cn/photo/29264403/

作者：
Sahil Captures

许可证：
[Free](https://www.pexels.com/zh-cn/license/)


## 许可（License）：

---

License :
 [Apache License (Version 2.0)](http://www.apache.org/licenses/)

---
