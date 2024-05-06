package com.wx_back.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.wx_back.Mapper.createWorkMapper;
import com.wx_back.Mapper.showListMapper;
import com.wx_back.Mapper.myAttentionMapper;
import com.wx_back.common.AddShowListDto;
import com.wx_back.common.R;
import com.wx_back.common.focusDto;
import com.wx_back.pojo.createWork;
import com.wx_back.pojo.myAttention;
import com.wx_back.pojo.showList;
import com.wx_back.pojo.workImg;
import com.wx_back.server.impl.createWorkServiceImpl;
import com.wx_back.server.impl.myAttentionServiceImpl;
import com.wx_back.server.impl.showListServiceImpl;
import com.wx_back.server.impl.workImgServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/show")
@Slf4j
public class showListController {

    @Autowired
    private showListServiceImpl showListService;
    @Autowired
    private showListMapper showListMapper;
    @Autowired
    private createWorkServiceImpl createWorkService;
    @Autowired
    private createWorkMapper createWorkMapper;
    @Autowired
    private workImgServiceImpl workImgService;

    @Autowired
    private myAttentionServiceImpl myAttentionService;

    @Autowired
    private myAttentionMapper myAttentionMapper;
    /**
     *  添加入 评论区 (展区) 的 模块
     *
     * */

    @RequestMapping("/add")
    public R<String>  addToList(AddShowListDto addShowListDto){
        showList showList = new showList();
        showList.setShowTime(addShowListDto.getDate());
        showList.setPhone(addShowListDto.getPhone());
        showList.setTitle(addShowListDto.getTitle());
        showList.setSession_key(addShowListDto.getSession_key());
        //  查找 对应 的 作品 值workId
        LambdaQueryWrapper<createWork> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 添加查询条件 根据 phone 进行查询
        dishLambdaQueryWrapper.eq(createWork::getUserPhone, addShowListDto.getPhone())
                .eq(createWork::getWorkId,addShowListDto.getWorkId()).eq(createWork::getTitle,addShowListDto.getTitle());
        createWork  createWork= createWorkService.getOne(dishLambdaQueryWrapper);

        LambdaQueryWrapper<workImg> dishLambdaQueryWrapper1 = new LambdaQueryWrapper<>();
        // 添加查询条件 根据 phone  进行查询 而后 更改
        // 可以 只修改 特定 的 值 使得 主键id 不受到影响
        dishLambdaQueryWrapper1.eq(workImg::getPhone, addShowListDto.getPhone())
               .eq(workImg::getName,addShowListDto.getTitle());
        workImg  workImg = workImgService.getOne(dishLambdaQueryWrapper1);

        if(createWork!= null &&  workImg != null){
            showList.setTexts(createWork.getTexts());
            showList.setImagUrl(workImg.getFilePath());
        }else{
            log.info("无此信息");
            return R.error("失败 查无此信息");
        }


        LambdaUpdateWrapper<createWork> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(com.wx_back.pojo.createWork::getWorkId,createWork.getWorkId()).set(com.wx_back.pojo.createWork::getStatus,1);
        boolean res = createWorkService.update(createWork,lambdaUpdateWrapper);
        if( res ){
            log.info("是否在展示列表的状态 修改成功");
        }else{
            log.info("是否在展示列表的状态 修改失败");
        }

        showListService.save(showList);

        return R.success("添加成功");
    }
    @RequestMapping("/delete")
    public R<String> deleteById(String workId){

        LambdaQueryWrapper<createWork> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(createWork::getWorkId,workId);
        createWork one = createWorkService.getOne(dishLambdaQueryWrapper);

        LambdaQueryWrapper<showList> dishLambdaQueryWrapper1 = new LambdaQueryWrapper<>();
        // 添加查询条件 根据 phone 进行查询
        dishLambdaQueryWrapper1.eq(showList::getPhone, one.getUserPhone())
                .eq(showList::getTitle,one.getTitle());
        showList showList = showListMapper.selectOne(dishLambdaQueryWrapper1);

        if(showListMapper.deleteById(showList.getSid())>0){
            // mybatisPlus 默认 Sid 作为唯一id

            LambdaUpdateWrapper<createWork> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper.eq(com.wx_back.pojo.createWork::getWorkId,one.getWorkId()).set(com.wx_back.pojo.createWork::getStatus,0); // 修改状态

            if(createWorkService.update(one,lambdaUpdateWrapper)) return R.success("修改成功");

        }
        return R.success("修改失败");
    }

    /**
     *  通过 手机号 获取 用户自己的全部  作品信息
     * @Param phone 手机号
     * */
    @RequestMapping("/All")
    public R<List<showList>> ShowList(String id,String phone){
        LambdaQueryWrapper<showList> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();

        List<showList> showListList = showListMapper.selectList(dishLambdaQueryWrapper);

        return R.success(showListList);
    }

    /**
     *  通过 手机号 获取 用户自己的全部  作品信息
     * @Param phone 手机号
     * */
    @RequestMapping("/AddPerson")
    public R<List<showList>> addPerson(String phone,String session_key){
        //
        LambdaQueryWrapper<showList> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 添加查询条件 根据 phone 进行查询
        dishLambdaQueryWrapper.eq(showList::getPhone, phone);
        List<showList>  showListList = showListMapper.selectList(dishLambdaQueryWrapper);

        return R.success(showListList);
    }

    /**
     *
     *  关注数目增加
     *
     **/
    @RequestMapping("/focus")
    public R<String> addFocus(focusDto focusDto){
        myAttention myAttention = new myAttention();
        myAttention.setAttenPhone(focusDto.getAttenPhone());
        myAttention.setPhone(focusDto.getPhone());
        myAttentionService.save(myAttention);

        showList showList = showListMapper.selectById(focusDto.getWid());
        LambdaUpdateWrapper<showList> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();

        lambdaUpdateWrapper.eq(com.wx_back.pojo.showList::getId,focusDto.getWid()).set(com.wx_back.pojo.showList::getAttentionNum,showList.getAttentionNum()+1);
        if(showListService.update(showList,lambdaUpdateWrapper)) return R.success("关注成功");

        return R.success("关注失败");
    }

    /**
     * 我的关注
     * @Param phone 个人手机号
     * */

    @RequestMapping("/myFocus")
    public R<List<myAttention>> showFocus(String phone){
        List<myAttention> myAttentions = myAttentionMapper.getAll(phone);

        return R.success(myAttentions);
    }

}
