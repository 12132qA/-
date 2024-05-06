package com.wx_back;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.wx_back.Mapper.userMapper;
import com.wx_back.controller.utils.HttpGet;
import com.wx_back.pojo.LoginRequest;
import com.wx_back.pojo.query.userquery;
import com.wx_back.pojo.user;
import org.apache.commons.httpclient.HttpClient;
import org.junit.jupiter.api.Test; // 导的包的差别
//import org.junit.Test //
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.out;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest // 测试时目录必须相同
public class wx_backApplicationTests {
    @Autowired
    private userMapper userMapper;
    @Autowired
    private HttpGet httpGet;

    @Test
    public void testGG(){

        httpGet.getWeChat(new LoginRequest());

        System.out.println();

    }

   @Test
  public void testGet(){
       out.println(userMapper);
       //方式一   按条件查询
       QueryWrapper<user> wrapper = new QueryWrapper<user>(); // 指定泛型
       wrapper.lt("id",1000000); // 小于
       List<user> userList = userMapper.selectList(wrapper);
       out.println(userList);
       out.println("=======================================================================");

       // 方式二 lambda格式的按条件查询
       wrapper.lambda().lt(user ::getId,200);
       List<user> userLambda = userMapper.selectList(wrapper);
       out.println(userLambda);
       out.println("=======================================================================");
       // 方式三 lambda格式专用类简化的按条件查询
       LambdaQueryWrapper<user> lqw =  new LambdaQueryWrapper<>();
       // and 关系
       // lqw.lt(user ::getId,200).gt(user ::getId,100); // 小于200  大于 100
       // or 关系
       lqw.gt(user ::getId,200).or().lt(user::getId,100);
       List<user> users = userMapper.selectList(lqw);
       out.println(users);
       out.println("=======================================================================");

      // 模拟页面传递过来的查询参数
       userquery uq = new userquery();
       uq.setUpId(150L);
       uq.setId(50L);

       // 非null判断
       // 方式 一
//       lqw.lt(user::getId,uq.getUpId());
//      if(uq.getId()!=null) lqw.gt(user::getId,uq.getId());

       // 方式 二
       lqw.lt(uq.getUpId()!=null,user::getId,uq.getUpId())
          .gt(uq.getId()!=null,user::getId,uq.getId());

       List<user> users1 = userMapper.selectList(lqw);
       out.println(users1);

       out.println("==================================查询投影 =======================================");
       // 查询投影
       LambdaQueryWrapper<user> lqw1 = new LambdaQueryWrapper<>();
       lqw1.select(user::getId,user::getPassword);
       // 或 当不是 lambda 表达式时
       QueryWrapper<user> wrq = new QueryWrapper<>();
       wrq.select("id","password");
       List<user> users2 = userMapper.selectList(lqw1);

       wrq.select("count(*) as count, id");
       wrq.groupBy("id"); // 分组统计
       List<Map<String, Object>> maps = userMapper.selectMaps(wrq);

       out.println("=========================================maps===================================================");
       out.println(maps);
       out.println("==============================================================================================");
       out.println(users2);
   }


   @Test // 分页查询
   public void testGetByPage(){
       IPage<user> page  = new Page<user>(2,2); // 当前页

       userMapper.selectPage(page,null);

       out.println("当前页码值 : 每页显示数 : 总页数 : 总数据数 : 数据");
       out.print(page.getCurrent()+".."+page.getSize()+".."+page.getPages()+".."+page.getTotal()+".."+page.getRecords());
   }


   // 查询语句
    @Test
   public void teSelect() {
       out.println(userMapper);
       //方式一   按条件查询
       QueryWrapper<user> wrapper = new QueryWrapper<user>(); // 指定泛型
       wrapper.lt("id", 1000000); // 小于
       List<user> userList = userMapper.selectList(wrapper);
       out.println(userList);
       wrapper.like("name","h");
      wrapper.between("id",10,30);
       out.println( userMapper.selectList(wrapper));
       out.println("=======================================================================");
       // 等同于 =
       LambdaQueryWrapper<user> lqws = new LambdaQueryWrapper<>();
       lqws.eq(user::getName, "hu"); // 相等
       user loginUser = userMapper.selectOne(lqws);
       // 范围查询 lt le gt  eq between
       lqws.between(user::getId,10,30); // 范围查询
       out.println(userMapper.selectOne(lqws));
       lqws.like(user::getName,"hu");
       // like left right 左边是%  或  右边是%
       out.println(userMapper.selectList(lqws));

   }
   // 多条数据
    @Test
    public void batchs_Use(){
       List<Long> ids = new ArrayList<Long>();
       ids.add(1111L);
       ids.add(231L);

        List<user> users = userMapper.selectBatchIds(ids);
        out.println(users);
    }
    // 逻辑删除  将deleted 标记值改成 1
    @Test
    public void delete_Use(){
        int res = userMapper.deleteById(231L);
        if(res==1)  out.println("id = 1111 字段 删除成功");
        out.println("============================删除后 查询==========================");
        List<user> users = userMapper.selectList(null);
        out.println(users);
    }
   @Test
    void test_OptimisticLock_Update(){
       //  乐观锁的实现
        user user = userMapper.selectById(1111L);
        user user1 = userMapper.selectById(1111L);
        user.setName("皇帝");
        userMapper.updateById(user);
        user1.setName("青帝");
        userMapper.updateById(user1);
    }
    @Test  // mp 代码生成器
    void test_Generator(){
        AutoGenerator autoGenerator = new AutoGenerator();

        DataSourceConfig dataSourceConfig = new DataSourceConfig();

        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/user");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("600600");

        autoGenerator.setDataSource(dataSourceConfig);

        // 设置全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir")+"/D:/spring-0001/wx_back/src/main/java/com/wx_back/pojo/user.java");

        autoGenerator.execute();
        autoGenerator.setDataSource(dataSourceConfig);

        autoGenerator.execute();
    }


}
