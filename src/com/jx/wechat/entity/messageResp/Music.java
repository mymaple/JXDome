package com.jx.wechat.entity.messageResp;

import com.jx.common.util.MapleStringUtil;

public class Music {
	
	/** 音乐标题	*/
    private String Title;
    
    /** 音乐描述	*/
    private String Description;
    
    /** 音乐链接	*/
    private String MusicUrl;
    
    /** 高质量音乐链接，WIFI环境优先使用该链接播放音乐		*/
    private String HQMusicUrl;
    
    /** 缩略图的媒体id，通过上传多媒体文件得到的id	*/
    private String ThumbMediaId;

	/**
	 * 获得	音乐标题
	 *	
	 * @return String title
	 */
	public String getTitle() {
		return this.Title;
	}

	/**
	 * 设置	音乐标题
	 *
	 * @param String title
	 */
	public void setTitle(String title) {
		this.Title = MapleStringUtil.trim(title);
	}

	/**
	 * 获得	音乐描述
	 *	
	 * @return String description
	 */
	public String getDescription() {
		return this.Description;
	}

	/**
	 * 设置	音乐描述
	 *
	 * @param String description
	 */
	public void setDescription(String description) {
		this.Description = MapleStringUtil.trim(description);
	}

	/**
	 * 获得	音乐链接
	 *	
	 * @return String musicUrl
	 */
	public String getMusicUrl() {
		return this.MusicUrl;
	}

	/**
	 * 设置	音乐链接
	 *
	 * @param String musicUrl
	 */
	public void setMusicUrl(String musicUrl) {
		this.MusicUrl = MapleStringUtil.trim(musicUrl);
	}

	/**
	 * 获得	高质量音乐链接，WIFI环境优先使用该链接播放音乐	
	 *	
	 * @return String hQMusicUrl
	 */
	public String getHQMusicUrl() {
		return this.HQMusicUrl;
	}

	/**
	 * 设置	高质量音乐链接，WIFI环境优先使用该链接播放音乐	
	 *
	 * @param String hQMusicUrl
	 */
	public void setHQMusicUrl(String hQMusicUrl) {
		this.HQMusicUrl = MapleStringUtil.trim(hQMusicUrl);
	}

	/**
	 * 获得	缩略图的媒体id，通过上传多媒体文件得到的id
	 *	
	 * @return String thumbMediaId
	 */
	public String getThumbMediaId() {
		return this.ThumbMediaId;
	}

	/**
	 * 设置	缩略图的媒体id，通过上传多媒体文件得到的id
	 *
	 * @param String thumbMediaId
	 */
	public void setThumbMediaId(String thumbMediaId) {
		this.ThumbMediaId = MapleStringUtil.trim(thumbMediaId);
	}
    
}
