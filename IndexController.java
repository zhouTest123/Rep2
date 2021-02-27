package cn.sdormitory.controller.smartdor;


import cn.sdormitory.common.api.CommonResult;
import cn.sdormitory.common.utils.PropertiesUtils;
import cn.sdormitory.request.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @创建人：zhouyang
 * @创建时间：2020/12/4 15:33
 * @version：V1.0
 */
@RestController
@Api(tags = "Smartdor-aidevice=> 首页")
@RequestMapping("/smartdor/aidevice")
public class IndexController {

    @ApiOperation("aiopen => 首页开闸")
    @GetMapping(value = "/aiopen")
    public CommonResult<Integer> aiopen() {
        String ip = PropertiesUtils.get("device.properties","sdormitory.device1.ip");
        String key = PropertiesUtils.get("device.properties","sdormitory.device1.key");
        String url=ip+"/open";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key="+key);
        String result= HttpRequest.sendGet(url,stringBuffer.toString());
        JSONObject parseObject=JSONObject.parseObject(result);
        String status=parseObject.getString("status");
        int count=0;
        if (""!=status && status!=null && status.equals("0")) {
            count=1;
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }


}
