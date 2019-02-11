package cn.e3mall.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.e3mall.common.pojo.SearchResult;
import cn.e3mall.search.service.SearchService;

/**  

* <p>Title: SearchController</p>  

* <p>Description: 商品搜索控制层</p>  

* @author 赵天宇

* @date 2018年11月28日  

*/
@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;
	
	@Value("${SEARCH_RESULT_ROWS}")
	private Integer SEARCH_RESULT_ROWS;
	
	
	
	@RequestMapping("/search")
	public String searchList(String keyword,
			@RequestParam(defaultValue="1") Integer page,Model model) throws Exception{
		//因为是get请求，出现中文会乱码，所以要转换一下
		keyword=new String(keyword.getBytes("iso-8859-1"), "utf-8");
		
		
		
		//查询商品列表
		SearchResult searchResult = searchService.search(keyword, page, SEARCH_RESULT_ROWS);
		//把结果传递给页面，所以参数加一个 model
		model.addAttribute("query", keyword);
		model.addAttribute("totalPages", searchResult.getTatalPages());
		model.addAttribute("page", page);
		model.addAttribute("recourdCount", searchResult.getRecordCount());
		model.addAttribute("itemList", searchResult.getItemList());
		//返回逻辑视图
		return "search";
		
	}
	
	
}
