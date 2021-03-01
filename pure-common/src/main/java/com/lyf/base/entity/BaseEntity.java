package com.lyf.base.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liyf on 2020-03-01
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel(value = "BaseEntity对象")
public class BaseEntity implements Serializable {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId("id")
    protected Long id;

    /**
     * 逻辑删除 0.否 1.是
     */
    @ApiModelProperty(value = "逻辑删除", hidden = true)
    @TableField(value = "deleted", fill = FieldFill.INSERT)
    @TableLogic
    protected Integer deleted;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号", hidden = true)
    @TableField(value = "version", fill = FieldFill.INSERT)
    @Version
    protected Integer version;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField(value = "status", fill = FieldFill.INSERT)
    protected Integer status;

    /**
     * 附件id
     */
    @ApiModelProperty(value = "附件id")
    @TableField(value = "file_uuid", fill = FieldFill.INSERT)
    protected Long fileUuid;


    /**
     * 创建人id
     */
    @ApiModelProperty(value = "创建人id", hidden = true)
    @TableField(value = "creator_id", fill = FieldFill.INSERT)
    protected Long creator_id;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected Date createTime;

    /**
     * 更新人id
     */
    @ApiModelProperty(value = "更新人id", hidden = true)
    @TableField(value = "updater_id", fill = FieldFill.UPDATE)
    protected Long updaterId;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", hidden = true)
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected Date updateTime;

    /**
     * vo 扩展自定义字段
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "vo 扩展自定义字段", hidden = true)
    private Map<String, Object> vos = new HashMap<>();
}
