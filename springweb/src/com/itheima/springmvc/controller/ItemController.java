package com.itheima.springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.itheima.springmvc.pojo.Items;
import com.itheima.springmvc.pojo.QueryVo;
import com.itheima.springmvc.service.ItemService;

@Controller
public class ItemController {

	@Autowired
    private ItemService itemService;

    @RequestMapping("/itemList")
    public ModelAndView getItemsList() {
        // 查询商品列表
        List<Items> itemList = itemService.getItemList();
        // 把查询结果传递给页面
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("itemList", itemList); // addObject方法相当于放到request域上
        // 设置逻辑视图
        modelAndView.setViewName("itemList"); 
        // 返回结果
        return modelAndView;
    }
    
    //使用模型设置返回结果
    @RequestMapping("/editItem")
    public String editItem(HttpServletRequest request,HttpServletResponse response, HttpSession session, Model model) {
        // 从request中取出参数
        String strId = request.getParameter("id");
        int id = new Integer(strId);
        // 调用服务
        Items items = itemService.getItemById(id);
        // 使用模型设置返回结果，model是框架给我们传递过来的对象，所以这个对象也不需要我们返回
        model.addAttribute("item", items);
        // 返回逻辑视图
        return "editItem";
    }

/*
    //使用ModelAndView设置返回结果
    @RequestMapping("/editItem")
    public ModelAndView editItem(HttpServletRequest request) {
        // 从request中取出参数
        String strId = request.getParameter("id");
        int id = new Integer(strId);
        // 调用服务
        Items items = itemService.getItemById(id);
        // 把结果传递给页面
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("item", items);
        // 设置逻辑视图
        modelAndView.setViewName("editItem");
        return modelAndView;
    }
    
    //当请求的参数名称和处理器形参名称一致时会将请求参数与形参进行绑定
    @RequestMapping("/editItem")
    public String editItem(Integer id, Model model) { 
        // 调用服务
        Items items = itemService.getItemById(id);
        // 把数据传递给页面，需要用到Model接口
        model.addAttribute("item", items); 
        // 返回逻辑视图
        return "editItem";
    }
    
    //使用@RequestParam注解
    @RequestMapping("/editItem")
    public String editItems(@RequestParam(value="id",defaultValue="1",required=true) Integer ids, Model model) { 
        // 调用服务
        Items items = itemService.getItemById(ids);
        // 把数据传递给页面，需要用到Model接口
        model.addAttribute("item", items); 
        // 返回逻辑视图
        return "editItem";
    }
*/    
    @RequestMapping("/updateitem")
    public String updateItems(Items items) {//请求的参数名称和pojo的属性名称一致，会自动将请求参数赋值给pojo的属性。
        itemService.updateItem(items);
        // 返回成功页面
        return "success";
    }
    
    @RequestMapping("/queryitem")
    public String queryItem(QueryVo queryVo) {
        // 打印绑定结果
        System.out.println(queryVo.getItems().getId());
        System.out.println(queryVo.getItems().getName());
        return "success";
    }
}
