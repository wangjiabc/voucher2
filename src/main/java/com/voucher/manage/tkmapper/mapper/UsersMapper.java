package com.voucher.manage.tkmapper.mapper;

import com.voucher.manage.tkmapper.entity.Usersinfo;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component(value = "userMapper")
public interface UsersMapper extends Mapper<Usersinfo> {
}
