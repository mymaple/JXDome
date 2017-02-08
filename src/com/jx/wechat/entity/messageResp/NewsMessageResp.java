package com.jx.wechat.entity.messageResp;

import java.util.List;

import com.jx.wechat.entity.BaseMessageResp;

/**
 * 类名: NewsMessageResp </br>
 * 描述: 回复消息之图文消息 </br>
 */
public class NewsMessageResp extends BaseMessageResp {

	/** 图文消息个数，限制为10条以内	*/
    private int articleCount;
    
    /** 多条图文消息信息，默认第一个item为大图	*/
    private List<News> articles;

	/**
	 * 获得	图文消息个数，限制为10条以内
	 *	
	 * @return int articleCount
	 */
	public int getArticleCount() {
		return this.articleCount;
	}

	/**
	 * 设置	图文消息个数，限制为10条以内
	 *
	 * @param int articleCount
	 */
	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}

	/**
	 * 获得	多条图文消息信息，默认第一个item为大图
	 *	
	 * @return List<News> articles
	 */
	public List<News> getArticles() {
		return this.articles;
	}

	/**
	 * 设置	多条图文消息信息，默认第一个item为大图
	 *
	 * @param List<News> articles
	 */
	public void setArticles(List<News> articles) {
		this.articles = articles;
	}
    
}

