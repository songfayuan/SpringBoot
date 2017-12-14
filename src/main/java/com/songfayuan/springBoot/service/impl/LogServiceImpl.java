/**
 * 项目名称：spring-boot
 * 项目包名：com.songfayuan.springBoot.service.impl
 * 创建时间：2017年12月14日下午2:50:02
 * 创建者：Administrator-宋发元
 * 创建地点：杭州
 */
package com.songfayuan.springBoot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.songfayuan.springBoot.dao.LogDao;
import com.songfayuan.springBoot.entity.LogEntity;
import com.songfayuan.springBoot.service.LogService;
import com.songfayuan.springBoot.utils.Page;
import com.songfayuan.springBoot.utils.Response;

/**
 * 描述：
 * @author songfayuan
 * 2017年12月14日下午2:50:02
 */
@Service
public class LogServiceImpl implements LogService {
	
	@Autowired
	private LogDao logDao;

	@Override
	public void save(LogEntity log) {
		this.logDao.save(log);
	}

	@Override
	public Response findLogListByPage(Integer page, Integer pageSize) {
		Integer offset = page > 0 ? page * pageSize : 0;
		List<LogEntity> list = this.logDao.findLogListByPage(offset, pageSize);
		Integer rows = this.logDao.findRows();
		Page<LogEntity> pagelist = new Page<>(page, pageSize, rows);
		pagelist.setData(list);
		return Response.success(pagelist);
	}

	@Override
	public LogEntity findLogById(Integer id) {
		return this.logDao.findLogById(id);
	}

}
