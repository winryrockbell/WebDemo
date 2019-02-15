package org.seckill.web;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillJson;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.enums.SeckillStatEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.Repeat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by YH on 2019/1/14.
 */

@Controller //放到Spring容器中
@RequestMapping("/seckill") //整体映射
public class SeckillController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired // 自动找到容器下的单例实现依赖注入
    private SeckillService seckillService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model){
        //Model + 页面 组成了 ModelAndView
        List<Seckill> list = seckillService.getSeckillList();
        model.addAttribute("seckilllist", list);
        return "list"; //返回到 /WEB-INF/jsp/list.jsp
    }

    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model){
        if(seckillId == null){
            //没拿到参数，直接重定向到列表页
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckillId);
        if(seckill == null){
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill", seckill);
        return "detail";
    }

    //ajax处理，返回json，暴露url地址

    @RequestMapping(value = "/{seckillId}/export",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody //spring看到这个会自动封装成json
    public SeckillJson<Exposer> exportUrl(@PathVariable("seckillId")Long seckillId){
        SeckillJson<Exposer> result;
        try {
            Exposer exposer =seckillService.exportSeckillUrl(seckillId);
            result = new SeckillJson<Exposer>(true, exposer);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            result = new SeckillJson<Exposer>(false,e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/{seckillId}/{md5}/execution",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    //killPhone是Request带的Cookie里的值，springmvc默认的required是true，也就是cookie没这个
    //K-V的话，会直接报错，改成false交给我们程序里判断
    public SeckillJson<SeckillResult> execute(
            @PathVariable("seckillId") Long seckillId,
            @PathVariable("md5") String md5,
            @CookieValue(value = "killPhone", required = false)Long phone
    ){
        if(phone == null){
            return new SeckillJson<SeckillResult>(false, "未注册");
        }
        SeckillJson<SeckillResult> result;
        try {
            SeckillResult seckillResult = seckillService.executeSeckill(seckillId, phone, md5);
            result = new SeckillJson<SeckillResult>(true, seckillResult);
        }
        catch(RepeatKillException e){
            SeckillResult seckillResult = new SeckillResult(seckillId, SeckillStatEnum.REPEAT_KILL);
            result = new SeckillJson<SeckillResult>(true, seckillResult);
        }
        catch(SeckillCloseException e){
            SeckillResult seckillResult = new SeckillResult(seckillId, SeckillStatEnum.END);
            result = new SeckillJson<SeckillResult>(true, seckillResult);
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            SeckillResult seckillResult = new SeckillResult(seckillId, SeckillStatEnum.INNER_ERROR);
            result = new SeckillJson<SeckillResult>(false, seckillResult);
        }
        return result;
    }

    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public SeckillJson<Long> time(){
        Date now = new Date();
        return new SeckillJson<Long>(true, now.getTime() / 1000);

    }

    @RequestMapping(value="/update")
    public String updateTime(Long seckillId, String startTime, String endTime){
        logger.info("666");
        System.out.println("lyh:" + seckillId + " "+ startTime + " "+ endTime);
        seckillService.updateTime(seckillId, startTime, endTime);
        return "redirect:/seckill/list";
    }
}
