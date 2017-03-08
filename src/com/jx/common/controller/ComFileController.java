package com.jx.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jx.common.config.BaseController;
import com.jx.common.config.Const;
import com.jx.common.config.ResultInfo;
import com.jx.common.util.AppUtil;
import com.jx.common.util.PathUtil;
import com.jx.common.util.UuidUtil;
import com.jx.common.util.MapleFileUtil;

/**
 * 文件上传下载
 */
@Controller
@RequestMapping(value = "/common/file")
public class ComFileController extends BaseController {

	/**
	 * 图片上传
	 */
	@RequestMapping(value="/upimg")
	@ResponseBody
	public Object upimg(@RequestParam(required=false) MultipartFile img) throws Exception{
		ResultInfo resultInfo = this.getResultInfo();
		if(img!=null){ 
			String imgPath = PathUtil.getProjectPath()+Const.PATH_FILEUPCACHE+"/";
            String imgName = UuidUtil.get32UUID();
            System.out.println(img.getOriginalFilename());
            imgName = MapleFileUtil.fileUp(img, imgPath, imgName);
            if(!"下载.jpg".equals( img.getName())){
            	resultInfo.setResultCode("success");
            }
            resultInfo.setResultContent(Const.PATH_FILEUPCACHE+"/"+imgName);
        }  
		return AppUtil.returnResult(this.getPageData(), resultInfo);
	}

}
