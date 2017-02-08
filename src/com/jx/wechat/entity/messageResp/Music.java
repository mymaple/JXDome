package com.jx.wechat.entity.messageResp;

import com.jx.common.util.MapleStringUtil;

public class Music {
	
	/** 音乐标题	*/
    private String title;
    
    /** 音乐描述	*/
    private String description;
    
    /** 音乐链接	*/
    private String musicUrl;
    
    /** 高质量音乐链接，WIFI环境优先使用该链接播放音乐		*/
    private String hQMusicUrl;
    
    /** 缩略图的媒体id，通过上传多媒体文件得到的id	*/
    private String thumbMediaId;

	/**
	 * 获得	音乐标题
	 *	
	 * @return String title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * 设置	音乐标题
	 *
	 * @param String title
	 */
	public void setTitle(String title) {
		this.title = MapleStringUtil.trim(title);
	}

	/**
	 * 获得	音乐描述
	 *	
	 * @return String description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * 设置	音乐描述
	 *
	 * @param String description
	 */
	public void setDescription(String description) {
		this.description = MapleStringUtil.trim(description);
	}

	/**
	 * 获得	音乐链接
	 *	
	 * @return String musicUrl
	 */
	public String getMusicUrl() {
		return this.musicUrl;
	}

	/**
	 * 设置	音乐链接
	 *
	 * @param String musicUrl
	 */
	public void setMusicUrl(String musicUrl) {
		this.musicUrl = MapleStringUtil.trim(musicUrl);
	}

	/**
	 * 获得	高质量音乐链接，WIFI环境优先使用该链接播放音乐	
	 *	
	 * @return String hQMusicUrl
	 */
	public String gethQMusicUrl() {
		return this.hQMusicUrl;
	}

	/**
	 * 设置	高质量音乐链接，WIFI环境优先使用该链接播放音乐	
	 *
	 * @param String hQMusicUrl
	 */
	public void sethQMusicUrl(String hQMusicUrl) {
		this.hQMusicUrl = MapleStringUtil.trim(hQMusicUrl);
	}

	/**
	 * 获得	缩略图的媒体id，通过上传多媒体文件得到的id
	 *	
	 * @return String thumbMediaId
	 */
	public String getThumbMediaId() {
		return this.thumbMediaId;
	}

	/**
	 * 设置	缩略图的媒体id，通过上传多媒体文件得到的id
	 *
	 * @param String thumbMediaId
	 */
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = MapleStringUtil.trim(thumbMediaId);
	}
    
}
