package com.hua.fileplat.cloud.user.service;

import com.hua.base.entity.Result;
import com.hua.fileplat.cloud.user.entity.User;
import com.hua.fileplat.cloud.user.vo.LoginUserVo;
import org.springframework.session.Session;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2019-03-10 15:47:21
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    /**
     * 登录
     * @param loginUserVo    session
     * @param bindingResult  验证结果
     * @return
     */
    Result login(LoginUserVo loginUserVo, BindingResult bindingResult);
}