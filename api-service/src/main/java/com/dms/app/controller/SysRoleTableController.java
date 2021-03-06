package com.dms.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dms.app.dao.SysRoleTableDao;
import com.dms.app.entity.SysRoleTable;
import com.dms.app.service.SysRoleBackendApiTableService;
import com.dms.app.service.SysRoleFrontendMenuTableService;
import com.dms.app.service.SysRoleTableService;
import com.dms.app.service.SysRoleUserTableService;
import com.dms.app.vo.SysRoleAndPermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (SysRoleTable)表控制层
 * 该类由EasyCode工具生成
 * @author
 * @since 2020-03-07 14:31:50
 */
@RestController
@RequestMapping("sysRoleTable")
public class SysRoleTableController extends ApiController {


    @Autowired
    SysRoleTableDao sysRoleDao;

    /**
     * 角色
     */
    @Resource
    private SysRoleTableService sysRoleTableService;

    /**
     * 角色-用户
     */
    @Autowired
    SysRoleUserTableService sysRoleUserTableService;

    /**
     * 角色-前端菜单
     */
    @Autowired
    SysRoleFrontendMenuTableService sysRoleFrontendMenuTableService;

    /**
     * 角色-API
     */
    @Autowired
    SysRoleBackendApiTableService sysRoleBackendApiTableService;

    

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param sysRoleTable 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<SysRoleTable> page, SysRoleTable sysRoleTable) {
        return success(this.sysRoleDao.getRoles());
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.sysRoleTableService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysRoleTable 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody SysRoleTable sysRoleTable) {
        return success(this.sysRoleTableService.save(sysRoleTable));
    }

    /**
     * 修改数据
     *
     * @param sysRoleTable 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody SysRoleTable sysRoleTable) {
        return success(this.sysRoleTableService.updateById(sysRoleTable));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<String> idList) {
        return success(this.sysRoleTableService.removeByIds(idList));
    }

    //-----------------------------------------------------------------------
    /**
     * 对应前端：系统管理->用户角色设置->右边用户查询
     * @param roleId
     * @return
     */
    @GetMapping("/roleUser/{roleId}")
    public R getRoleAndUserList(@PathVariable("roleId") String roleId) {
        return success(sysRoleTableService.getRoleAndUserList(roleId));
    }

    @PostMapping("/roleUser/set")
    public R saveRoleUser(@RequestParam("roleId") String roleId,
                          @RequestBody SysRoleAndPermissionVo[] sysRoleAndPermissionVos){

        System.out.println("--------------现在进入方法saveRoleUser--------------");
        return success(sysRoleUserTableService.saveRoleUser(roleId, sysRoleAndPermissionVos));
    }

    //-----------------------------------------------------------------------
    /**
     * 对应前端：系统管理->菜单角色设置->右边菜单查询
     * @param roleId
     * @return
     */
    @GetMapping("/roleMenu/{roleId}")
    public R getRoleAndMenuList(@PathVariable("roleId") String roleId) {
        return success(sysRoleTableService.getRoleAndMenuList(roleId));
    }


    @PostMapping("/roleMenu/set")
    public R saveRoleMenu(@RequestParam("roleId") String roleId,
                                @RequestBody SysRoleAndPermissionVo[] sysRoleAndPermissionVos){

        System.out.println("--------------现在进入方法saveRoleMenu--------------");
        return success(sysRoleFrontendMenuTableService.saveRoleMenu(roleId,sysRoleAndPermissionVos));
    }

    //-----------------------------------------------------------------------
    /**
     * 对应前端：系统管理->菜单角色设置->右边api查询
     * @param roleId
     * @return
     */
    @GetMapping("/roleApi/{roleId}")
    public R getRoleAndApiList(@PathVariable("roleId") String roleId) {
        return success(sysRoleTableService.getRoleAndApiList(roleId));
    }


    @PostMapping("/roleApi/set")
    public R saveRoleApi(@RequestParam("roleId") String roleId,
                          @RequestBody SysRoleAndPermissionVo[] sysRoleAndPermissionVos){

        System.out.println("--------------现在进入方法saveRoleApi--------------");
        return success(sysRoleBackendApiTableService.saveRoleAip(roleId,sysRoleAndPermissionVos));
    }



}