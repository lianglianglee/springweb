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
        // ��ѯ��Ʒ�б�
        List<Items> itemList = itemService.getItemList();
        // �Ѳ�ѯ������ݸ�ҳ��
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("itemList", itemList); // addObject�����൱�ڷŵ�request����
        // �����߼���ͼ
        modelAndView.setViewName("itemList"); 
        // ���ؽ��
        return modelAndView;
    }
    
    //ʹ��ģ�����÷��ؽ��
    @RequestMapping("/editItem")
    public String editItem(HttpServletRequest request,HttpServletResponse response, HttpSession session, Model model) {
        // ��request��ȡ������
        String strId = request.getParameter("id");
        int id = new Integer(strId);
        // ���÷���
        Items items = itemService.getItemById(id);
        // ʹ��ģ�����÷��ؽ����model�ǿ�ܸ����Ǵ��ݹ����Ķ��������������Ҳ����Ҫ���Ƿ���
        model.addAttribute("item", items);
        // �����߼���ͼ
        return "editItem";
    }

/*
    //ʹ��ModelAndView���÷��ؽ��
    @RequestMapping("/editItem")
    public ModelAndView editItem(HttpServletRequest request) {
        // ��request��ȡ������
        String strId = request.getParameter("id");
        int id = new Integer(strId);
        // ���÷���
        Items items = itemService.getItemById(id);
        // �ѽ�����ݸ�ҳ��
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("item", items);
        // �����߼���ͼ
        modelAndView.setViewName("editItem");
        return modelAndView;
    }
    
    //������Ĳ������ƺʹ������β�����һ��ʱ�Ὣ����������βν��а�
    @RequestMapping("/editItem")
    public String editItem(Integer id, Model model) { 
        // ���÷���
        Items items = itemService.getItemById(id);
        // �����ݴ��ݸ�ҳ�棬��Ҫ�õ�Model�ӿ�
        model.addAttribute("item", items); 
        // �����߼���ͼ
        return "editItem";
    }
    
    //ʹ��@RequestParamע��
    @RequestMapping("/editItem")
    public String editItems(@RequestParam(value="id",defaultValue="1",required=true) Integer ids, Model model) { 
        // ���÷���
        Items items = itemService.getItemById(ids);
        // �����ݴ��ݸ�ҳ�棬��Ҫ�õ�Model�ӿ�
        model.addAttribute("item", items); 
        // �����߼���ͼ
        return "editItem";
    }
*/    
    @RequestMapping("/updateitem")
    public String updateItems(Items items) {//����Ĳ������ƺ�pojo����������һ�£����Զ������������ֵ��pojo�����ԡ�
        itemService.updateItem(items);
        // ���سɹ�ҳ��
        return "success";
    }
    
    @RequestMapping("/queryitem")
    public String queryItem(QueryVo queryVo) {
        // ��ӡ�󶨽��
        System.out.println(queryVo.getItems().getId());
        System.out.println(queryVo.getItems().getName());
        return "success";
    }
}
