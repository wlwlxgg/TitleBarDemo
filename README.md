## Demo说明
### HorizonScrollView+RadioGroup
#### 实现的功能
* 可水平滚动
* 动态添加和删除标题
* 点击切换不同栏目
* 指示下划线，指示当前栏目

#### 遇到的问题
* xml中定义RadioGroup显示不全，解决：在Java代码中new了RadioGruop
* 没有排序
* 修改标题后默认回到第一个

#### 下个版本
* 将固定顺序
* 从哪里进入修改页面回来也是哪
* 进阶版将实现手动拖拽

### ViewPager+Fragment
#### 实现功能
* 可滑动切换栏目（在栏目切换事件监听中执行radioButton.perfmClick()使标题随之改变，在标题的onCheckedChange方法设置viewPager.setCurrentItem()使当前fragment切换到相应标题对应的。

#### 遇到的问题
* 当修改栏目后，回到主界面发现即使fragmentList改变了可是界面没有变化。解决：在FragmentPagerAdapter中重新执行fragment的捆绑。

#### 下个版本
* 实现一个fragment显示所有界面。
## 截图
![img](http://ohjmj8cyg.bkt.clouddn.com/2017010501.png)
<!--more-->
![img](http://ohjmj8cyg.bkt.clouddn.com/2017010502.png)
## 更多详情请去我的主页www.wlwlxgg.com
