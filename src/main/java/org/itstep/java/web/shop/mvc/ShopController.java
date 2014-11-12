package org.itstep.java.web.shop.mvc;

import java.util.List;
import org.itstep.java.web.shop.model.Basket;
import org.itstep.java.web.shop.model.BasketItem;
import org.itstep.java.web.shop.model.Category;
import org.itstep.java.web.shop.model.Good;
import org.itstep.java.web.shop.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/shop")
@SessionAttributes(types = Basket.class)
public class ShopController {
    @Autowired
    GoodService srv;
    
    @RequestMapping(method = RequestMethod.GET)
    public String categories(ModelMap model) {
        List<Category> cats = srv.getCategoryList();
        model.addAttribute("cats", cats);
        
        Basket basket = (Basket) model.get("basket");
        if (basket == null) {
            basket = new Basket();
            model.addAttribute("basket", basket);
        }
        
        return "categories";
    }
    
    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public String goods(
            @PathVariable(value = "id")
            Integer id,
            ModelMap model) {
        List<Good> goods = srv.getList(id);
        model.addAttribute("goods", goods);
        
        Basket basket = (Basket) model.get("basket");
        if (basket == null) {
            basket = new Basket();
            
        }
        
        model.addAttribute("basket", basket);
        return "goodlist";
    }
    
    @RequestMapping(value = "/add/{catId}/{id}", method = RequestMethod.GET)
    public String addToBasket(
            @PathVariable(value = "catId")
            Integer catId,
            @PathVariable(value = "id")
            Integer id,
            ModelMap model) {
        
        Basket basket = (Basket) model.get("basket");
        if (basket == null) basket = new Basket();
        
        
        BasketItem i = basket.get(id);
        if (i == null) {
            i = new BasketItem();
            i.setGood(srv.find(id));
        } else {
            i.incCount();
        }

        basket.put(id, i);
        
        model.addAttribute("basket", basket);
        return "redirect:/shop/category/" + catId.toString();
    }
}
