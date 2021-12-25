package com.ruoyi.web.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 报修信息对象 order
 *
 * @author guo
 * @date 2021-12-24
 */
public class Order extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 维修记录id */
    private Long id;

    /** 设备编号 */
    @Excel(name = "设备编号")
    private String deviceNumber;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String deviceName;

    /** 设备购买时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "设备购买时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deviceBuyTime;

    /** 设备生产线 */
    @Excel(name = "设备生产线")
    private String deviceProductionLine;

    /** 报修时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报修时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /** 维修开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "维修开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date repairStartTime;

    /** 维修结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "维修结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date repairEndTime;

    /** 故障部位 */
    @Excel(name = "故障部位")
    private String faultPosition;

    /** 故障原因 */
    @Excel(name = "故障原因")
    private String faultReason;

    /** 修理过程 */
    @Excel(name = "修理过程")
    private String repairProcess;

    /** 更换设备情况 */
    @Excel(name = "更换设备情况")
    private String deviceReplacement;

    /** 修理情况 */
    @Excel(name = "修理情况")
    private String repairResult;

    /** 报修人 */
    @Excel(name = "报修人")
    private String submitMan;

    /** 维修人 */
    @Excel(name = "维修人")
    private String repairMan;

    /** 验收人 */
    @Excel(name = "验收人")
    private String checkMan;

    /** 备注 */
    @Excel(name = "备注")
    private String note;

    /** 更换备件型号 */
    @Excel(name = "更换备件型号")
    private String sparePartType;

    /** 备件剩余库存 */
    @Excel(name = "备件剩余库存")
    private Long sparePartStockNumber;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setDeviceNumber(String deviceNumber)
    {
        this.deviceNumber = deviceNumber;
    }

    public String getDeviceNumber()
    {
        return deviceNumber;
    }
    public void setDeviceName(String deviceName)
    {
        this.deviceName = deviceName;
    }

    public String getDeviceName()
    {
        return deviceName;
    }
    public void setDeviceBuyTime(Date deviceBuyTime)
    {
        this.deviceBuyTime = deviceBuyTime;
    }

    public Date getDeviceBuyTime()
    {
        return deviceBuyTime;
    }
    public void setDeviceProductionLine(String deviceProductionLine)
    {
        this.deviceProductionLine = deviceProductionLine;
    }

    public String getDeviceProductionLine()
    {
        return deviceProductionLine;
    }
    public void setSubmitTime(Date submitTime)
    {
        this.submitTime = submitTime;
    }

    public Date getSubmitTime()
    {
        return submitTime;
    }
    public void setRepairStartTime(Date repairStartTime)
    {
        this.repairStartTime = repairStartTime;
    }

    public Date getRepairStartTime()
    {
        return repairStartTime;
    }
    public void setRepairEndTime(Date repairEndTime)
    {
        this.repairEndTime = repairEndTime;
    }

    public Date getRepairEndTime()
    {
        return repairEndTime;
    }
    public void setFaultPosition(String faultPosition)
    {
        this.faultPosition = faultPosition;
    }

    public String getFaultPosition()
    {
        return faultPosition;
    }
    public void setFaultReason(String faultReason)
    {
        this.faultReason = faultReason;
    }

    public String getFaultReason()
    {
        return faultReason;
    }
    public void setRepairProcess(String repairProcess)
    {
        this.repairProcess = repairProcess;
    }

    public String getRepairProcess()
    {
        return repairProcess;
    }
    public void setDeviceReplacement(String deviceReplacement)
    {
        this.deviceReplacement = deviceReplacement;
    }

    public String getDeviceReplacement()
    {
        return deviceReplacement;
    }
    public void setRepairResult(String repairResult)
    {
        this.repairResult = repairResult;
    }

    public String getRepairResult()
    {
        return repairResult;
    }
    public void setSubmitMan(String submitMan)
    {
        this.submitMan = submitMan;
    }

    public String getSubmitMan()
    {
        return submitMan;
    }
    public void setRepairMan(String repairMan)
    {
        this.repairMan = repairMan;
    }

    public String getRepairMan()
    {
        return repairMan;
    }
    public void setCheckMan(String checkMan)
    {
        this.checkMan = checkMan;
    }

    public String getCheckMan()
    {
        return checkMan;
    }
    public void setNote(String note)
    {
        this.note = note;
    }

    public String getNote()
    {
        return note;
    }
    public void setSparePartType(String sparePartType)
    {
        this.sparePartType = sparePartType;
    }

    public String getSparePartType()
    {
        return sparePartType;
    }
    public void setSparePartStockNumber(Long sparePartStockNumber)
    {
        this.sparePartStockNumber = sparePartStockNumber;
    }

    public Long getSparePartStockNumber()
    {
        return sparePartStockNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deviceNumber", getDeviceNumber())
            .append("deviceName", getDeviceName())
            .append("deviceBuyTime", getDeviceBuyTime())
            .append("deviceProductionLine", getDeviceProductionLine())
            .append("submitTime", getSubmitTime())
            .append("repairStartTime", getRepairStartTime())
            .append("repairEndTime", getRepairEndTime())
            .append("faultPosition", getFaultPosition())
            .append("faultReason", getFaultReason())
            .append("repairProcess", getRepairProcess())
            .append("deviceReplacement", getDeviceReplacement())
            .append("repairResult", getRepairResult())
            .append("submitMan", getSubmitMan())
            .append("repairMan", getRepairMan())
            .append("checkMan", getCheckMan())
            .append("note", getNote())
            .append("sparePartType", getSparePartType())
            .append("sparePartStockNumber", getSparePartStockNumber())
            .toString();
    }
}
