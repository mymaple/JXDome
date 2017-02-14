package com.jx.wechat.entity;

import com.jx.common.util.MapleStringUtil;

public class UserInfo {
	
		/**	用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。	*/
		private String subscribe;
		
		/**	用户的标识，对当前公众号唯一	*/
		private String openid;
		
		/**	用户的昵称		*/
		private String nickname;
		
		/**	用户的性别，值为1时是男性，值为2时是女性，值为0时是未知	*/
		private String sex;
		
		/**	用户所在城市	*/
		private String city;
		
		/**	用户所在国家	*/
		private String country;
		
		/**	用户所在省份	*/
		private String province;
		
		/**	用户的语言，简体中文为zh_CN	*/
		private String language;
		
		/**	用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），
		 * 用户没有头像时该项为空。若用户更换头像，原有头像URL将失效
		 */
		private String headimgurl;
		
		/**	用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间	*/
		private String subscribe_time;
		
		/**	公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注	*/
		private String remark;
		
		/**	用户所在的分组ID	*/
		private String groupid;

		/**
		 * 获得	用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
		 *	
		 * @return String subscribe
		 */
		public String getSubscribe() {
			return this.subscribe;
		}

		/**
		 * 设置	用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
		 *
		 * @param String subscribe
		 */
		public void setSubscribe(String subscribe) {
			this.subscribe = MapleStringUtil.trim(subscribe);
		}

		/**
		 * 获得	用户的标识，对当前公众号唯一
		 *	
		 * @return String openid
		 */
		public String getOpenid() {
			return this.openid;
		}

		/**
		 * 设置	用户的标识，对当前公众号唯一
		 *
		 * @param String openid
		 */
		public void setOpenid(String openid) {
			this.openid = MapleStringUtil.trim(openid);
		}

		/**
		 * 获得	用户的昵称
		 *	
		 * @return String nickname
		 */
		public String getNickname() {
			return this.nickname;
		}

		/**
		 * 设置	用户的昵称
		 *
		 * @param String nickname
		 */
		public void setNickname(String nickname) {
			this.nickname = MapleStringUtil.trim(nickname);
		}

		/**
		 * 获得	用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
		 *	
		 * @return String sex
		 */
		public String getSex() {
			return this.sex;
		}

		/**
		 * 设置	用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
		 *
		 * @param String sex
		 */
		public void setSex(String sex) {
			this.sex = MapleStringUtil.trim(sex);
		}

		/**
		 * 获得	用户所在城市
		 *	
		 * @return String city
		 */
		public String getCity() {
			return this.city;
		}

		/**
		 * 设置	用户所在城市
		 *
		 * @param String city
		 */
		public void setCity(String city) {
			this.city = MapleStringUtil.trim(city);
		}

		/**
		 * 获得	用户所在国家
		 *	
		 * @return String country
		 */
		public String getCountry() {
			return this.country;
		}

		/**
		 * 设置	用户所在国家
		 *
		 * @param String country
		 */
		public void setCountry(String country) {
			this.country = MapleStringUtil.trim(country);
		}

		/**
		 * 获得	用户所在省份
		 *	
		 * @return String province
		 */
		public String getProvince() {
			return this.province;
		}

		/**
		 * 设置	用户所在省份
		 *
		 * @param String province
		 */
		public void setProvince(String province) {
			this.province = MapleStringUtil.trim(province);
		}

		/**
		 * 获得	用户的语言，简体中文为zh_CN
		 *	
		 * @return String language
		 */
		public String getLanguage() {
			return this.language;
		}

		/**
		 * 设置	用户的语言，简体中文为zh_CN
		 *
		 * @param String language
		 */
		public void setLanguage(String language) {
			this.language = MapleStringUtil.trim(language);
		}

		/**
		 * 获得	用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），
		 * 用户没有头像时该项为空。若用户更换头像，原有头像URL将失效
		 *	
		 * @return String headimgurl
		 */
		public String getHeadimgurl() {
			return this.headimgurl;
		}

		/**
		 * 设置	用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），
		 * 用户没有头像时该项为空。若用户更换头像，原有头像URL将失效
		 *
		 * @param String headimgurl
		 */
		public void setHeadimgurl(String headimgurl) {
			this.headimgurl = MapleStringUtil.trim(headimgurl);
		}

		/**
		 * 获得	用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
		 *	
		 * @return String subscribe_time
		 */
		public String getSubscribe_time() {
			return this.subscribe_time;
		}

		/**
		 * 设置	用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
		 *
		 * @param String subscribe_time
		 */
		public void setSubscribe_time(String subscribe_time) {
			this.subscribe_time = MapleStringUtil.trim(subscribe_time);
		}

		/**
		 * 获得	公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
		 *	
		 * @return String remark
		 */
		public String getRemark() {
			return this.remark;
		}

		/**
		 * 设置	公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
		 *
		 * @param String remark
		 */
		public void setRemark(String remark) {
			this.remark = MapleStringUtil.trim(remark);
		}

		/**
		 * 获得	用户所在的分组ID
		 *	
		 * @return String groupid
		 */
		public String getGroupid() {
			return this.groupid;
		}

		/**
		 * 设置	用户所在的分组ID
		 *
		 * @param String groupid
		 */
		public void setGroupid(String groupid) {
			this.groupid = MapleStringUtil.trim(groupid);
		}
		
		
		
}
