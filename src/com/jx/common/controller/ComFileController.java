package com.jx.common.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jx.background.entity.BgConfig;
import com.jx.background.entity.BgMenu;
import com.jx.background.entity.BgRights;
import com.jx.background.entity.BgRole;
import com.jx.background.entity.BgUser;
import com.jx.background.service.BgConfigService;
import com.jx.background.service.BgMenuService;
import com.jx.background.service.BgRoleService;
import com.jx.background.service.BgUserService;
import com.jx.background.util.BgSessionUtil;
import com.jx.background.util.JudgeRightsUtil;
import com.jx.common.config.BaseController;
import com.jx.common.config.Const;
import com.jx.common.config.PageData;
import com.jx.common.config.ResultInfo;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.PathUtil;
import com.jx.common.util.UuidUtil;
import com.jx.common.util.DrawImageUtil;
import com.jx.common.util.MapleFileUtil;

/**
 * 文件上传下载
 */
@Controller
@RequestMapping(value = "/common/file")
public class ComFileController extends BaseController {

	/**
	 * 获取验证码
	 */
	@RequestMapping(value="/upimg")
	@ResponseBody
	public Object upimg(@RequestParam(required=false) MultipartFile file) throws Exception{
		ResultInfo resultInfo = this.getResultInfo();
		if(file!=null){ 
			String filePath = PathUtil.getProjectPath()+Const.PATH_FILEUPCACHE+"/";
            String fileName = UuidUtil.get32UUID();
            System.out.println(file.getOriginalFilename());
            MapleFileUtil.fileUp(file, filePath, fileName);
            resultInfo.setResultCode("success");
            resultInfo.setResultContent(Const.PATH_FILEUPCACHE+"/"+fileName);
        }  
		return AppUtil.returnResult(this.getPageData(), resultInfo);
	}
	
	/**
	 * 获取验证码
	 */
	@RequestMapping(value="/upimgs")
	@ResponseBody
	public Object upimgs(@RequestParam(required=false) MultipartFile[] files) throws Exception{
		ResultInfo resultInfo = this.getResultInfo();
		if(files!=null&&files.length>0){ 
			String filePath = Const.PATH_FILEUPCACHE;
			//循环获取file数组中得文件  
			for(int i = 0;i<files.length;i++){  
				MultipartFile file = files[i];
				BufferedImage sourceImg =ImageIO.read(file.getInputStream());
				String fileName = UuidUtil.get32UUID();
				System.out.println(sourceImg.getHeight()+"---------"+sourceImg.getWidth()+"------"+file.getOriginalFilename());
				MapleFileUtil.fileUp(file, filePath, fileName);
			}  
		}  
		return AppUtil.returnResult(this.getPageData(), resultInfo);
	}
	
	

}
