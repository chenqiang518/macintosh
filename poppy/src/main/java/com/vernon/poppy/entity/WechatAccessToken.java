package com.vernon.poppy.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "wechat_access_token")
public class WechatAccessToken extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 企业ID
     */
    @Column(name = "corp_id")
    private String corpId;

    /**
     * 应用的凭证密钥
     */
    @Column(name = "corp_secret")
    private String corpSecret;

    /**
     * 删除标志 1 已删除 0 未删除
     */
    private Byte deleted;

    /**
     * 每个应用有独立的secret，获取到的access_token只能本应用使用，所以每个应用的access_token应该分开来获取
     */
    @Column(name = "access_token")
    private String accessToken;

    /**
     * 过期时间
     */
    @Column(name = "expires_in")
    private Date expiresIn;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取企业ID
     *
     * @return corp_id - 企业ID
     */
    public String getCorpId() {
        return corpId;
    }

    /**
     * 设置企业ID
     *
     * @param corpId 企业ID
     */
    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    /**
     * 获取应用的凭证密钥
     *
     * @return corp_secret - 应用的凭证密钥
     */
    public String getCorpSecret() {
        return corpSecret;
    }

    /**
     * 设置应用的凭证密钥
     *
     * @param corpSecret 应用的凭证密钥
     */
    public void setCorpSecret(String corpSecret) {
        this.corpSecret = corpSecret;
    }

    /**
     * 获取删除标志 1 已删除 0 未删除
     *
     * @return deleted - 删除标志 1 已删除 0 未删除
     */
    public Byte getDeleted() {
        return deleted;
    }

    /**
     * 设置删除标志 1 已删除 0 未删除
     *
     * @param deleted 删除标志 1 已删除 0 未删除
     */
    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取每个应用有独立的secret，获取到的access_token只能本应用使用，所以每个应用的access_token应该分开来获取
     *
     * @return access_token - 每个应用有独立的secret，获取到的access_token只能本应用使用，所以每个应用的access_token应该分开来获取
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * 设置每个应用有独立的secret，获取到的access_token只能本应用使用，所以每个应用的access_token应该分开来获取
     *
     * @param accessToken 每个应用有独立的secret，获取到的access_token只能本应用使用，所以每个应用的access_token应该分开来获取
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * 获取过期时间
     *
     * @return expires_in - 过期时间
     */
    public Date getExpiresIn() {
        return expiresIn;
    }

    /**
     * 设置过期时间
     *
     * @param expiresIn 过期时间
     */
    public void setExpiresIn(Date expiresIn) {
        this.expiresIn = expiresIn;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}