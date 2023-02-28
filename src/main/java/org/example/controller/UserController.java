package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.example.common.R;
import org.example.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import org.example.entity.User;
import org.example.dto.UserDTO;

import org.example.service.IUserService;

/**
 * 用户表 控制器
 *
 * @author AI
 * @since 2020-06-19
 */
@RestController
@AllArgsConstructor
@RequestMapping("user")
@Api(description = "用户表相关接口")
public class UserController {

	private IUserService userService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入user")
	public R<User> detail(UserDTO dto) {
		User detail = userService.getOne(dto);
		return R.data(detail);
	}

	/**
	 * 分页 用户表
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入user")
	public R<IPage<User>> page(UserDTO dto) {
		IPage<User> pages = userService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 用户表
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入user")
	public R<List<User>> list(UserDTO dto) {
		List<User> list = userService.list(dto);
		return R.data(list);
	}

	/**
	 * 新增 用户表
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入user")
	public R save(@RequestBody UserDTO dto) {
		return R.data(userService.save(dto));
	}

	/**
	 * 修改 用户表
	 */
	@PutMapping("/update")
	@ApiOperation(value = "修改", notes = "传入user")
	public R update(@RequestBody UserDTO dto) {
		return R.data(userService.updateById(dto));
	}

	/**
	 * 删除 用户表
	 */
	@DeleteMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.data(userService.deleteLogic(Func.toIntList(ids)));
	}

	/**
	 * 详情
	 */
	@GetMapping("/hello")
	public String hello(UserDTO dto) {
		return "hello";
	}


}
