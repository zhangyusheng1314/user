package com.zys.user.utils;

import com.zys.user.enums.ResultEnums;
import com.zys.user.vo.ResultVO;
import lombok.Data;

@Data
public class ResultVOUtils {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setData(object);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVO success(ResultEnums resultEnums){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnums.getCode());
        resultVO.setMsg(resultEnums.getMessage());
        return resultVO;
    }

    public static ResultVO error(ResultEnums resultEnums){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnums.getCode());
        resultVO.setMsg(resultEnums.getMessage());
        return resultVO;
    }
}
