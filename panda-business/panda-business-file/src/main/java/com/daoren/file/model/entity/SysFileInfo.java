package com.daoren.file.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.daoren.mybatis.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 文件
 * </p>
 *
 * @author daoren
 * @since 2022-03-08
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ApiModel(value = "FileInfo对象", description = "文件")
public class SysFileInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文件名称")
    private String name;

    @ApiModelProperty("文件大小")
    private Integer size;

    @ApiModelProperty("文件格式")
    private String format;

    @ApiModelProperty("文件内容")
    private byte[] content;

    @ApiModelProperty("文件路径")
    private String path;

    @ApiModelProperty("存储类型：0 数据库存储；1 文件存储；")
    private Integer storageType;

    @TableField(exist = false)
    private CustomUser customUser;

}
